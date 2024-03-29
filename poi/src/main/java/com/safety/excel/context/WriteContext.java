package com.safety.excel.context;

import com.safety.excel.annotation.ExcelProperty;
import com.safety.excel.annotation.Select;
import com.safety.excel.annotation.Textattr;
import com.safety.excel.event.WriteHandler;
import com.safety.excel.metadata.BaseRowModel;
import com.safety.excel.metadata.DropDownHelper;
import com.safety.excel.metadata.ExcelHeadProperty;
import com.safety.excel.metadata.Table;
import com.safety.excel.support.ExcelTypeEnum;
import com.safety.excel.util.CollectionUtils;
import com.safety.excel.util.StyleUtil;
import com.safety.excel.util.WorkBookUtil;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.safety.excel.util.StyleUtil.buildSheetStyle;

/**
 * A context is the main anchorage point of a excel writer.
 *
 * @author jipengfei
 */
public class WriteContext {

    /***
     * The sheet currently written
     */
    private Sheet currentSheet;

    /**
     * current param
     */
    private com.safety.excel.metadata.Sheet currentSheetParam;

    /**
     * The sheet currently written's name
     */
    private String currentSheetName;

    /**
     *
     */
    private Table currentTable;

    /**
     * Excel type
     */
    private ExcelTypeEnum excelType;

    /**
     * POI Workbook
     */
    private Workbook workbook;

    /**
     * Final output stream
     */
    private OutputStream outputStream;

    /**
     * Written form collection
     */
    private Map<Integer, Table> tableMap = new ConcurrentHashMap<Integer, Table>();

    /**
     * Cell default style
     */
    private CellStyle defaultCellStyle;

    /**
     * Current table head  style
     */
    private CellStyle currentHeadCellStyle;

    /**
     * Current table content  style
     */
    private CellStyle currentContentCellStyle;

    /**
     * the header attribute of excel
     */
    private ExcelHeadProperty excelHeadProperty;

    private boolean needHead = Boolean.TRUE;

    /**
     * 下拉框数据源
     */
    private Map<String,Map<String,String>> currentDropDownSource;

    private WriteHandler afterWriteHandler;

    public WriteHandler getAfterWriteHandler() {
        return afterWriteHandler;
    }

    public WriteContext(InputStream templateInputStream, OutputStream out, ExcelTypeEnum excelType,
                        boolean needHead, WriteHandler afterWriteHandler) throws IOException {
        this.needHead = needHead;
        this.outputStream = out;
        this.afterWriteHandler = afterWriteHandler;
        this.workbook = WorkBookUtil.createWorkBook(templateInputStream, excelType);
        this.defaultCellStyle = StyleUtil.buildDefaultCellStyle(this.workbook);

    }

    /**
     * @param sheet
     */
    public void currentSheet(com.safety.excel.metadata.Sheet sheet) {
        if (null == currentSheetParam || currentSheetParam.getSheetNo() != sheet.getSheetNo()) {
            cleanCurrentSheet();
            currentSheetParam = sheet;
            try {
                this.currentSheet = workbook.getSheetAt(sheet.getSheetNo() - 1);
            } catch (Exception e) {
                this.currentSheet = WorkBookUtil.createSheet(workbook, sheet);
                if (null != afterWriteHandler) {
                    this.afterWriteHandler.sheet(sheet.getSheetNo(), currentSheet);
                }
            }
            buildSheetStyle(currentSheet, sheet.getColumnWidthMap());
            /** **/
            this.initCurrentSheet(sheet);
        }

    }

    private void initCurrentSheet(com.safety.excel.metadata.Sheet sheet) {

        /** **/
        initExcelHeadProperty(sheet.getHead(), sheet.getClazz());

        initTableStyle(sheet.getTableStyle());

        initTableHead();

        initDropDownSource(sheet);
        //初始化下拉框
        initDropDown();
    }

    private void initDropDownSource(com.safety.excel.metadata.Sheet sheet) {
        if(sheet.getDropDownSource()!=null && sheet.getDropDownSource().size()>0){
            this.currentDropDownSource = sheet.getDropDownSource();
        }
    }

    /**
     * 初始化下拉框
     */
    private void initDropDown() {

        //表头行数
        int headRowNum = this.excelHeadProperty.getRowNum();
        //拿到所有的列
        Field[] declaredFields = this.currentSheetParam.getClazz().getDeclaredFields();
        if(declaredFields.length>0){
            for(Field field: declaredFields){
                Select annotation = field.getAnnotation(Select.class);
                if(annotation!=null && currentDropDownSource != null && currentDropDownSource.size()>0){
                    String name = field.getName();
                    Map<String, String> map = currentDropDownSource.get(name);
                    if(map!=null && map.size()>0){
                        ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
                        int index = excelProperty.index();
                        System.out.println("列索引："+index);
                        try {
                            DropDownHelper.addDropDownList((HSSFWorkbook) this.currentSheet.getWorkbook(),
                                    (HSSFSheet) this.currentSheet,
                                    name, map,
                                    headRowNum,
                                    65535,index);
                        } catch (Exception e) {
                            System.out.println("创建下拉框失败."+e);
                        }
                    }
                }
            }
        }
    }

    private void cleanCurrentSheet() {
        this.currentSheet = null;
        this.currentSheetParam = null;
        this.excelHeadProperty = null;
        this.currentHeadCellStyle = null;
        this.currentContentCellStyle = null;
        this.currentTable = null;

    }

    /**
     * init excel header
     *
     * @param head
     * @param clazz
     */
    private void initExcelHeadProperty(List<List<String>> head, Class<? extends BaseRowModel> clazz) {
        if (head != null || clazz != null) { this.excelHeadProperty = new ExcelHeadProperty(clazz, head); }
    }

    public void initTableHead() {
        if (needHead && null != excelHeadProperty && !CollectionUtils.isEmpty(excelHeadProperty.getHead())) {
            int startRow = currentSheet.getLastRowNum();
            if (startRow > 0) {
                startRow += 4;
            }else {
                startRow = currentSheetParam.getStartRow();
            }
            addMergedRegionToCurrentSheet(startRow);
            int i = startRow;
            for (; i < this.excelHeadProperty.getRowNum() + startRow; i++) {
                Row row = WorkBookUtil.createRow(currentSheet, i);
                if (null != afterWriteHandler) {
                    this.afterWriteHandler.row(i, row);
                }
                addOneRowOfHeadDataToExcel(row, this.excelHeadProperty.getHeadByRowNum(i - startRow));
            }
        }
    }

    private void addMergedRegionToCurrentSheet(int startRow) {
        for (com.safety.excel.metadata.CellRange cellRangeModel : excelHeadProperty.getCellRangeModels()) {
            currentSheet.addMergedRegion(new CellRangeAddress(cellRangeModel.getFirstRow() + startRow,
                cellRangeModel.getLastRow() + startRow,
                cellRangeModel.getFirstCol(), cellRangeModel.getLastCol()));
        }
    }

    private void addOneRowOfHeadDataToExcel(Row row, List<String> headByRowNum) {
        if (headByRowNum != null && headByRowNum.size() > 0) {
            for (int i = 0; i < headByRowNum.size(); i++) {
                Cell cell = WorkBookUtil.createCell(row, i, getCurrentHeadCellStyle(), headByRowNum.get(i));
                if (null != afterWriteHandler) {
                    this.afterWriteHandler.cell(i, cell);
                }
            }
        }
    }

    private void initTableStyle(com.safety.excel.metadata.TableStyle tableStyle) {
        if (tableStyle != null) {
            this.currentHeadCellStyle = StyleUtil.buildCellStyle(this.workbook, tableStyle.getTableHeadFont(),
                tableStyle.getTableHeadBackGroundColor());
            this.currentContentCellStyle = StyleUtil.buildCellStyle(this.workbook, tableStyle.getTableContentFont(),
                tableStyle.getTableContentBackGroundColor());
        }
    }

    private void cleanCurrentTable() {
        this.excelHeadProperty = null;
        this.currentHeadCellStyle = null;
        this.currentContentCellStyle = null;
        this.currentTable = null;

    }

    public void currentTable(Table table) {
        if (null == currentTable || currentTable.getTableNo() != table.getTableNo()) {
            cleanCurrentTable();
            this.currentTable = table;
            this.initExcelHeadProperty(table.getHead(), table.getClazz());
            this.initTableStyle(table.getTableStyle());
            this.initTableHead();
            this.initDropDown();
        }

    }

    public ExcelHeadProperty getExcelHeadProperty() {
        return this.excelHeadProperty;
    }

    public boolean needHead() {
        return this.needHead;
    }

    public Sheet getCurrentSheet() {
        return currentSheet;
    }

    public void setCurrentSheet(Sheet currentSheet) {
        this.currentSheet = currentSheet;
    }

    public String getCurrentSheetName() {
        return currentSheetName;
    }

    public void setCurrentSheetName(String currentSheetName) {
        this.currentSheetName = currentSheetName;
    }

    public ExcelTypeEnum getExcelType() {
        return excelType;
    }

    public void setExcelType(ExcelTypeEnum excelType) {
        this.excelType = excelType;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public CellStyle getCurrentHeadCellStyle() {
        return this.currentHeadCellStyle == null ? defaultCellStyle : this.currentHeadCellStyle;
    }

    public CellStyle getCurrentContentStyle() {
        return this.currentContentCellStyle;
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public com.safety.excel.metadata.Sheet getCurrentSheetParam() {
        return currentSheetParam;
    }

    public void setCurrentSheetParam(com.safety.excel.metadata.Sheet currentSheetParam) {
        this.currentSheetParam = currentSheetParam;
    }

    public Table getCurrentTable() {
        return currentTable;
    }

    public void setCurrentTable(Table currentTable) {
        this.currentTable = currentTable;
    }
}



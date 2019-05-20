package com.safety.excel.write;

import com.safety.excel.annotation.ExcelProperty;
import com.safety.excel.annotation.Select;
import com.safety.excel.annotation.Textattr;
import com.safety.excel.context.WriteContext;
import com.safety.excel.event.WriteHandler;
import com.safety.excel.exception.ExcelGenerateException;
import com.safety.excel.metadata.BaseRowModel;
import com.safety.excel.metadata.ExcelColumnProperty;
import com.safety.excel.metadata.Sheet;
import com.safety.excel.metadata.Table;
import com.safety.excel.support.ExcelTypeEnum;
import com.safety.excel.util.CollectionUtils;
import com.safety.excel.util.POITempFile;
import com.safety.excel.util.TypeUtil;
import com.safety.excel.util.WorkBookUtil;
import net.sf.cglib.beans.BeanMap;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author jipengfei
 */
public class ExcelBuilderImpl implements ExcelBuilder {

    private WriteContext context;

    public ExcelBuilderImpl(InputStream templateInputStream,
                            OutputStream out,
                            ExcelTypeEnum excelType,
                            boolean needHead, WriteHandler writeHandler) {
        try {
            //初始化时候创建临时缓存目录，用于规避POI在并发写bug
            POITempFile.createPOIFilesDirectory();
            context = new WriteContext(templateInputStream, out, excelType, needHead, writeHandler);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addContent(List data, int startRow) {
        if (CollectionUtils.isEmpty(data)) {
            return;
        }
        int rowNum = context.getCurrentSheet().getLastRowNum();
        if (rowNum == 0) {
            Row row = context.getCurrentSheet().getRow(0);
            if (row == null) {
                if (context.getExcelHeadProperty() == null || !context.needHead()) {
                    rowNum = -1;
                }
            }
        }
        if (rowNum < startRow) {
            rowNum = startRow;
        }
        for (int i = 0; i < data.size(); i++) {
            int n = i + rowNum + 1;
            addOneRowOfDataToExcel(data.get(i), n);
        }
    }

    @Override
    public void addContent(List data, Sheet sheetParam) {
        context.currentSheet(sheetParam);
        addContent(data, sheetParam.getStartRow());
        //设置字段列为文本属性
        Field[] declaredFields = sheetParam.getClazz().getDeclaredFields();
        for(Field field: declaredFields){
            Textattr annotation = field.getAnnotation(Textattr.class);
            if(annotation !=null){
                ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
                int index = excelProperty.index();
                Workbook workbook = this.context.getWorkbook();
                HSSFDataFormat format = (HSSFDataFormat) workbook.createDataFormat();
                HSSFCellStyle textStyle = (HSSFCellStyle) workbook.createCellStyle();
                textStyle.setDataFormat(format.getFormat("@"));
                this.context.getCurrentSheet().setColumnWidth(index,
                        this.context.getCurrentSheet().getColumnWidth(index));
                this.context.getCurrentSheet().setDefaultColumnStyle(index, textStyle);
            }
        }
    }

    @Override
    public void addContent(List data, Sheet sheetParam, Table table) {
        context.currentSheet(sheetParam);
        context.currentTable(table);
        addContent(data, sheetParam.getStartRow());
    }

    @Override
    public void merge(int firstRow, int lastRow, int firstCol, int lastCol) {
        CellRangeAddress cra = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
        context.getCurrentSheet().addMergedRegion(cra);
    }

    @Override
    public void finish() {
        try {
            context.getWorkbook().write(context.getOutputStream());
            context.getWorkbook().close();
        } catch (IOException e) {
            throw new ExcelGenerateException("IO error", e);
        }
    }

    private void addBasicTypeToExcel(List<Object> oneRowData, Row row) {
        if (CollectionUtils.isEmpty(oneRowData)) {
            return;
        }
        for (int i = 0; i < oneRowData.size(); i++) {
            Object cellValue = oneRowData.get(i);
            Cell cell = WorkBookUtil.createCell(row, i, context.getCurrentContentStyle(), cellValue,
                    TypeUtil.isNum(cellValue));
            if (null != context.getAfterWriteHandler()) {
                context.getAfterWriteHandler().cell(i, cell);
            }
        }
    }

    private void addJavaObjectToExcel(Object oneRowData, Row row) {
        int i = 0;
        BeanMap beanMap = BeanMap.create(oneRowData);
        for (ExcelColumnProperty excelHeadProperty : context.getExcelHeadProperty().getColumnPropertyList()) {
            BaseRowModel baseRowModel = (BaseRowModel)oneRowData;
            String cellValue = TypeUtil.getFieldStringValue(beanMap, excelHeadProperty.getField().getName(),
                    excelHeadProperty.getFormat());

            CellStyle cellStyle = baseRowModel.getStyle(i) != null ? baseRowModel.getStyle(i)
                    : context.getCurrentContentStyle();
            Cell cell = WorkBookUtil.createCell(row, i, cellStyle, cellValue,
                    TypeUtil.isNum(excelHeadProperty.getField()));
            if (null != context.getAfterWriteHandler()) {
                context.getAfterWriteHandler().cell(i, cell);
            }
            i++;
        }

    }

    private void addOneRowOfDataToExcel(Object oneRowData, int n) {
        Row row = WorkBookUtil.createRow(context.getCurrentSheet(), n);
        if (null != context.getAfterWriteHandler()) {
            context.getAfterWriteHandler().row(n, row);
        }
        if (oneRowData instanceof List) {
            addBasicTypeToExcel((List)oneRowData, row);
        } else {
            addJavaObjectToExcel(oneRowData, row);
        }
    }
}

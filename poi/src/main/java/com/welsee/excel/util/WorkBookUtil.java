package com.welsee.excel.util;

import com.welsee.excel.ExcelWriter;
import com.welsee.excel.context.WriteContext;
import com.welsee.excel.support.ExcelTypeEnum;
import com.welsee.excel.write.ExcelBuilderImpl;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

import static com.welsee.excel.util.StyleUtil.buildSheetStyle;

/**
 * 
 * @author jipengfei
 */
public class WorkBookUtil {

    public static Workbook createWorkBook(InputStream templateInputStream, ExcelTypeEnum excelType) throws IOException {
        Workbook workbook;
        if (ExcelTypeEnum.XLS.equals(excelType)) {
            workbook = (templateInputStream == null) ? new HSSFWorkbook() : new HSSFWorkbook(
                new POIFSFileSystem(templateInputStream));
        } else {
            workbook = (templateInputStream == null) ? new SXSSFWorkbook(500) : new SXSSFWorkbook(
                new XSSFWorkbook(templateInputStream));
        }
        return workbook;
    }

    public static Sheet createOrGetSheet(Workbook workbook, com.welsee.excel.metadata.Sheet sheet) {
        Sheet sheet1 = null;
        try {
            try {
                sheet1 = workbook.getSheetAt(sheet.getSheetNo()-1);
            } catch (Exception e) {
            }
            if (null == sheet1) {
                sheet1 = createSheet(workbook, sheet);
                buildSheetStyle(sheet1,sheet.getColumnWidthMap());
            }
        } catch (Exception e) {
            throw new RuntimeException("constructCurrentSheet error", e);
        }
        return sheet1;
    }

    public static Sheet createSheet(Workbook workbook, com.welsee.excel.metadata.Sheet sheet) {
        return workbook.createSheet(sheet.getSheetName() != null ? sheet.getSheetName() : sheet.getSheetNo() + "");
    }

    public static Row createRow(Sheet sheet, int rowNum) {
        return sheet.createRow(rowNum);
    }

    public static Cell createCell(Row row, int colNum, CellStyle cellStyle, String cellValue) {
        return createCell(row, colNum, cellStyle, cellValue, false);
    }

    public static Cell createCell(Row row, int colNum, CellStyle cellStyle, Object cellValue, Boolean isNum) {
        Cell cell = row.createCell(colNum);//haoyc
        cell.setCellStyle(cellStyle);
        if(cellValue instanceof String){
            cell.setCellType(CellType.STRING);
        }
        if (null != cellValue) {
            if (isNum) {
                cell.setCellValue(Double.parseDouble(cellValue.toString()));
            } else {
                cell.setCellValue(cellValue.toString());
            }
        }
        return cell;
    }

    /**
     * **获取workbook**
     * 因为EasyExcel这个库设计的原因
     * 只能使用反射获取workbook
     *
     * @param writer
     * @return
     */
    private static Workbook getWorkbook(ExcelWriter writer) {
        Workbook workbook = null;
        try {
            Class<?> clazz1 = Class.forName("com.welsee.excel.ExcelWriter");
            Field[] fs = clazz1.getDeclaredFields();
            for (Field field : fs) {
                // 要设置属性可达，不然会抛出IllegalAccessException异常
                field.setAccessible(true);
                if ("excelBuilder".equals(field.getName())) {
                    ExcelBuilderImpl excelBuilder = (ExcelBuilderImpl) field.get(writer);
                    Class<?> clazz2 = Class.forName("com.welsee.excel.write.ExcelBuilderImpl");
                    Field[] fs2 = clazz2.getDeclaredFields();
                    for (Field field2 : fs2) {
                        field2.setAccessible(true);
                        if ("context".equals(field2.getName())) {
                            WriteContext context = (WriteContext) field2.get(excelBuilder);
                            workbook = context.getWorkbook();
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return workbook;
    }
}

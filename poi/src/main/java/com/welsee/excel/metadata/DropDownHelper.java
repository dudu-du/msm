package com.welsee.excel.metadata;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.util.Map;

/**
 * 下拉框
 */
public class DropDownHelper {
    public static void addDropDownList(HSSFWorkbook workbook, HSSFSheet tarSheet,
                                       String hiddenSheetName,Map<String,String> menuItems,
                                       int firstRow, int lastRow, int column) throws Exception
    {
        if(null == workbook){
            throw new Exception("workbook为null");
        }
        if(null == tarSheet){
            throw new Exception("待添加菜单的sheet为null");
        }

        //必须以字母开头，最长为31位
        hiddenSheetName = "select_" + hiddenSheetName;
        //excel中的"名称"，用于标记隐藏sheet中的用作菜单下拉项的所有单元格
        String formulaId = "form" + hiddenSheetName;
        HSSFSheet hiddenSheet = workbook.createSheet(hiddenSheetName);//用于存储 下拉菜单数据
        //存储下拉菜单项的sheet页不显示
        workbook.setSheetHidden(workbook.getSheetIndex(hiddenSheet), true);

        HSSFRow row = null;
        HSSFCell cell = null;
        int rowNum = 0;
        for(Map.Entry<String,String> entry:menuItems.entrySet()){
            row = hiddenSheet.createRow(rowNum);
            //隐藏表的数据列必须和添加下拉菜单的列序号相同，否则不能显示下拉菜单
            cell = row.createCell(column);
            cell.setCellValue(entry.getKey());
            cell = row.createCell(column+1);
            cell.setCellValue(entry.getValue());
            rowNum++;
        }
        //TODO 07版本的支持待扩展
        HSSFName namedCell = workbook.createName();//创建"名称"标签，用于链接
        namedCell.setNameName(formulaId);
        namedCell.setRefersToFormula(hiddenSheetName + "!A$1:A$" + menuItems.size());
        HSSFDataValidationHelper dvHelper = new HSSFDataValidationHelper(tarSheet);
        DataValidationConstraint dvConstraint = dvHelper.createFormulaListConstraint(formulaId);

        CellRangeAddressList addressList = new CellRangeAddressList(firstRow, lastRow, column, column);
        HSSFDataValidation validation = (HSSFDataValidation)dvHelper.createValidation(dvConstraint, addressList);//添加菜单(将单元格与"名称"建立关联)
        tarSheet.addValidationData(validation);
    }
}

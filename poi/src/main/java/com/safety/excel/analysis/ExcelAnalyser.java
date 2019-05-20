package com.safety.excel.analysis;

import com.safety.excel.metadata.Sheet;

import java.util.List;

/**
 * Excel file analyser
 *
 * @author jipengfei
 */
public interface ExcelAnalyser {

    /**
     * parse one sheet
     *
     * @param sheetParam
     */
    Object analysis(Sheet sheetParam);

    /**
     * parse all sheets
     */
    Object analysis();

    /**
     * get all sheet of workbook
     *
     * @return all sheets
     */
    List<Sheet> getSheets();

}

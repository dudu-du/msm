package com.welsee.excel.context;

import com.welsee.excel.EasyExcelFactory;
import com.welsee.excel.event.AnalysisEventListener;
import com.welsee.excel.exception.CustomException;
import com.welsee.excel.exception.ExcelAnalysisException;
import com.welsee.excel.metadata.BaseRowModel;
import com.welsee.excel.metadata.ExcelHeadProperty;
import com.welsee.excel.metadata.Sheet;
import com.welsee.excel.support.ExcelTypeEnum;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author jipengfei
 */
public class AnalysisContextImpl implements AnalysisContext {

    private Object custom;

    private Sheet currentSheet;

    private ExcelTypeEnum excelType;

    private InputStream inputStream;

    private AnalysisEventListener eventListener;

    private Integer currentRowNum;

    private Integer totalCount;

    private ExcelHeadProperty excelHeadProperty;

    private boolean trim;

    private boolean use1904WindowDate = false;

    private Map<String,Map<String,String>> dropDownDataSource;

    @Override
    public void setUse1904WindowDate(boolean use1904WindowDate) {
        this.use1904WindowDate = use1904WindowDate;
    }

    @Override
    public Object getCurrentRowAnalysisResult() {
        return currentRowAnalysisResult;
    }

    @Override
    public void interrupt() {
        throw new ExcelAnalysisException("interrupt error");
    }

    @Override
    public boolean use1904WindowDate() {
        return use1904WindowDate;
    }

    @Override
    public void setCurrentRowAnalysisResult(Object currentRowAnalysisResult) {
        this.currentRowAnalysisResult = currentRowAnalysisResult;
    }

    private Object currentRowAnalysisResult;

    public AnalysisContextImpl(InputStream inputStream, ExcelTypeEnum excelTypeEnum, Object custom,
                               AnalysisEventListener listener, boolean trim) {
        this.custom = custom;
        this.eventListener = listener;
        this.inputStream = inputStream;
        this.excelType = excelTypeEnum;
        this.trim = trim;
    }

    @Override
    public void setCurrentSheet(Sheet currentSheet) {
        cleanCurrentSheet();
        this.currentSheet = currentSheet;
        if (currentSheet.getClazz() != null) {
            buildExcelHeadProperty(currentSheet.getClazz(), null);
//            buildDropDownDataSource(currentSheet.getClazz());
        }
    }

    private void cleanCurrentSheet() {
        this.currentSheet = null;
        this.excelHeadProperty = null;
        this.totalCount = 0;
        this.currentRowAnalysisResult = null;
        this.currentRowNum =0;
    }

    @Override
    public ExcelTypeEnum getExcelType() {
        return excelType;
    }

    public void setExcelType(ExcelTypeEnum excelType) {
        this.excelType = excelType;
    }

    public Object getCustom() {
        return custom;
    }

    public void setCustom(Object custom) {
        this.custom = custom;
    }

    @Override
    public Sheet getCurrentSheet() {
        return currentSheet;
    }

    @Override
    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public AnalysisEventListener getEventListener() {
        return eventListener;
    }

    public void setEventListener(AnalysisEventListener eventListener) {
        this.eventListener = eventListener;
    }

    @Override
    public Integer getCurrentRowNum() {
        return this.currentRowNum;
    }

    @Override
    public void setCurrentRowNum(Integer row) {
        this.currentRowNum = row;
    }

    @Override
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public ExcelHeadProperty getExcelHeadProperty() {
        return this.excelHeadProperty;
    }

    public Map<String, Map<String, String>> getDropDownDataSource() {
        return dropDownDataSource;
    }

    public void setDropDownDataSource(Map<String, Map<String, String>> dropDownDataSource) {
        this.dropDownDataSource = dropDownDataSource;
    }

    @Override
    public void buildExcelHeadProperty(Class<? extends BaseRowModel> clazz, List<String> headOneRow) {
        if (this.excelHeadProperty == null && (clazz != null || headOneRow != null)) {
            this.excelHeadProperty = new ExcelHeadProperty(clazz, new ArrayList<List<String>>());
        }
        if (this.excelHeadProperty.getHead() == null && headOneRow != null) {
            this.excelHeadProperty.appendOneRow(headOneRow);
        }else if(this.excelHeadProperty.getHead() != null && headOneRow != null){
            List<List<String>> head = this.excelHeadProperty.getHead();
            List<String> headsForModel = new ArrayList<>();
            this.excelHeadProperty.getHead().stream().forEach(arrs->{
                String s = arrs.get(0);
                headsForModel.add(s);
            });
            headsForModel.stream().collect(Collectors.toList()).sort(Comparator.reverseOrder());
            if(!headsForModel.equals(headOneRow)){
                throw new CustomException("模板不合法");
            }
        }
    }

    /**
     * 获取所有的下拉列表数据源
     * @param hiddenSheet
     */
    public void buildDropDownDataSource(Sheet hiddenSheet){
        if(this.dropDownDataSource==null){
            this.getInputStream();
            List<Object> read = EasyExcelFactory.read(this.getInputStream(),hiddenSheet);
            System.out.println(read);
            dropDownDataSource = new HashMap<>();
        }


    }

    @Override
    public boolean trim() {
        return this.trim;
    }
}

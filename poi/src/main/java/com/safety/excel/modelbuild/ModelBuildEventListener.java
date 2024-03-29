package com.safety.excel.modelbuild;

import com.safety.excel.context.AnalysisContext;
import com.safety.excel.event.AnalysisEventListener;
import com.safety.excel.exception.ExcelGenerateException;
import com.safety.excel.metadata.ExcelHeadProperty;
import com.safety.excel.util.TypeUtil;
import net.sf.cglib.beans.BeanMap;

import java.util.List;

/**
 * @author jipengfei
 */
public class ModelBuildEventListener extends AnalysisEventListener {

    @Override
    public void invoke(Object object, AnalysisContext context) {
        if (context.getExcelHeadProperty() != null && context.getExcelHeadProperty().getHeadClazz() != null) {
            try {
                Object resultModel = buildUserModel(context, (List<String>)object);
                context.setCurrentRowAnalysisResult(resultModel);
            } catch (Exception e) {
                throw new ExcelGenerateException(e);
            }
        }
    }

    private Object buildUserModel(AnalysisContext context, List<String> stringList) throws Exception {
        //表格数据读取
        ExcelHeadProperty excelHeadProperty = context.getExcelHeadProperty();
        Object resultModel = excelHeadProperty.getHeadClazz().newInstance();
        if (excelHeadProperty == null) {
            return resultModel;
        }
        BeanMap.create(resultModel).putAll(
            TypeUtil.getFieldValues(stringList, excelHeadProperty, context.use1904WindowDate()));
        return resultModel;
    }

    @Override
    public Object doAfterAllAnalysed(AnalysisContext context) {
        return null;
    }
}

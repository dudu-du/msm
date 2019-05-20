package com.welsee.excel.analysis;

import com.welsee.excel.context.AnalysisContext;
import com.welsee.excel.event.AnalysisEventListener;
import com.welsee.excel.event.AnalysisEventRegisterCenter;
import com.welsee.excel.event.OneRowAnalysisFinishEvent;
import com.welsee.excel.metadata.Sheet;
import com.welsee.excel.util.TypeUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jipengfei
 */
public abstract class BaseSaxAnalyser implements AnalysisEventRegisterCenter, ExcelAnalyser {

    protected AnalysisContext analysisContext;

    private LinkedHashMap<String, AnalysisEventListener> listeners = new LinkedHashMap<String, AnalysisEventListener>();

    /**
     * execute method
     */
    protected abstract void execute();


    @Override
    public void appendLister(String name, AnalysisEventListener listener) {
        if (!listeners.containsKey(name)) {
            listeners.put(name, listener);
        }
    }

    @Override
    public Object analysis(Sheet sheetParam) {
        execute();
        return null;
    }

    @Override
    public Object analysis() {
        execute();
        return null;
    }

    /**
     */
    @Override
    public void cleanAllListeners() {
        listeners = new LinkedHashMap<String, AnalysisEventListener>();
    }

    @Override
    public void notifyListeners(OneRowAnalysisFinishEvent event) {
        analysisContext.setCurrentRowAnalysisResult(event.getData());
        /** Parsing header content **/
        if (analysisContext.getCurrentRowNum() < analysisContext.getCurrentSheet().getHeadLineMun()) {
            if (analysisContext.getCurrentRowNum() <= analysisContext.getCurrentSheet().getHeadLineMun() - 1) {
                analysisContext.buildExcelHeadProperty(null,
                    (List<String>)analysisContext.getCurrentRowAnalysisResult());
            }
        } else {
            List<String> content = converter((List<String>)event.getData());
            /** Parsing Analyze the body content **/
            analysisContext.setCurrentRowAnalysisResult(content);
            if (listeners.size() == 1) {
                analysisContext.setCurrentRowAnalysisResult(content);
            }
            /**  notify all event listeners **/
            for (Map.Entry<String, AnalysisEventListener> entry : listeners.entrySet()) {
                entry.getValue().invoke(analysisContext.getCurrentRowAnalysisResult(), analysisContext);
            }
        }
    }

    private List<String> converter(List<String> data) {
        List<String> list = new ArrayList<String>();
        if (data != null) {
            for (String str : data) {
                list.add(TypeUtil.formatFloat(str));
            }
        }
        return list;
    }

}

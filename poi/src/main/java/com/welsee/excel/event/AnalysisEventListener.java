package com.welsee.excel.event;

import com.welsee.excel.context.AnalysisContext;

/**
 *
 *
 * @author jipengfei
 */
public abstract class AnalysisEventListener<T> {

    /**
     * when analysis one row trigger invoke function
     *
     * @param object  one row data
     * @param context analysis context
     */
    public abstract void invoke(T object, AnalysisContext context);

    /**
     * if have something to do after all  analysis
     *
     * @param context
     */
    public abstract Object doAfterAllAnalysed(AnalysisContext context);
}

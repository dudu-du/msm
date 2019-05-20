package com.welsee.excel.test.listen;

import com.welsee.excel.context.AnalysisContext;
import com.welsee.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

public class ExcelListener extends AnalysisEventListener {


    private List<Object>  data = new ArrayList<Object>();

    @Override
    public void invoke(Object object, AnalysisContext context) {
        System.out.println(context.getCurrentSheet());
        if(data.size()<=100){
            data.add(object);
        }else {
            doSomething();
            data = new ArrayList<Object>();
        }
    }

    @Override
    public Object doAfterAllAnalysed(AnalysisContext context) {
       return doSomething();
    }
    public Object doSomething(){
        for (Object o:data) {
            System.out.println(o);
        }
        return "导入测试完成";
    }
}

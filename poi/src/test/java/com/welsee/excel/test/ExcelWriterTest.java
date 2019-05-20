package com.welsee.excel.test;

import com.welsee.excel.EasyExcelFactory;
import com.welsee.excel.ExcelWriter;
import com.welsee.excel.metadata.Sheet;
import com.welsee.excel.metadata.TableStyle;
import com.welsee.excel.support.ExcelTypeEnum;
import com.welsee.excel.test.model.WriteModel;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;


public class ExcelWriterTest {

    private List<WriteModel> list;
    TableStyle tableStyle;

    @Before
    public void before(){
        List<WriteModel> model1s = new ArrayList<WriteModel>();
        for (int i = 0; i <1 ; i++) {
            WriteModel model = new WriteModel();
            model.setRealName("郝永超");
            model.setLoginName("haoyongchao");
            model.setTel("13033333333");
            model.setOrgName("复兴路小学");
            model1s.add(model);
        }
        list=model1s;

        tableStyle = new TableStyle();

    }


    @Test
    public void writeV2003() throws IOException {
        /*OutputStream out = new FileOutputStream("C:\\Users\\haoyc\\Desktop\\2003.xls");
        ExcelWriter writer = EasyExcelFactory.getWriter(out, ExcelTypeEnum.XLS,true);

        Map<String,Map<String,String>> dropDownSource = new HashMap<>();
        Map<String,String> dropDown = new HashMap<>();
        dropDown.put("复兴路小学","111111");
        dropDown.put("高新区小学","222222");
        dropDownSource.put("orgName",dropDown);
        dropDown = new HashMap<>();
        dropDown.put("男","0");
        dropDown.put("女","1");
        dropDownSource.put("sex",dropDown);
        //写第二个sheet sheet2  模型上打有表头的注解，合并单元格
        Sheet sheet2 = new Sheet(2, 3, WriteModel.class, "第二个sheet", null,dropDownSource);
        sheet2.setTableStyle(tableStyle);
        writer.write(list, sheet2);

        writer.finish();
        out.close();*/
    }
}
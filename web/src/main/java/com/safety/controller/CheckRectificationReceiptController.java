package com.safety.controller;


import com.safety.entity.CheckRectificationReceipt;
import com.safety.service.ICheckRectificationReceiptService;
import com.safety.tools.BaseController;
import com.safety.tools.JsonResult;
import com.safety.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 隐患整改回执单 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkRectificationReceipt")
public class CheckRectificationReceiptController extends BaseController {
    @Autowired
    private ICheckRectificationReceiptService iCheckRectificationReceiptService;
    /**
     * 添加
     * @param checkRectificationReceipt
     * @return
     */
    @RequestMapping(value = "/checkRectificationReceipt",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckRectificationReceipt(@RequestBody CheckRectificationReceipt checkRectificationReceipt){
        String id = UUIDUtil.getUUID();
        checkRectificationReceipt.setId(id);
        boolean result = iCheckRectificationReceiptService.save(checkRectificationReceipt);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param checkRectificationReceipt
     * @return
     */
    @RequestMapping(value = "/checkRectificationReceipt",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckRectificationReceipt(@RequestBody CheckRectificationReceipt checkRectificationReceipt){
        boolean result = iCheckRectificationReceiptService.updateById(checkRectificationReceipt);
        if (result){
            return renderSuccess("修改成功");
        }else {
            return renderError("修改失败");
        }
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/checkRectificationReceipt",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckRectificationReceipt(String id){
        boolean result = iCheckRectificationReceiptService.removeById(id);
        if (result){
            return renderSuccess("删除成功");
        }else {
            return renderError("删除失败");
        }
    }

    /**
     * 通过ID查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/checkRectificationReceipt",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckRectificationReceiptById(String id){
        CheckRectificationReceipt checkRectificationReceipt = iCheckRectificationReceiptService.getById(id);
        if(checkRectificationReceipt!=null){
            return renderSuccess("查询成功",checkRectificationReceipt);
        }else {
            return renderError("无数据");
        }
    }
}

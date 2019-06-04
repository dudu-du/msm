package com.safety.controller;


import com.github.pagehelper.PageInfo;
import com.safety.entity.CheckRectificationReceipt;
import com.safety.service.ICheckRectificationReceiptService;
import com.safety.tools.BaseController;
import com.safety.tools.BaseModelAndView;
import com.safety.tools.JsonResult;
import com.safety.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

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
        checkRectificationReceipt.setCreateTime(LocalDateTime.now());
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

    /**
     * 分页查询清单
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/checkRectificationReceiptByPage",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckRectificationReceiptByPage(@RequestParam(defaultValue="1")Integer currentPage,@RequestParam(defaultValue="10")Integer pageSize){
        PageInfo<CheckRectificationReceipt> page = iCheckRectificationReceiptService.getByPage(currentPage, pageSize);
        if(page!=null){
            return renderSuccess("查询成功",page);
        }else {
            return renderError("无数据");
        }
    }


    //-----------------------------------------------------页面跳转-----------------------------------------------------
    /**
     * 添加页面
     * @return
     */
    @RequestMapping(value = "/checkRectificationReceiptAdd",method = RequestMethod.GET)
    public BaseModelAndView getCheckRectificationReceiptAdd(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/rectificationReceiptAdd");
        return modelAndView;
    }

    /**
     * 修改页面
     * @return
     */
    @RequestMapping(value = "/checkRectificationReceiptEdit",method = RequestMethod.GET)
    public BaseModelAndView getCheckRectificationReceiptEdit(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/rectificationReceiptEdit");
        return modelAndView;
    }

    /**
     * 分页查询页面
     * @return
     */
    @RequestMapping(value = "/checkRectificationReceiptPage",method = RequestMethod.GET)
    public BaseModelAndView getCheckRectificationReceiptPage(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/rectificationReceiptPage");
        return modelAndView;
    }

    /**
     * 打印页面
     * @return
     */
    @RequestMapping(value = "/checkRectificationReceiptPrint",method = RequestMethod.GET)
    public BaseModelAndView getCheckRectificationReceiptPrint(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/rectificationReceiptPrint");
        return modelAndView;
    }

    //------------------------------------------------------------------------------------------------------------------
}

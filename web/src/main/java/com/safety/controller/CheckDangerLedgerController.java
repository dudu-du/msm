package com.safety.controller;


import com.github.pagehelper.PageInfo;
import com.safety.entity.CheckDangerLedger;
import com.safety.service.ICheckDangerLedgerService;
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
 * 隐患治理信息台账 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkDangerLedger")
public class CheckDangerLedgerController extends BaseController {
    @Autowired
    private ICheckDangerLedgerService iCheckDangerLedgerService;
    /**
     * 添加
     * @param checkDangerLedger
     * @return
     */
    @RequestMapping(value = "/checkDangerLedger",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckDangerLedger(@RequestBody CheckDangerLedger checkDangerLedger){
        String id = UUIDUtil.getUUID();
        checkDangerLedger.setId(id);
        checkDangerLedger.setCreateTime(LocalDateTime.now());
        boolean result = iCheckDangerLedgerService.save(checkDangerLedger);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param checkDangerLedger
     * @return
     */
    @RequestMapping(value = "/checkDangerLedger",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckDangerLedger(@RequestBody CheckDangerLedger checkDangerLedger){
        boolean result = iCheckDangerLedgerService.updateById(checkDangerLedger);
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
    @RequestMapping(value = "/checkDangerLedger",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckDangerLedger(String id){
        boolean result = iCheckDangerLedgerService.removeById(id);
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
    @RequestMapping(value = "/checkDangerLedger",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckDangerLedgerById(String id){
        CheckDangerLedger checkDangerLedger = iCheckDangerLedgerService.getById(id);
        if(checkDangerLedger!=null){
            return renderSuccess("查询成功",checkDangerLedger);
        }else {
            return renderError("无数据");
        }
    }

    /**
     * 分页查询台账
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/checkDangerLedgerByPage",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckDangerLedgerByPage(@RequestParam(defaultValue="1")Integer currentPage,@RequestParam(defaultValue="10")Integer pageSize){
        PageInfo<CheckDangerLedger> page = iCheckDangerLedgerService.getByPage(currentPage, pageSize);
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
    @RequestMapping(value = "/checkDangerLedgerAdd",method = RequestMethod.GET)
    public BaseModelAndView getCheckDangerLedgerAdd(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/dangerLedgerAdd");
        return modelAndView;
    }

    /**
     * 修改页面
     * @return
     */
    @RequestMapping(value = "/checkDangerLedgerUpdate",method = RequestMethod.GET)
    public BaseModelAndView getCheckDangerLedgerUpdate(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/dangerLedgerUpdate");
        return modelAndView;
    }

    /**
     * 分页查询页面
     * @return
     */
    @RequestMapping(value = "/checkDangerLedgerPage",method = RequestMethod.GET)
    public BaseModelAndView getCheckDangerLedgerPage(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/dangerLedgerPage");
        return modelAndView;
    }

    //------------------------------------------------------------------------------------------------------------------
}

package com.safety.controller;


import com.safety.entity.CheckDangerLedger;
import com.safety.service.ICheckDangerLedgerService;
import com.safety.tools.BaseController;
import com.safety.tools.JsonResult;
import com.safety.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public JsonResult addCheckDangerLedger(CheckDangerLedger checkDangerLedger){
        String id = UUIDUtil.getUUID();
        checkDangerLedger.setId(id);
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
    public JsonResult updateCheckDangerLedger(CheckDangerLedger checkDangerLedger){
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
}

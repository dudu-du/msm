package com.safety.controller;


import com.safety.entity.RiskDict;
import com.safety.service.IRiskDictService;
import com.safety.tools.BaseController;
import com.safety.tools.BaseModelAndView;
import com.safety.tools.JsonResult;
import com.safety.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 风险相关数据字典 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/riskDict")
public class RiskDictController extends BaseController {
    @Autowired
    private IRiskDictService iRiskDictService;
    /**
     * 添加
     * @param riskDict
     * @return
     */
    @RequestMapping(value = "/riskDict",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addRiskDict(@RequestBody RiskDict riskDict){
        String id = UUIDUtil.getUUID();
        riskDict.setId(id);
        boolean result = iRiskDictService.save(riskDict);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param riskDict
     * @return
     */
    @RequestMapping(value = "/riskDict",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateRiskDict(@RequestBody RiskDict riskDict){
        boolean result = iRiskDictService.updateById(riskDict);
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
    @RequestMapping(value = "/riskDict",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteRiskDict(String id){
        boolean result = iRiskDictService.removeById(id);
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
    @RequestMapping(value = "/riskDict",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getRiskDictById(String id){
        RiskDict riskDict = iRiskDictService.getById(id);
        if(riskDict!=null){
            return renderSuccess("查询成功",riskDict);
        }else {
            return renderError("无数据");
        }
    }

    /**
     * 通过code查询列表
     * @param code
     * @return
     */
    @RequestMapping(value = "/riskDictList",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getRiskDictListByCode(String code){
        List<RiskDict> riskDictList = iRiskDictService.getRiskDictListByCode(code);
        if(riskDictList!=null){
            return renderSuccess("查询成功",riskDictList);
        }else {
            return renderError("无数据");
        }
    }

    /**
     * 通过code查询列表
     * @param code
     * @return
     */
    @RequestMapping(value = "/riskDictCodeList",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getRiskDictCodeList(){
        List<RiskDict> riskDictList = iRiskDictService.getRiskDictCodeList();
        if(riskDictList!=null){
            return renderSuccess("查询成功",riskDictList);
        }else {
            return renderError("无数据");
        }
    }

    //-----------------------------------------------------页面跳转-----------------------------------------------------
    /**
     * 添加页面
     * @return
     */
    @RequestMapping(value = "/riskDictAdd",method = RequestMethod.GET)
    public BaseModelAndView getRiskDictAdd(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("risk/dictAdd");
        return modelAndView;
    }

    /**
     * 修改页面
     * @return
     */
    @RequestMapping(value = "/riskDictEdit",method = RequestMethod.GET)
    public BaseModelAndView getRiskDictEdit(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("risk/dictEdit");
        return modelAndView;
    }

    /**
     * 分页查询页面
     * @return
     */
    @RequestMapping(value = "/riskDictPage",method = RequestMethod.GET)
    public BaseModelAndView getRiskDictPage(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("risk/dictPage");
        return modelAndView;
    }

    //------------------------------------------------------------------------------------------------------------------
}

package com.safety.controller;


import com.safety.entity.RiskDict;
import com.safety.service.IRiskDictService;
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
    public JsonResult addRiskDict(RiskDict riskDict){
        String id = UUIDUtil.getUUID();
        riskDict.setId(id);
        boolean result = iRiskDictService.save(riskDict);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderSuccess("添加失败");
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
    public JsonResult updateRiskDict(RiskDict riskDict){
        boolean result = iRiskDictService.updateById(riskDict);
        if (result){
            return renderSuccess("修改成功");
        }else {
            return renderSuccess("修改失败");
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
            return renderSuccess("删除失败");
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
            return renderSuccess("无数据");
        }
    }
}

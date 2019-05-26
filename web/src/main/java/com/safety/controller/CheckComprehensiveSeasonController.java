package com.safety.controller;


import com.safety.entity.CheckComprehensiveSeason;
import com.safety.service.ICheckComprehensiveSeasonService;
import com.safety.tools.BaseController;
import com.safety.tools.JsonResult;
import com.safety.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 综合检查(季节性) 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkComprehensiveSeason")
public class CheckComprehensiveSeasonController extends BaseController {
    @Autowired
    private ICheckComprehensiveSeasonService iCheckComprehensiveSeasonService;
    /**
     * 添加
     * @param checkComprehensiveSeason
     * @return
     */
    @RequestMapping(value = "/checkComprehensiveSeason",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckComprehensiveSeason(@RequestBody CheckComprehensiveSeason checkComprehensiveSeason){
        boolean result = iCheckComprehensiveSeasonService.addCheckComprehensiveSeason(checkComprehensiveSeason);
        if (result){
            return renderSuccess("添加成功");
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param checkComprehensiveSeason
     * @return
     */
    @RequestMapping(value = "/checkComprehensiveSeason",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckComprehensiveSeason(@RequestBody CheckComprehensiveSeason checkComprehensiveSeason){
        boolean result = iCheckComprehensiveSeasonService.updateById(checkComprehensiveSeason);
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
    @RequestMapping(value = "/checkComprehensiveSeason",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckComprehensiveSeason(String id){
        boolean result = iCheckComprehensiveSeasonService.removeById(id);
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
    @RequestMapping(value = "/checkComprehensiveSeasonById",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckComprehensiveSeasonById(String id){
        CheckComprehensiveSeason checkComprehensiveSeason = iCheckComprehensiveSeasonService.getById(id);
        if(checkComprehensiveSeason!=null){
            return renderSuccess("查询成功",checkComprehensiveSeason);
        }else {
            return renderError("无数据");
        }
    }

    /**
     * 根据日期和机构名称查询
     * @param orgId
     * @param year
     * @return
     */
    @RequestMapping(value = "/checkComprehensiveSeason",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckComprehensiveSeasonByParam(String orgId, String year){
        CheckComprehensiveSeason checkComprehensiveSeason = iCheckComprehensiveSeasonService.getByParam(orgId,year);
        if(checkComprehensiveSeason!=null){
            return renderSuccess("查询成功",checkComprehensiveSeason);
        }else {
            return renderError("无数据");
        }
    }
}

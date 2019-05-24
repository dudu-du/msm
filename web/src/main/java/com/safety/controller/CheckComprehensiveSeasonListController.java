package com.safety.controller;


import com.safety.entity.CheckComprehensiveSeasonList;
import com.safety.service.ICheckComprehensiveSeasonListService;
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
 * 综合检查(季节性)列表 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkComprehensiveSeasonList")
public class CheckComprehensiveSeasonListController extends BaseController {
    @Autowired
    private ICheckComprehensiveSeasonListService iCheckComprehensiveSeasonListService;
    /**
     * 添加
     * @param checkComprehensiveSeasonList
     * @return
     */
    @RequestMapping(value = "/checkComprehensiveSeasonList",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckComprehensiveSeasonList(CheckComprehensiveSeasonList checkComprehensiveSeasonList){
        String id = UUIDUtil.getUUID();
        checkComprehensiveSeasonList.setId(id);
        boolean result = iCheckComprehensiveSeasonListService.save(checkComprehensiveSeasonList);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderSuccess("添加失败");
        }
    }

    /**
     * 修改
     * @param checkComprehensiveSeasonList
     * @return
     */
    @RequestMapping(value = "/checkComprehensiveSeasonList",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckComprehensiveSeasonList(CheckComprehensiveSeasonList checkComprehensiveSeasonList){
        boolean result = iCheckComprehensiveSeasonListService.updateById(checkComprehensiveSeasonList);
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
    @RequestMapping(value = "/checkComprehensiveSeasonList",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckComprehensiveSeasonList(String id){
        boolean result = iCheckComprehensiveSeasonListService.removeById(id);
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
    @RequestMapping(value = "/checkComprehensiveSeasonList",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckComprehensiveSeasonListById(String id){
        CheckComprehensiveSeasonList checkComprehensiveSeasonList = iCheckComprehensiveSeasonListService.getById(id);
        if(checkComprehensiveSeasonList!=null){
            return renderSuccess("查询成功",checkComprehensiveSeasonList);
        }else {
            return renderSuccess("无数据");
        }
    }
}

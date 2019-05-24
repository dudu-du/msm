package com.safety.controller;


import com.safety.entity.SafetyNotificationCardList;
import com.safety.service.ISafetyNotificationCardListService;
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
 * 岗位安全风险告知卡列表 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/safetyNotificationCardList")
public class SafetyNotificationCardListController extends BaseController {
    @Autowired
    private ISafetyNotificationCardListService iSafetyNotificationCardListService;
    /**
     * 添加
     * @param safetyNotificationCardList
     * @return
     */
    @RequestMapping(value = "/safetyNotificationCardList",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addSafetyNotificationCardList(SafetyNotificationCardList safetyNotificationCardList){
        String id = UUIDUtil.getUUID();
        safetyNotificationCardList.setId(id);
        boolean result = iSafetyNotificationCardListService.save(safetyNotificationCardList);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderSuccess("添加失败");
        }
    }

    /**
     * 修改
     * @param safetyNotificationCardList
     * @return
     */
    @RequestMapping(value = "/safetyNotificationCardList",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateSafetyNotificationCardList(SafetyNotificationCardList safetyNotificationCardList){
        boolean result = iSafetyNotificationCardListService.updateById(safetyNotificationCardList);
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
    @RequestMapping(value = "/safetyNotificationCardList",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteSafetyNotificationCardList(String id){
        boolean result = iSafetyNotificationCardListService.removeById(id);
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
    @RequestMapping(value = "/safetyNotificationCardList",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getSafetyNotificationCardListById(String id){
        SafetyNotificationCardList safetyNotificationCardList = iSafetyNotificationCardListService.getById(id);
        if(safetyNotificationCardList!=null){
            return renderSuccess("查询成功",safetyNotificationCardList);
        }else {
            return renderSuccess("无数据");
        }
    }
}

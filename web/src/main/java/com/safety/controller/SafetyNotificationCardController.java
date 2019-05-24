package com.safety.controller;


import com.safety.entity.SafetyNotificationCard;
import com.safety.service.ISafetyNotificationCardService;
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
 * 岗位安全风险告知卡 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/safetyNotificationCard")
public class SafetyNotificationCardController extends BaseController {
    @Autowired
    private ISafetyNotificationCardService iSafetyNotificationCardService;
    /**
     * 添加
     * @param safetyNotificationCard
     * @return
     */
    @RequestMapping(value = "/safetyNotificationCard",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addSafetyNotificationCard(SafetyNotificationCard safetyNotificationCard){
        String id = UUIDUtil.getUUID();
        safetyNotificationCard.setId(id);
        boolean result = iSafetyNotificationCardService.save(safetyNotificationCard);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderSuccess("添加失败");
        }
    }

    /**
     * 修改
     * @param safetyNotificationCard
     * @return
     */
    @RequestMapping(value = "/safetyNotificationCard",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateSafetyNotificationCard(SafetyNotificationCard safetyNotificationCard){
        boolean result = iSafetyNotificationCardService.updateById(safetyNotificationCard);
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
    @RequestMapping(value = "/safetyNotificationCard",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteSafetyNotificationCard(String id){
        boolean result = iSafetyNotificationCardService.removeById(id);
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
    @RequestMapping(value = "/safetyNotificationCard",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getSafetyNotificationCardById(String id){
        SafetyNotificationCard safetyNotificationCard = iSafetyNotificationCardService.getById(id);
        if(safetyNotificationCard!=null){
            return renderSuccess("查询成功",safetyNotificationCard);
        }else {
            return renderSuccess("无数据");
        }
    }
}

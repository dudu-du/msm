package com.safety.controller;


import com.safety.entity.SafetyNotificationCard;
import com.safety.service.ISafetyNotificationCardService;
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
    public JsonResult addSafetyNotificationCard(@RequestBody SafetyNotificationCard safetyNotificationCard){
        String id = UUIDUtil.getUUID();
        safetyNotificationCard.setId(id);
        safetyNotificationCard.setCreateTime(LocalDateTime.now());
        boolean result = iSafetyNotificationCardService.save(safetyNotificationCard);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderError("添加失败");
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
    public JsonResult updateSafetyNotificationCard(@RequestBody SafetyNotificationCard safetyNotificationCard){
        boolean result = iSafetyNotificationCardService.updateById(safetyNotificationCard);
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
    @RequestMapping(value = "/safetyNotificationCard",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteSafetyNotificationCard(String id){
        boolean result = iSafetyNotificationCardService.removeById(id);
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
    @RequestMapping(value = "/safetyNotificationCard",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getSafetyNotificationCardById(String id){
        SafetyNotificationCard safetyNotificationCard = iSafetyNotificationCardService.getById(id);
        if(safetyNotificationCard!=null){
            return renderSuccess("查询成功",safetyNotificationCard);
        }else {
            return renderError("无数据");
        }
    }

    //-----------------------------------------------------页面跳转-----------------------------------------------------
    /**
     * 添加页面
     * @return
     */
    @RequestMapping(value = "/safetyNotificationCardAdd",method = RequestMethod.GET)
    public BaseModelAndView getSafetyNotificationCardAdd(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("safety/notificationCardAdd");
        return modelAndView;
    }

    /**
     * 修改页面
     * @return
     */
    @RequestMapping(value = "/safetyNotificationCardEdit",method = RequestMethod.GET)
    public BaseModelAndView getSafetyNotificationCardEdit(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("safety/notificationCardEdit");
        return modelAndView;
    }

    /**
     * 分页查询页面
     * @return
     */
    @RequestMapping(value = "/safetyNotificationCardPage",method = RequestMethod.GET)
    public BaseModelAndView getSafetyNotificationCardPage(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("safety/notificationCardPage");
        return modelAndView;
    }


    /**
     * 打印页面跳转
     * @return
     */
    @RequestMapping(value = "/safetyNotificationCardPrint",method = RequestMethod.GET)
    public BaseModelAndView getSafetyNotificationCardPrint(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("safety/notificationCardPrint");
        return modelAndView;
    }

    //------------------------------------------------------------------------------------------------------------------
}

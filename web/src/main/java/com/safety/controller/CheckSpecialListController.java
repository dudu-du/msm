package com.safety.controller;


import com.safety.entity.CheckSpecialList;
import com.safety.service.ICheckSpecialListService;
import com.safety.tools.BaseController;
import com.safety.tools.BaseModelAndView;
import com.safety.tools.JsonResult;
import com.safety.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 专项检查列表 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkSpecialList")
public class CheckSpecialListController extends BaseController {
    @Autowired
    private ICheckSpecialListService iCheckSpecialListService;
    /**
     * 添加
     * @param checkSpecialList
     * @return
     */
    @RequestMapping(value = "/checkSpecialList",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckSpecialList(@RequestBody CheckSpecialList checkSpecialList){
        String id = UUIDUtil.getUUID();
        checkSpecialList.setId(id);
        boolean result = iCheckSpecialListService.save(checkSpecialList);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param checkSpecialList
     * @return
     */
    @RequestMapping(value = "/checkSpecialList",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckSpecialList(@RequestBody CheckSpecialList checkSpecialList){
        boolean result = iCheckSpecialListService.updateById(checkSpecialList);
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
    @RequestMapping(value = "/checkSpecialList",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckSpecialList(String id){
        boolean result = iCheckSpecialListService.removeById(id);
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
    @RequestMapping(value = "/checkSpecialList",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckSpecialListById(String id){
        CheckSpecialList checkSpecialList = iCheckSpecialListService.getById(id);
        if(checkSpecialList!=null){
            return renderSuccess("查询成功",checkSpecialList);
        }else {
            return renderError("无数据");
        }
    }

    //-----------------------------------------------------页面跳转-----------------------------------------------------
    /**
     * 添加页面
     * @return
     */
    @RequestMapping(value = "/checkSpecialListAdd",method = RequestMethod.GET)
    public BaseModelAndView getCheckSpecialListAdd(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/specialListAdd");
        return modelAndView;
    }

    /**
     * 修改页面
     * @return
     */
    @RequestMapping(value = "/checkSpecialListUpdate",method = RequestMethod.GET)
    public BaseModelAndView getCheckSpecialListUpdate(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/specialListUpdate");
        return modelAndView;
    }

    /**
     * 分页查询页面
     * @return
     */
    @RequestMapping(value = "/checkSpecialListPage",method = RequestMethod.GET)
    public BaseModelAndView getCheckSpecialListPage(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/specialListPage");
        return modelAndView;
    }

    //------------------------------------------------------------------------------------------------------------------
}

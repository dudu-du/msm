package com.safety.controller;


import com.github.pagehelper.PageInfo;
import com.safety.entity.CheckSpecialRecord;
import com.safety.service.ICheckSpecialRecordService;
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
 * 月排查记录填写 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkSpecialRecord")
public class CheckSpecialRecordController extends BaseController {
    @Autowired
    private ICheckSpecialRecordService iCheckSpecialRecordService;
    /**
     * 添加
     * @param checkSpecialRecord
     * @return
     */
    @RequestMapping(value = "/checkSpecialRecord",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckSpecialRecord(@RequestBody CheckSpecialRecord checkSpecialRecord){
        boolean result = iCheckSpecialRecordService.addCheckSpecialRecord(checkSpecialRecord);
        if (result){
            return renderSuccess("添加成功",result);
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param checkSpecialRecord
     * @return
     */
    @RequestMapping(value = "/checkSpecialRecord",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckSpecialRecord(@RequestBody CheckSpecialRecord checkSpecialRecord){
        boolean result = iCheckSpecialRecordService.updateById(checkSpecialRecord);
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
    @RequestMapping(value = "/checkSpecialRecord",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckSpecialRecord(String id){
        boolean result = iCheckSpecialRecordService.removeById(id);
        if (result){
            return renderSuccess("删除成功");
        }else {
            return renderError("删除失败");
        }
    }

    /**
     * 通过ID查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/checkSpecialRecordById",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckSpecialRecordById(String id){
        CheckSpecialRecord checkSpecialRecord = iCheckSpecialRecordService.getById(id);
        if(checkSpecialRecord!=null){
            return renderSuccess("查询成功",checkSpecialRecord);
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
    @RequestMapping(value = "/checkSpecialRecord",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckSpecialRecordByParam(String orgId, String year){
        CheckSpecialRecord checkSpecialRecord = iCheckSpecialRecordService.getByParam(orgId,year);
        if(checkSpecialRecord!=null){
            return renderSuccess("查询成功",checkSpecialRecord);
        }else {
            return renderError("无数据");
        }
    }

    /**
     * 分页查询月记录
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/checkSpecialRecordByPage",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckSpecialRecordByPage(@RequestParam(defaultValue="1")Integer currentPage,@RequestParam(defaultValue="10")Integer pageSize){
        PageInfo<CheckSpecialRecord> page = iCheckSpecialRecordService.getByPage(currentPage, pageSize);
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
    @RequestMapping(value = "/checkSpecialRecordAdd",method = RequestMethod.GET)
    public BaseModelAndView getCheckSpecialRecordAdd(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/specialRecordAdd");
        return modelAndView;
    }

    /**
     * 修改页面
     * @return
     */
    @RequestMapping(value = "/checkSpecialRecordEdit",method = RequestMethod.GET)
    public BaseModelAndView getCheckSpecialRecordEdit(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/specialRecordEdit");
        return modelAndView;
    }

    /**
     * 分页查询页面
     * @return
     */
    @RequestMapping(value = "/checkSpecialRecordPage",method = RequestMethod.GET)
    public BaseModelAndView getCheckSpecialRecordPage(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/specialRecordPage");
        return modelAndView;
    }

    /**
     * 打印页面
     * @return
     */
    @RequestMapping(value = "/checkSpecialRecordPrint",method = RequestMethod.GET)
    public BaseModelAndView getCheckSpecialRecordPrint(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/specialRecordPrint");
        return modelAndView;
    }

    //------------------------------------------------------------------------------------------------------------------
}

package com.safety.controller;


import com.github.pagehelper.PageInfo;
import com.safety.entity.CheckSeasonRecord;
import com.safety.service.ICheckSeasonRecordService;
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
 * 综合季度排查记录填写 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkSeasonRecord")
public class CheckSeasonRecordController extends BaseController {
    @Autowired
    private ICheckSeasonRecordService iCheckSeasonRecordService;
    /**
     * 添加
     * @param checkSeasonRecord
     * @return
     */
    @RequestMapping(value = "/checkSeasonRecord",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckSeasonRecord(@RequestBody CheckSeasonRecord checkSeasonRecord){
        boolean result = iCheckSeasonRecordService.addCheckSeasonRecord(checkSeasonRecord);
        if (result){
            return renderSuccess("添加成功",checkSeasonRecord);
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param checkSeasonRecord
     * @return
     */
    @RequestMapping(value = "/checkSeasonRecord",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckSeasonRecord(@RequestBody CheckSeasonRecord checkSeasonRecord){
        boolean result = iCheckSeasonRecordService.updateById(checkSeasonRecord);
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
    @RequestMapping(value = "/checkSeasonRecord",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckSeasonRecord(String id){
        boolean result = iCheckSeasonRecordService.removeById(id);
        if (result){
            return renderSuccess("删除成功");
        }else {
            return renderError("删除失败");
        }
    }

    /**
     * 通过ID查询b'
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/checkSeasonRecordById",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckSeasonRecordById(String id){
        CheckSeasonRecord checkSeasonRecord = iCheckSeasonRecordService.getById(id);
        if(checkSeasonRecord!=null){
            return renderSuccess("查询成功",checkSeasonRecord);
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
    @RequestMapping(value = "/checkSeasonRecord",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckSeasonRecordByParam(String orgId, String year){
        CheckSeasonRecord checkSeasonRecord = iCheckSeasonRecordService.getByParam(orgId,year);
        if(checkSeasonRecord!=null){
            return renderSuccess("查询成功",checkSeasonRecord);
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
    @RequestMapping(value = "/checkSeasonRecordByPage",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckSeasonRecordByPage(@RequestParam(defaultValue="1")Integer currentPage,@RequestParam(defaultValue="10")Integer pageSize){
        PageInfo<CheckSeasonRecord> page = iCheckSeasonRecordService.getByPage(currentPage, pageSize);
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
    @RequestMapping(value = "/checkSeasonRecordAdd",method = RequestMethod.GET)
    public BaseModelAndView getCheckSeasonRecordAdd(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/seasonRecordAdd");
        return modelAndView;
    }

    /**
     * 修改页面
     * @return
     */
    @RequestMapping(value = "/checkSeasonRecordEdit",method = RequestMethod.GET)
    public BaseModelAndView getCheckSeasonRecordEdit(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/seasonRecordEdit");
        return modelAndView;
    }

    /**
     * 分页查询页面
     * @return
     */
    @RequestMapping(value = "/checkSeasonRecordPage",method = RequestMethod.GET)
    public BaseModelAndView getCheckSeasonRecordPage(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/seasonRecordPage");
        return modelAndView;
    }


    /**
     * 打印页面
     * @return
     */
    @RequestMapping(value = "/checkSeasonRecordPrint",method = RequestMethod.GET)
    public BaseModelAndView getCheckSeasonRecordPrint(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/seasonRecordPrint");
        return modelAndView;
    }

    //------------------------------------------------------------------------------------------------------------------
}

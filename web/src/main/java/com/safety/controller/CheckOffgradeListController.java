package com.safety.controller;


import com.github.pagehelper.PageInfo;
import com.safety.entity.CheckDay;
import com.safety.entity.CheckOffgradeList;
import com.safety.service.ICheckDayService;
import com.safety.service.ICheckOffgradeListService;
import com.safety.tools.BaseController;
import com.safety.tools.BaseModelAndView;
import com.safety.tools.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 检查结果为否 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkOffgradeList")
public class CheckOffgradeListController extends BaseController {

    @Autowired
    private ICheckOffgradeListService iCheckOffgradeListService;
    /**
     * 分页查询月记录
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/checkOffgradeListByPage",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckOffgradeListByPage(@RequestParam(defaultValue="1")Integer currentPage,@RequestParam(defaultValue="10")Integer pageSize,@RequestParam(defaultValue="")String orgId){
        PageInfo<CheckOffgradeList> page = iCheckOffgradeListService.getByPage(currentPage, pageSize, orgId);
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
    @RequestMapping(value = "/checkOffgradeListAdd",method = RequestMethod.GET)
    public BaseModelAndView getCheckOffgradeListAdd(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/offgradeListAdd");
        return modelAndView;
    }

    /**
     * 修改页面
     * @return
     */
    @RequestMapping(value = "/checkOffgradeListEdit",method = RequestMethod.GET)
    public BaseModelAndView getCheckOffgradeListEdit(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/offgradeListEdit");
        return modelAndView;
    }

    /**
     * 分页查询页面
     * @return
     */
    @RequestMapping(value = "/checkOffgradeListPage",method = RequestMethod.GET)
    public BaseModelAndView getCheckOffgradeListPage(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/offgradeListPage");
        return modelAndView;
    }

    //------------------------------------------------------------------------------------------------------------------
}

package com.welsee.apicontroller;

import com.welsee.dto.ClassInfoResult;
import com.welsee.dto.EvaluationGradleResult;
import com.welsee.dto.user.PublicPersonParams;
import com.welsee.entity.*;
import com.welsee.entity.Class;
import com.welsee.excel.util.StringUtils;
import com.welsee.exception.ProgramException;
import com.welsee.extentity.ExSubjectTeacher;
import com.welsee.extentity.ExtSensitive;
import com.welsee.extentity.ExtWxInfo;
import com.welsee.service.*;
import com.welsee.shiro.AuthRealm;
import com.welsee.tools.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


/**
 * 对外接口
 */
@Controller
@Slf4j
@CrossOrigin
@RequestMapping("/Public")
public class PublicController extends BaseController {
    @Autowired
    private IAccessTokenService accessTokenService;
    @Autowired
    private IOrgService orgService;

    /**
     * 根据类型获取所有机构列表
     *
     * @return JsonResult
     */
    @RequestMapping(value = "/OrgList", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getAllOrgListByType(String token, String orgType) {
        try {
            if (accessTokenService.validateOAuth2Token(token, DictConstants.TOKEN_ALL)) {
                List orgList = orgService.getAllOrgListByType(orgType);
                log.info("获取机构列表：" + orgList);
                return renderSuccess("机构列表成功", orgList);
            }
        } catch (ProgramException e) {
            return renderError(e.getMessage());
        } catch (Exception e) {
            log.error("获取机构列表失败." + e.getMessage());
            return renderError("获取机构列表失败");
        }
        return renderError("获取机构列表失败");
    }

    /**
     * 第三方应用验证token是否有效
     *
     * @param token
     * @return
     */
    @RequestMapping(value = "token", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult tokenVerification(String token) {
        try {
            if (accessTokenService.validateOAuth2Token(token, DictConstants.TOKEN_ALL)) {
                return renderSuccess("token有效");
            } else {
                return renderError("无效token");
            }
        } catch (ProgramException e) {
            return renderError(e.getMessage());
        } catch (Exception e) {
            log.error("[公共接口]验证token失败", e);
            return renderError(e.getMessage());
        }
    }
}


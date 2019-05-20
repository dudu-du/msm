package com.welsee.controller;

import com.welsee.service.IAccessCodeService;
import com.welsee.service.ILoginService;
import com.welsee.service.IPersonService;
import com.welsee.tools.BaseController;
import com.welsee.tools.BaseModelAndView;
import com.welsee.tools.RegexUtil;
import com.welsee.tools.UUIDUtil;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;

/**
 *
 */
@Controller
@RequestMapping("/oauth2")
@CrossOrigin
public class AuthzController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(AuthzController.class);

    @Autowired
    private ILoginService loginService;

    @Autowired
    private IAccessCodeService accessCodeService;

    @Autowired
    private IPersonService personService;

    //http://localhost:8080/oauth2/authorize?response_type=code&client_id=CLIENT001&redirect_uri=http://www.baidu.com
    @RequestMapping(value = "/authorize", method = RequestMethod.GET)
    public BaseModelAndView authorize(HttpServletRequest request, HttpSession session, BaseModelAndView bmv) throws OAuthSystemException {
        try {
            if (bmv == null) {
                bmv = new BaseModelAndView();
            }

            //构建OAuth请求
            OAuthAuthzRequest oAuthAuthzRequest = new OAuthAuthzRequest(request);
            //验证redirecturl格式是否合法
            if (!RegexUtil.isUrl(oAuthAuthzRequest.getRedirectURI())) {
                OAuthResponse oAuthResponse = OAuthASResponse
                        .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                        .setError(OAuthError.CodeResponse.INVALID_REQUEST)
                        .setErrorDescription(OAuthError.OAUTH_ERROR_URI)
                        .buildJSONMessage();
                bmv.setViewName("authz/error");
                bmv.addObject("errorMsg", oAuthResponse.getBody());
                return bmv;
            }

            bmv.addObject("response_type", oAuthAuthzRequest.getResponseType());
            bmv.addObject("client_id", oAuthAuthzRequest.getClientId());
            bmv.addObject("redirect_uri", oAuthAuthzRequest.getRedirectURI());
            bmv.addObject("scope", oAuthAuthzRequest.getScopes());
            bmv.addObject("picture_uuid", UUIDUtil.getUUID());

            //验证用户是否已登录 如果未登录
            if (session.getAttribute("MEMBER_USER_KEY") == null) {
                //跳到登录界面
                bmv.setViewName("welsee/login");
                return bmv;
            }
            //生成授权码 UUIDValueGenerator OR MD5Generator
            String uId = session.getAttribute("MEMBER_USER_KEY").toString();
            String authorizationCode = new OAuthIssuerImpl(new MD5Generator()).authorizationCode();
            Date dt = new Date();
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            rightNow.add(Calendar.MINUTE, 10);
            String expiresin = Long.toString(rightNow.getTimeInMillis());

            try {
                accessCodeService.addAccessCode(oAuthAuthzRequest.getClientId(), uId, expiresin, authorizationCode);
            } catch (Exception ex) {
                bmv.setViewName("welsee/login");
                bmv.addObject("errorMsg", ex.getMessage());
                logger.error("保存Code异常," + ex.getMessage());
                return bmv;
            }
            //把授权码存入缓存h(
            //构建oauth2授权返回信息
            OAuthResponse oAuthResponse = OAuthASResponse
                    .authorizationResponse(request, HttpServletResponse.SC_FOUND)
                    .setCode(authorizationCode)
                    .location(oAuthAuthzRequest.getParam(OAuth.OAUTH_REDIRECT_URI))
                    .buildQueryMessage();
            bmv = new BaseModelAndView("redirect:" + oAuthResponse.getLocationUri());
            return bmv;
        } catch (OAuthProblemException oa) {
            OAuthResponse oauthResponse = OAuthResponse
                    .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                    .error(oa)
                    .buildJSONMessage();
            bmv.setViewName("authz/error");
            bmv.addObject("errorMsg", oauthResponse.getBody());
            logger.error("/authorize请求异常," + oa.getMessage());
            return bmv;
        } catch (Exception e) {
            bmv.setViewName("authz/error");
            bmv.addObject("errorMsg", e.getMessage());
            logger.error("/authorize系统异常," + e.getMessage());
            return bmv;
        }
    }
}

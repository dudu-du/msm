package com.welsee.controller;

import com.welsee.entity.AccessCode;
import com.welsee.entity.Login;
import com.welsee.service.IAccessCodeService;
import com.welsee.service.IAccessTokenService;
import com.welsee.service.ILoginService;
import com.welsee.tools.BaseController;
import com.welsee.tools.DictConstants;
import com.welsee.tools.EncryptionUtil;
import com.welsee.tools.JsonResult;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


@RestController
@RequestMapping("/oauth2")
@CrossOrigin
public class TokenController  extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(TokenController.class);

    @Autowired
    private IAccessCodeService accessCodeService;

    @Autowired
    private IAccessTokenService accessTokenService;

    @Autowired
    private ILoginService loginService;

    /**
     * 认证服务器申请令牌(AccessToken) [验证client_id、client_secret、auth code的正确性或更新令牌 refresh_token]
     * @param request
     * @param response
     * @return
     * @url http://localhost:8080/oauth2/access_token?client_id={AppKey}&client_secret={AppSecret}&grant_type=authorization_code&redirect_uri={YourSiteUrl}&code={code}
     */
    @RequestMapping(value = "/access_token",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult access_token(HttpServletRequest request, HttpServletResponse response)throws Exception {
        OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
        JsonResult jsonResult = new JsonResult();
        try {
            //构建oauth2请求
            OAuthTokenRequest oauthRequest = new OAuthTokenRequest(request);

            String uId = "";
            //生成token
            final String accessToken = oauthIssuerImpl.accessToken();
            Date dt = new Date();
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            rightNow.add(Calendar.HOUR, 24);//token 过期时间设为24小时
            String expiresin = Long.toString(rightNow.getTimeInMillis());

            //code方式
            //https://www.example.com/v1/oauth/authorize?response_type=code&client_id=CLIENT_ID&redirect_uri=CALLBACK_URL&scope=read&state=xxx
            if (GrantType.AUTHORIZATION_CODE.name().equalsIgnoreCase(oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE))) {

//                //验证redirecturl格式是否合法 (8080端口测试)
//                if (!RegexUtil.isUrl(oauthRequest.getRedirectURI())) {
//                    OAuthResponse oauthResponse = OAuthASResponse
//                            .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
//                            .setError(OAuthError.CodeResponse.INVALID_REQUEST)
//                            .setErrorDescription(OAuthError.OAUTH_ERROR_URI)
//                            .buildJSONMessage();
//                    jsonResult.setSuccess(false);
//                    jsonResult.setMsg(oauthResponse.getBody());
//                    return jsonResult;
//                }

                String authzCode = oauthRequest.getCode();
                //验证AUTHORIZATION_CODE , 其他的还有PASSWORD 或 REFRESH_TOKEN (考虑到更新令牌的问题，在做修改)


                //验证code是否有效
                if (!validateOAuth2Code(oauthRequest.getClientId(),authzCode)){
                    OAuthResponse oauthResponse = OAuthASResponse
                            .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                            .setError(OAuthError.CodeResponse.UNAUTHORIZED_CLIENT)
                            .setErrorDescription(OAuthError.CodeResponse.UNAUTHORIZED_CLIENT)
                            .buildJSONMessage();
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(oauthResponse.getBody());
                    return jsonResult;
                }
                AccessCode accessCode = accessCodeService.validateAccessCode(oauthRequest.getClientId(), authzCode);
                uId = accessCode.getUserId();

                int i = accessTokenService.addToken(oauthRequest.getClientId(), uId, DictConstants.TOKEN_PERSONAL, accessToken, expiresin);
                if(i>0){//生成token删除之前的code
                    accessCodeService.delAccessCode(accessCode.getId());
                }else{
                    OAuthResponse oauthResponse = OAuthASResponse
                            .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                            .setError(OAuthError.TokenResponse.INVALID_GRANT)
                            .setErrorDescription("token生成失败")
                            .buildJSONMessage();
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(oauthResponse.getBody());
                    return jsonResult;
                }
                //构建oauth2授权返回信息
                OAuthResponse oauthResponse = OAuthASResponse
                        .tokenResponse(HttpServletResponse.SC_OK)
                        .setAccessToken(accessToken)
                        .setExpiresIn(expiresin)
                        .buildJSONMessage();
                response.setStatus(oauthResponse.getResponseStatus());
                jsonResult.setSuccess(true);
                HashMap map = new HashMap();
                map.put("userId",uId);
                map.put("token",accessToken);
                map.put("expiresin",expiresin);//失效时间（java时间戳)
                jsonResult.setData(map);
                return jsonResult;

            }
            //用户名密码方式
            //https://www.example.com/token?grant_type=password&username=USERNAME&password=PASSWORD&client_id=CLIENT_ID
            else if(GrantType.PASSWORD.name().equalsIgnoreCase(oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE))){
                Login login = loginService.selectByLoginName(oauthRequest.getUsername());
                if(login != null){
                    String pwd = oauthRequest.getPassword();

                    if(login.getPassword().equals(EncryptionUtil.encrypt(pwd))){

                        uId = login.getId();

                        accessTokenService.addToken(oauthRequest.getClientId(), uId,DictConstants.TOKEN_PERSONAL, accessToken, expiresin);

                        //构建oauth2授权返回信息
                        OAuthResponse oauthResponse = OAuthASResponse
                                .tokenResponse(HttpServletResponse.SC_OK)
                                .setAccessToken(accessToken)
                                .setExpiresIn(expiresin)
                                .buildJSONMessage();
                        response.setStatus(oauthResponse.getResponseStatus());
                        jsonResult.setSuccess(false);
                        jsonResult.setMsg(oauthResponse.getBody());
                        return jsonResult;

                    }
                }
                OAuthResponse oauthResponse = OAuthASResponse
                        .errorResponse(HttpServletResponse.SC_NOT_FOUND)
                        .setError(OAuthError.TokenResponse.INVALID_REQUEST)
                        .setErrorDescription("username or password error")
                        .buildJSONMessage();
                jsonResult.setSuccess(false);
                jsonResult.setMsg(oauthResponse.getBody());
                return jsonResult;
            }
            else{
                OAuthResponse oauthResponse = OAuthASResponse
                        .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                        .setError(OAuthError.TokenResponse.INVALID_GRANT)
                        .setErrorDescription("grant_type is not authorization_code")
                        .buildJSONMessage();
                jsonResult.setSuccess(false);
                jsonResult.setMsg(oauthResponse.getBody());
                return jsonResult;
            }
        } catch(OAuthProblemException ex) {
            OAuthResponse oauthResponse = OAuthResponse
                    .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                    .error(ex)
                    .buildJSONMessage();
            response.setStatus(oauthResponse.getResponseStatus());
            jsonResult.setSuccess(false);
            jsonResult.setMsg(oauthResponse.getBody());
            return jsonResult;
        }
    }

    /**
     * 验证code 是否正确和有效
     * @param clientId
     * @param authzCode
     * @return
     */
    public boolean validateOAuth2Code(String clientId,String authzCode) {
        boolean flag = false;
        try {
            AccessCode accessCode = accessCodeService.validateAccessCode(clientId,authzCode);
            if(accessCode.getId()!=null||accessCode.getId()!=""){
                LocalDateTime localDateTime = LocalDateTime.now();
                LocalDateTime localDateTime1 = accessCode.getCreatedatetime();
                LocalDateTime localDateTime2 = localDateTime1.plusMinutes(10);
                if(localDateTime.isBefore(localDateTime2)){
                    flag = true;
                }
            }
        }
       catch (Exception e)
       {
           flag =false;
       }
        return flag;
    }

    /**
     * 认证服务器申请令牌(AccessToken) [验证client_id、client_secret] 获取系统token
     * @param request
     * @param response
     * @return
     * @url http://localhost:8080/oauth2/access_token?client_id={AppKey}&client_secret={AppSecret}
     */
    @RequestMapping(value = "/access_systoken",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult access_systoken(HttpServletRequest request, HttpServletResponse response) throws OAuthSystemException {
        JsonResult jsonResult = new JsonResult();
        OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());


        try {
            //构建oauth2请求
            String clientId = request.getParameter("client_id");
            String clientSecret = request.getParameter("client_secret");
            //生成token
            final String accessToken = oauthIssuerImpl.accessToken();
            Date dt = new Date();
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            rightNow.add(Calendar.HOUR, 1);//token 过期时间设为1小时
            String expiresin = Long.toString(rightNow.getTimeInMillis());

            accessTokenService.addToken(clientId, "",DictConstants.TOKEN_SYSTEM, accessToken, expiresin);

            //构建oauth2授权返回信息
            OAuthResponse oauthResponse = OAuthASResponse
                    .tokenResponse(HttpServletResponse.SC_OK)
                    .buildJSONMessage();
            response.setStatus(oauthResponse.getResponseStatus());
            jsonResult.setSuccess(true);
            jsonResult.setMsg(oauthResponse.getBody());
            HashMap map = new HashMap();
            map.put("token",accessToken);
            map.put("expiresin",expiresin);
            jsonResult.setData(map);
            return jsonResult;

        } catch(Exception ex) {
            OAuthResponse oauthResponse = OAuthResponse
                    .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                    .error((OAuthProblemException) ex)
                    .buildJSONMessage();
            response.setStatus(oauthResponse.getResponseStatus());
            jsonResult.setSuccess(false);
            jsonResult.setMsg(oauthResponse.getBody());
            return jsonResult;
        }
    }
}

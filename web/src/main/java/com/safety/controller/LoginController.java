package com.safety.controller;

import com.safety.entity.Login;
import com.safety.entity.Org;
import com.safety.entity.Person;
import com.safety.exception.ProgramException;
import com.safety.extentity.ExtPerson;
import com.safety.service.*;
import com.safety.shiro.AuthRealm;
import com.safety.tools.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
public class LoginController extends BaseController {

    @Autowired
    private ILoginService loginService;
    @Autowired
    private IPersonService personService;
    @Autowired
    private ICaptchaService captchaService;
    @Autowired
    private IAccessTokenService accessTokenService;
    @Autowired
    private IOrgService orgService;

    @RequestMapping(value="login",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult Login(HttpServletRequest request){
        Session session = SecurityUtils.getSubject().getSession();
        if(session.getAttribute("MEMBER_USER_KEY")==null) {
            try {
                if (!validateOAuth2Pwd(request,"default")) {
                    return renderError("用户名或密码错误！");
                }
            } catch (ProgramException ex) {
                return renderError(ex.getMessage());
            } catch (Exception ex){
                log.error("登录异常.",ex);
                return renderError("登录异常！");
            }
        }
        return renderSuccess();
    }

    @RequestMapping(value="schoolLogin",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult schoolLogin(HttpServletRequest request){
        Session session = SecurityUtils.getSubject().getSession();
        if(session.getAttribute("MEMBER_USER_KEY")==null) {
            try {
                if (!validateOAuth2Pwd(request,"school")) {
                    return renderError("用户名或密码错误！");
                }
            } catch (ProgramException ex) {
                return renderError(ex.getMessage());
            } catch (Exception ex){
                log.error("登录异常.",ex);
                return renderError("登录异常！");
            }
        }
        return renderSuccess();
    }

    private boolean validateOAuth2Pwd(HttpServletRequest request,String type) throws Exception {
        if ("get".equalsIgnoreCase(request.getMethod())) {
            return false;
        }
        String name = request.getParameter("txtUserName");
        String pwd = request.getParameter("txtPassword");
        if(("default").equals(type)) {
            String code = request.getParameter("txtCode");
            String pictureUuid = request.getParameter("pictureUuid");
//            if (!captchaService.validate(pictureUuid, code)) {
//                throw new ProgramException("验证码不正确!");
//            }
        }
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(pwd)) {
            throw new ProgramException("用户名或密码不可以为空!");
        }

        UsernamePasswordToken token = new UsernamePasswordToken(name, MD5.GetMD5Code(pwd));
//         token.setRememberMe(true);
        // 获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            // 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            // 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            // 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            log.info("对用户[" + name + "]进行登录验证..验证开始");
            currentUser.login(token);
            log.info("对用户[" + name + "]进行登录验证..验证通过");

        } catch (Exception e) {
            // 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            log.info("对用户[" + name + "]进行登录验证..验证未通过,堆栈轨迹如下");
            e.printStackTrace();

        }
        // 验证是否登录成功
        if (currentUser.isAuthenticated()) {
            log.info("用户[" + name + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
            Login login = loginService.selectByLoginName(name);
            Person person = personService.selectByLoginId(login.getId());
            Session session = currentUser.getSession();
            session.setAttribute("MEMBER_LOGIN_KEY", login.getId());
            session.setAttribute("MEMBER_USER_REAL_NAME", login.getRealname());
            session.setAttribute("MEMBER_USER_KEY", person.getId());
            String role = ((ExtPerson) person).getRoleList().size()>0?((ExtPerson) person).getRoleList().get(0):"";
            session.setAttribute("MEMBER_ROLE", role);
            String orgId = person.getOrgId();
            Org org = orgService.getOrgById(orgId);
            String orgName = org==null?"":org.getName();
            if (role.equals("ROLE_SUPERADMIN")){
                List<Org> orgList = orgService.getAllOrgList("0");
                if (orgList.size()>0){
                    orgId = orgList.get(0).getId();
                    orgName = orgList.get(0).getName();
                }
            }
            session.setAttribute("MEMBER_ORGID", orgId);
            session.setAttribute("MEMBER_ORGNAME", orgName);
            session.setAttribute("MEMBER_USER_PERSON", person);
            session.setAttribute("MEMBER_USER_LOGIN", login);
            return true;
        }
        else {
            token.clear();
            return false;
        }
    }


    @RequestMapping(value="logout",method = RequestMethod.GET)
    public String Logout(HttpServletRequest request,Model model,String redirect_uri){
        try {
            Session session = SecurityUtils.getSubject().getSession();
            Person person = (Person) session.getAttribute("MEMBER_USER_PERSON");
            if(person != null) {
                accessTokenService.delToken(person.getId());
            }
            //清除当前用户缓存
            DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager)SecurityUtils.getSecurityManager();
            AuthRealm shiroRealm = (AuthRealm) securityManager.getRealms().iterator().next();
            shiroRealm.clearCache(SecurityUtils.getSubject().getPrincipals());
            SecurityUtils.getSubject().logout();
        }
        catch (Exception e){
            log.error("退出异常："+e.getMessage());
        }
        if(redirect_uri==null || "".equals(redirect_uri) ) {
            return "redirect:/notlogin";
        }
        else{
            return "redirect:" + redirect_uri;
        }
    }

    /**
     * 验证码
     */
    @RequestMapping(value="captcha",method = RequestMethod.GET)
    public void captcha(HttpServletResponse response, String uuid)throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //获取图片验证码
        BufferedImage image = captchaService.getCaptcha(uuid);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    /**
     * 给角色添加权限
     * @param model
     * @return
     */
    @RequestMapping(value = "/addPermission",method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("admin:add")
    public String addPermission(Model model) {

        //在sys_role_permission 表中  将 删除的权限 关联到admin用户所在的角色
//        roleMapper.addPermission(1,3);

        //添加成功之后 清除缓存
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager)SecurityUtils.getSecurityManager();
        AuthRealm shiroRealm = (AuthRealm) securityManager.getRealms().iterator().next();
        //清除权限 相关的缓存
        shiroRealm.clearAllCache();
        return "给admin用户添加 userInfo:del 权限成功";

    }

    /**
     * 删除角色的权限
     * @param model
     * @return
     */
    @RequestMapping(value = "/delPermission",method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("admin:delete")
    public String delPermission(Model model) {

        //在sys_role_permission 表中  将 删除的权限 关联到admin用户所在的角色
//        roleMapper.delPermission(1,3);
        //添加成功之后 清除缓存
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager)SecurityUtils.getSecurityManager();
        AuthRealm shiroRealm = (AuthRealm) securityManager.getRealms().iterator().next();
        //清除权限 相关的缓存
        shiroRealm.clearAllCache();

        return "删除admin用户userInfo:del 权限成功";

    }

    /**
     * 删除某用户的权限
     * @param model
     * @return
     */
    @RequestMapping(value = "/delUserPermission",method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("admin:delete")
    public String delUserPermission(Model model) {

        //在sys_role_permission 表中  将 删除的权限 关联到admin用户所在的角色
//        roleMapper.delPermission(1,3);
        //添加成功之后 清除缓存
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager)SecurityUtils.getSecurityManager();
        AuthRealm shiroRealm = (AuthRealm) securityManager.getRealms().iterator().next();
        //清除权限 相关的缓存
        Login login = new Login();
        login.setId("UCTEST0001");
        shiroRealm.clearUserCache(login);

        return "删除admin用户userInfo:del 权限成功";

    }

    /**
     * 禁用用户
     * @param id    用户ID
     * @return
     */
    @RequestMapping(value="login",method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResult delUser(@RequestParam(value = "id") String id){
        try {
            if(loginService.removeByIdStr(id)){
                return renderSuccess();
            }
        } catch (ProgramException e) {
            log.error("用户禁用/删除失败",e);
            return renderError("用户禁用/删除失败["+id+"]");
        }
        return renderError("用户禁用/删除失败["+id+"]");
    }

    /**
     * 启用用户
     * @param id    用户ID
     * @return
     */
    @RequestMapping(value="login/enable",method = RequestMethod.PUT)
    @ResponseBody
    public JsonResult enableUser(@RequestParam(value = "id") String id){
        if(loginService.enableUserById(id)){
            return renderSuccess();
        }
        return renderError("用户启用失败,id="+id);
    }
    /**
     * 重置密码
     * @param id    用户ID
     * @return
     */
    @RequestMapping(value="password",method = RequestMethod.PUT)
    @ResponseBody
    public JsonResult resetPasswords(@RequestParam(value = "id") String id){
        try {
            if(loginService.resetPasswords(id,EncryptionUtil.encrypt("123456"))){
                return renderSuccess();
            }
        } catch (ProgramException e) {
            log.error("密码重置失败["+id+"]");
            return renderError("密码重置失败["+id+"]");
        }
        return renderError("密码重置失败["+id+"]");
    }

    /**
     * 修改当前用户密码
     * @param password    用户密码
     * @return
     */
    @RequestMapping(value="updatePassword",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updatePassword(@RequestParam(value = "password") String password,
                                     @RequestParam(value = "oldPassword") String oldPassword){
        boolean result = false;
        String msg = "";
        try {
            Session session = SecurityUtils.getSubject().getSession();
            Login login = (Login) session.getAttribute("MEMBER_USER_LOGIN");
            if(login != null) {
                if ( EncryptionUtil.encrypt(oldPassword).equalsIgnoreCase(login.getPassword())) {
                    if (loginService.resetPasswords(login.getId(), EncryptionUtil.encrypt(password))) {
                        login.setPassword(EncryptionUtil.encrypt(oldPassword));
                        session.setAttribute("MEMBER_USER_LOGIN", login);
                        result = true;
                    }
                }
                else{
                    msg = ":原密码不正确";
                }
            }
        } catch (ProgramException e) {
            log.error("密码修改失败："+e.getMessage());
        }
        if(result){
            return renderSuccess();
        }
        else {
            return renderError("密码修改失败"+msg);
        }
    }

    @RequestMapping(value = "/notlogin",method = RequestMethod.GET)
    public BaseModelAndView notlogin(BaseModelAndView bmv) {
        if(bmv==null){
            bmv = new BaseModelAndView();
        }
        bmv.addObject("client_id","");
        bmv.addObject("redirect_uri","");
        bmv.addObject("picture_uuid", UUIDUtil.getUUID());
        bmv.setViewName("safety/login");
        return bmv;
    }
}

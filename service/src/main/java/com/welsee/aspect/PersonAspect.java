package com.welsee.aspect;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.welsee.entity.Dict;
import com.welsee.entity.Login;
import com.welsee.entity.Person;
import com.welsee.exception.ProgramException;
import com.welsee.extentity.ExtPerson;
import com.welsee.service.*;
import com.welsee.tools.DictConstants;
import com.welsee.tools.HanyupinyinUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 切面类，person表的增删改操作同步操作login表
 * 登陆名唯一验证也在这里
 * person的其他业务需求也可在这里处理
 */
@Aspect
@Component
@Slf4j
public class PersonAspect {

    @Autowired
    private ILoginService loginService;
    @Autowired
    private IPersonService personService;
    @Autowired
    private IDictService dictService;

    /**
     * 切面无法切到父类的方法上，需要覆写父类的方法
     * 切入的点
     */
    @Pointcut("execution(public * com.welsee.service.impl.PersonServiceImpl.saveEntity(..)) " +
            "||execution(public * com.welsee.service.impl.PersonServiceImpl.updateEntityById(..))" +
            "||execution(public * com.welsee.service.impl.PersonServiceImpl.saveEntityBatch(..))" +
            "||execution(public * com.welsee.service.impl.PersonServiceImpl.removeByIdStr(..))")
    public void pointCut() {
    }

    @Around("pointCut()")//方法环绕切面
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //增强处理，写入login表(写在前边是因为如果走删除方法的话，就拿不到login_id了)
        saveLogin(proceedingJoinPoint);
        //执行切面自身方法
        Object proceed = proceedingJoinPoint.proceed();
        //赋权限
        saveRole(proceedingJoinPoint);//赋权限
        return proceed;
    }

    /**
     * 拦截一下数据库层面的异常，重新抛出自定义异常
     *
     * @param e
     * @return
     * @throws ProgramException
     */
    @AfterThrowing(value = "pointCut()", throwing = "e")
    public Object afterThrowing(Throwable e) throws ProgramException {
        log.error("用户切面拦截异常成功", e);
        if (e instanceof ProgramException) {
            throw new ProgramException(e.getMessage());
        } else {
            throw new ProgramException("用户操作未知异常");
        }
    }

    /**
     * 保存login表
     *
     * @param proceedingJoinPoint
     */
    private void saveLogin(ProceedingJoinPoint proceedingJoinPoint) throws ProgramException {
        Object[] args = proceedingJoinPoint.getArgs();
        if (args.length == 1) {//单条操作
            Object aClass = args[0];
            String methodName = proceedingJoinPoint.getSignature().getName();
            if (methodName.equals("saveEntity")) {//保证类型为ExtPerson
                ExtPerson person = (ExtPerson) aClass;//这里可以根据实体类型的用户类型区别处理(如不做login表操作)
                Login login = convertLogin(person, false);
                checkLoginName(login, false);//校验登陆名是否已存在
                loginService.save(login);
            } else if (methodName.equals("updateEntityById")) {
                ExtPerson person = (ExtPerson) aClass;//这里可以根据实体类型的用户类型区别处理(如不做login表操作)
                if (person.getPersonType() == null || DictConstants.PERSON_PATRIARCH.equals(person.getPersonType())) {
                    return;//家长目前没有存登陆表
                }
                Login login = convertLogin(person, true);
                checkLoginName(login, true);//校验登陆名是否已存在
                loginService.updateById(login);
            } else if (methodName.equals("removeByIdStr")) {//删除是根据ID进行操作,对象是一个字符串
                String aClass1 = (String) aClass;//这里接收到的是用户的ID
                List<String> loginIds = personService.getLoginIdByPersonId(aClass1);//根据用户ID查询出需要操作的登陆ID
                if (loginIds.size() > 0) {
                    aClass1 = loginIds.get(0);
                    if (loginIds.size() > 1) {//批量操作的话需要拿到对应的所有login_id
                        aClass1 = StringUtils.quotaMarkList(loginIds);
                    }
                    loginService.removeByIdStr(aClass1);//这里是根据login_id进行删除(而不是person表的id)
                }
            } else if (methodName.equals("saveEntityBatch")) {
                List<ExtPerson> extPersonList = (List<ExtPerson>) aClass;
                List<Login> loginList = new ArrayList<>();
                extPersonList.stream().forEach(extPerson -> {
                    Login login = new Login();
                    login.setId(extPerson.getLoginId());
                    login.setPassword(extPerson.getPassword());
                    login.setEmail(extPerson.getEmail());
                    login.setTel(extPerson.getTel());
                    login.setRealname(extPerson.getRealname());
                    login.setLoginName1(extPerson.getLoginName());
                    login.setLoginName2(extPerson.getTel());
                    login.setLoginName3(extPerson.getEmail());
                    login.setLoginName4(extPerson.getIdentityNo());
                    login.setDel(extPerson.getDel());
                    login.setCreatedatetime(extPerson.getCreatedatetime());
                    login.setModifydatetime(extPerson.getModifydatetime());
                    loginList.add(login);
                });
                loginService.saveOrUpdateEntityBatch(loginList);
            }
        }
    }

    /**
     * 检查登陆名是否重复
     *
     * @param login
     * @throws ProgramException
     */
    private void checkLoginName(Login login, boolean isUpdate) throws ProgramException {
        if (login.getLoginName1() == null
                && login.getLoginName2() == null
                && login.getLoginName3() == null
                && login.getLoginName4() == null) {
            return;
        }
        List<Login> logins = loginService.getLoginByLoginName(
                login.getLoginName1(),
                login.getLoginName2(),
                login.getLoginName3(),
                login.getLoginName4());
        if (isUpdate) {
            if (logins.size() >= 1 && !logins.stream().anyMatch(login1 -> login1.getId().equals(login.getId()))) {
                throw new ProgramException("登陆名重复");
            }
        } else {
            if (logins.size() > 0) {
                throw new ProgramException("登陆名重复");
            }
        }

    }

    /**
     * 转换成login表对应字段
     *
     * @param person
     * @return
     */
    private Login convertLogin(ExtPerson person, boolean isUpdate) {
        if (!isUpdate) {
            person.setDel(0);//教师登陆账号默认启用
            //新增时，设置初始密码(当然是在没有指定的情况下)
            if (person.getPassword() == null || "".equals(person.getPassword())) {
                person.setPassword("123456");
            }
            if (person.getLoginName() == null || "".equals(person.getLoginName())) {
                //不写用户名的话，来个默认的，不去判断是否存在了，直接姓名转拼音+id前5位
                String loginName = HanyupinyinUtil.toHanyuPinyin(person.getRealname());
                //这样不爽的话可以先查以下数据库，如果不存在同名就不拼id了，但是这样会牺牲一点性能
                //这个名字可以后期通过前端页面，由用户自己修改
                loginName += person.getId().substring(0, 4);
                person.setLoginName(loginName);
            }
        }
        return person.convert(isUpdate);
    }

    /**
     * 根据用户类型赋权限
     *
     * @param proceedingJoinPoint
     */
    private void saveRole(ProceedingJoinPoint proceedingJoinPoint) throws Exception {
        //获取方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        //获取所有的参数
        Object[] args = proceedingJoinPoint.getArgs();
        if (methodName.equals("saveEntity")) {
            //获取用户对象
            Object aClass = args[0];
            ExtPerson person = (ExtPerson) aClass;
            //如果有权限的话，同时赋上权限
            List<String> roleList = person.getRoleList();
            if (roleList != null && roleList.size() > 0) {
                String codes = roleList.stream().collect(Collectors.joining(","));
                try {
                    List<Dict> dicts = dictService.getDicts(codes);
                    String dictIds = dicts.stream().map(Dict::getId).collect(Collectors.joining(","));
                    personService.addRoleByUser(person.getId(), dictIds);
                } catch (Exception e) {
                    log.error("用户赋权失败", e);
                    throw new ProgramException(e.getMessage());
                }
            }
        } else if (methodName.equals("removeByIdStr")) {//删除是根据ID进行操作,对象是一个字符串
            Object aClass = args[0];
            String userId = (String) aClass;//这里接收到的是用户的ID
            try {
                personService.deleteRoleByUser(userId);
            } catch (Exception e) {
                log.error("用户权限移除失败", e);
                throw new ProgramException(e.getMessage());
            }
        }
    }
}

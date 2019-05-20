package com.welsee.aspect;

import com.welsee.dto.user.PublicPersonParams;
import com.welsee.entity.Dict;
import com.welsee.entity.Org;
import com.welsee.exception.ProgramException;
import com.welsee.service.IAccessTokenService;
import com.welsee.service.IDictService;
import com.welsee.service.IOrgService;
import com.welsee.tools.DictConstants;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 公共接口切面类
 * 接口验证
 */
@Aspect
@Component
@Slf4j
public class PublicPersonAspect {

    @Autowired
    private IDictService dictService;

    @Autowired
    private IAccessTokenService accessTokenService;

    @Autowired
    private IOrgService orgService;

    /**
     * 切入的点
     */
    @Pointcut("execution(public * com.welsee.service.impl.PersonServiceImpl.getPerson(..)) " )
    public void pointCut(){}

    @Around("pointCut()")//方法环绕切面
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //检查参数
        checkParams(proceedingJoinPoint);
        //执行切面自身方法
        Object proceed = proceedingJoinPoint.proceed();
        return proceed;
    }

    /**
     * 拦截一下数据库层面的异常，重新抛出自定义异常
     * @param e
     * @return
     * @throws ProgramException
     */
    @AfterThrowing(value = "pointCut()",throwing = "e")
    public Object afterThrowing(Throwable e) throws ProgramException {
        log.error("用户公共接口切面拦截异常成功",e);
        if(e instanceof ProgramException){
            throw new ProgramException(e.getMessage());
        }else{
            throw new ProgramException("用户公共接口未知异常");
        }
    }

    /**
     * 检查参数
     * @param proceedingJoinPoint
     */
    private void checkParams(ProceedingJoinPoint proceedingJoinPoint) throws Exception {
        Object[] args = proceedingJoinPoint.getArgs();
        //参数不能为空
        if(args.length<1){
            throw new ProgramException("参数不存在");
        }else{
            Object aClass = args[0];
            PublicPersonParams params = (PublicPersonParams) aClass;
            String token = params.getToken();
            if(token == null ||  "".equals(token) || "null".equals(token)){
                log.error("token为空:",params);
                throw new ProgramException("token不能为空");
            }
            //校验机构编码及用户类型不能为空
            String orgCode = params.getOrgCode();
            String userType = params.getUserType();
            if(("".equals(orgCode) || "null".equals(orgCode)) && ("".equals(userType) || "null".equals(userType))){
                log.error("机构编码或用户类型不能为空:",orgCode,"-",userType);
                throw new ProgramException("机构编码或用户类型不能为空");
            }
            if(!accessTokenService.validateOAuth2Token(token,DictConstants.TOKEN_SYSTEM)){
                log.error("token验证失败:",token);
                throw new ProgramException("token验证失败");
            }
            //检查机构类型是否为学校
            if(!checkOrgType(orgCode,DictConstants.ORGTYPE_SCHOOL)){
                throw new ProgramException("机构必须为学校,CODE["+orgCode+"]");
            }
        }
    }

    /**
     * 检查是否是预期的机构类型
     * @param orgCode           机构CODE
     * @param expectedType      预期机构类型编码
     * @return
     * @throws ProgramException
     */
    private boolean checkOrgType(String orgCode,String expectedType) throws ProgramException {
        if(DictConstants.FLAG_ORG.equals(orgCode)){//返回所有
            return true;
        }
        try {
            Org orgByCode = orgService.getOrgByCode(orgCode);
            if(orgByCode == null || !expectedType.equals(orgByCode.getOrgType())){
                return false;
            }else{
                return true;
            }
        } catch (Exception e) {
            log.error("机构编码不正确，请检查机构编码",e);
            throw new ProgramException("请检查机构编码["+orgCode+"]是否正确");
        }
    }
}

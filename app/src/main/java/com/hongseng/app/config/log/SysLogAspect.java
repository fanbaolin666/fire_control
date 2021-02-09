package com.hongseng.app.config.log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hongseng.app.mapper.LogMapper;
import enums.TokenEnum;
import model.JwtUser;
import model.SysLog;
import model.dto.UserDto;
import model.vo.SelectUserVo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import utils.IpUtil;
import utils.JwtTokenUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.charset.Charset;

import static com.hongseng.app.config.jwtfilter.JWTAuthorizationFilter.LOGIN_URL;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-02-04 16:10
 **/
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    LogMapper logMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * @Author fbl
     * @Description 定义切点 @Pointcut 在注解的位置切入代码
     * @Date 16:31 2021/2/4
     * @Param
     */
    @Pointcut("@annotation(com.hongseng.app.config.log.MyLog)")
    public void logPointCut() {
    }

    /**
     * @Author fbl
     * @Description 切面 配置通知
     * @Date 16:31 2021/2/4
     * @Param
     */
    @AfterReturning("logPointCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        SysLog sysLog = new SysLog();
        // 获取ip
        String ipAddr = IpUtil.getIpAddr(request);
        sysLog.setIp(ipAddr);
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 获取操作
        MyLog myLog = method.getAnnotation(MyLog.class);
        sysLog.setOperation(myLog.value());
        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName);

        //请求的参数
        Object[] args = joinPoint.getArgs();
        //将参数所在的数组转换成json
        String params = JSON.toJSONString(args);
        sysLog.setParams(params);
        if (request.getRequestURL().toString().contains(LOGIN_URL)) {
            Object[] loginPar = joinPoint.getArgs();
            //将参数所在的数组转换成json
            String loginParStr = JSON.toJSONString(loginPar[0]);
            UserDto userDto = JSON.parseObject(loginParStr, UserDto.class);
            sysLog.setUserName(userDto.getUserName());
            logMapper.insert(sysLog);
        } else {
            String header = request.getHeader(TokenEnum.TOKEN_HEADER.getValue());
            String token = header.replace(TokenEnum.TOKEN_PREFIX.getValue(), "");
            String userName = JwtTokenUtils.getUsername(token);
            sysLog.setUserName(userName);
            logMapper.insert(sysLog);
        }
    }

}

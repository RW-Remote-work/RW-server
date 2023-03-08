package com.rwws.rwserver.module.support.core;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rwws.rwserver.common.core.domain.RequestUser;
import com.rwws.rwserver.common.util.RWRequestUtil;
import com.rwws.rwserver.module.support.annotation.WebOperateLog;
import com.rwws.rwserver.module.support.domain.OperateLog;
import com.rwws.rwserver.module.support.mapper.OperateLogMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

/**
 * 操作日志记录处理,对所有OperateLog注解的Controller进行操作日志监控
 *
 * @Author 1024创新实验室: 罗伊
 * @Date 2021-12-08 20:48:52
 * @Wechat zhuoda1024
 * @Email lab1024@163.com
 * @Copyright 1024创新实验室 （ https://1024lab.net ）
 */
@Slf4j
@Aspect
public class WebOperateLogAspect {

    private static final String pointCut = "@within(com.rwws.rwserver.module.support.annotation.WebOperateLog)";

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 线程池
     */
    @Qualifier("logThreadPool")
    private ThreadPoolTaskExecutor taskExecutor;

    public WebOperateLogAspect() {
    }

    @Pointcut(pointCut)
    public void logPointCut() {
    }

    @AfterReturning(pointcut = "logPointCut()")
    public void doAfterReturning(JoinPoint joinPoint) {
        handleLog(joinPoint, null);
    }

    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e);
    }

    protected void handleLog(final JoinPoint joinPoint, final Exception e) {
        try {
            WebOperateLog operateLog = this.getAnnotationLog(joinPoint);
            if (operateLog == null) {
                return;
            }
            this.submitLog(joinPoint, e);
        } catch (Exception exp) {
            log.error("保存操作日志异常:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    private WebOperateLog getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        WebOperateLog classAnnotation = AnnotationUtils.findAnnotation(method.getDeclaringClass(), WebOperateLog.class);
        if (method != null) {
            return classAnnotation;
        }
        return null;
    }

    /**
     * swagger API
     *
     * @param joinPoint
     * @return
     * @throws Exception
     */
    private Tag getApi(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Tag classAnnotation = AnnotationUtils.findAnnotation(method.getDeclaringClass(), Tag.class);
        if (method != null) {
            return classAnnotation;
        }
        return null;
    }

    /**
     * swagger ApiOperation
     *
     * @param joinPoint
     * @return
     * @throws Exception
     */
    private Operation getApiOperation(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(Operation.class);
        }
        return null;
    }

    /**
     * 提交存储操作日志
     *
     * @param joinPoint
     * @param e
     * @throws Exception
     */
    private void submitLog(final JoinPoint joinPoint, final Throwable e) throws Exception {
        Boolean isOpen = this.isOpen();
        if (!isOpen) {
            return;
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Boolean filter = this.filterUrl(request.getRequestURI());
        if (filter) {
            return;
        }
        //设置用户信息
        RequestUser user = RWRequestUtil.getRequestUser();
        if (user == null) {
            return;
        }

        Object[] args = joinPoint.getArgs();
        String params = JSON.toJSONString(args);
        // 设置方法名称
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        String operateMethod = className + "." + methodName;
        String failReason = null;
        Boolean successFlag = true;
        if (e != null) {
            successFlag = false;
            failReason = getExceptionString(e);
        }


        OperateLog operateLogEntity =
                OperateLog.builder()
                        .operateUserId(user.getUserId())
                        .operateUserType(user.getUserType().getValue())
                        .operateUserName(user.getUserName())
                        .url(request.getRequestURI())
                        .method(operateMethod)
                        .param(params)
                        .ip(user.getIp())
                        .userAgent(user.getUserAgent())
                        .failReason(failReason)
                        .successFlag(successFlag).build();
        Operation apiOperation = this.getApiOperation(joinPoint);
        if (apiOperation != null) {
            operateLogEntity.setContent(apiOperation.description());
        }
        Tag api = this.getApi(joinPoint);
        if (api != null) {
            operateLogEntity.setModule(api.name());
        }
        taskExecutor.execute(() -> {
            this.saveLog(operateLogEntity);
        });
    }


    private String getExceptionString(Throwable e) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw);) {
            e.printStackTrace(pw);
        }
        return sw.toString();
    }

    /**
     * 是否开启操作日志
     *
     * @return
     */
    private Boolean isOpen() {
        return Boolean.TRUE;
    }

    /**
     * 需要过滤的url
     *
     * @param url
     * @return
     */
    private Boolean filterUrl(String url) {
        return Boolean.FALSE;
    }

    /**
     * 保存操作日志
     *
     * @param operateLogEntity
     * @return
     */
    private Boolean saveLog(OperateLog operateLogEntity) {
        BaseMapper mapper = applicationContext.getBean(OperateLogMapper.class);
        if (mapper == null) {
            return false;
        }
        mapper.insert(operateLogEntity);
        return true;
    }

}

package cn.itcast.ssm.controller;

import cn.itcast.ssm.dao.SysLog;
import cn.itcast.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime;//开始时间
    private Class clazz;//访问的类
    private Method method;//访问的方法

    //前置通知 获取开始时间,执行的类,执行的方法
    @Before("execution(* cn.itcast.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();//开始访问时间
        clazz = jp.getTarget().getClass();//具体要访问的类
        String methodName = jp.getSignature().getName(); //访问的方法名

        //获取具体执行的方法的Method对象
        Object[] args = jp.getArgs();//执行方法的参数数组
        if (args == null || args.length == 0) {
            method = clazz.getMethod(methodName);//获取无参数的方法
        } else {
            Class[] classeArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classeArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName, classeArgs);
        }
    }

    //后置通知
    @After("execution(* cn.itcast.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        //1.访问时长
        long time = new Date().getTime() - visitTime.getTime();

        //2.获取url
        String url = "";
        //如果要访问的类为空或日志类,或方法为空则退出
        if (clazz == null || method == null || clazz == LogAop.class) {
            return;
        }

        //获取类上的@RequestMapping("/orders")
        RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
        //如果没有@RequesetMapping注解则退出
        if (classAnnotation == null) {
            return;
        }
        String[] classValue = classAnnotation.value();

        //获取方法上的@RequestMapping(xxx)
        RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
        //如果方法上没有该注解,则退出
        if (methodAnnotation == null) {
            return;
        }
        String[] methodValue = methodAnnotation.value();

        url = classValue[0] + methodValue[0];

        //3.获取访问的IP
        String ip = request.getRemoteAddr();

        //4.获取当前操作的用户
        SecurityContext context = SecurityContextHolder.getContext();//从上下文中获取当前登录的用户
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();

        //5.将日志信息封装到SysLog对象
        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(time);//执行时长
        sysLog.setIp(ip);
        sysLog.setMethod("[类名]" + clazz.getName() + "[方法名]" + method.getName());
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(visitTime);

        //调用Service完成储存
        sysLogService.save(sysLog);
    }
}










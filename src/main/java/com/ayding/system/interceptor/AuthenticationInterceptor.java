package com.ayding.system.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.ayding.common.annotation.LoginRequired;
import com.ayding.common.constant.Constants;
import com.ayding.common.enums.ErrorEnum;
import com.ayding.common.util.ExceptionUtil;
import com.ayding.system.enums.RoleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Enumeration;

/**
 * @Author:ayding
 * @Date:2018/09/27 12:52
 * preHandle：在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理；
 * postHandle：在业务处理器处理请求执行完成后，生成视图之前执行。后处理（调用了Service并返回ModelAndView，但未进行页面渲染），有机会修改ModelAndView （这个博主就基本不怎么用了）；
 * afterCompletion：在DispatcherServlet完全处理完请求后被调用，可用于清理资源等。返回处理（已经渲染了页面）；
 */
@Configuration
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        log.info("<=========================进入拦截器AuthenticationInterceptor:preHandle=============================>");
        //打印请求参数
        JSONObject parameter =new JSONObject();// 保存参数
        // 请求的参数
        Enumeration<String> enums = httpServletRequest.getParameterNames();
        while(enums.hasMoreElements()) {
            String paramName = enums.nextElement();
            String paramValue = httpServletRequest.getParameter(String.valueOf(paramName));
            parameter.put(paramName,paramValue);
        }
        log.info("<==================请求参数=====================>{}",parameter.toJSONString());


        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader(Constants.AUTHENTICATION);

        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有LoginRequired注释，没有有则跳过认证
        if (!method.isAnnotationPresent(LoginRequired.class)) {
            return true;
        }

        LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);
        if (loginRequired.required()) {
            // 执行认证
            if (token == null) {
                ExceptionUtil.rollback(ErrorEnum.INVALID_TOKEN);
            }

            RoleEnum role = loginRequired.role();
            if (role == RoleEnum.USER) {
                return true;
            }

//            if (role == RoleEnum.ADMIN) {
//                UserSessionVO userSessionInfo = SessionUtil.getUserSessionInfo();
//                if (role != RoleEnum.getEnumTypeMap().get(userSessionInfo.getRoleId())) {
//                    ExceptionUtil.rollback(ErrorEnum.ACCESS_NO_PRIVILEGE);
//                }
//            }

            return true;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {
        log.info("<=========================进入拦截器AuthenticationInterceptor:postHandle=============================>");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
        log.info("<=========================进入拦截器AuthenticationInterceptor:afterCompletion=============================>");
    }
}

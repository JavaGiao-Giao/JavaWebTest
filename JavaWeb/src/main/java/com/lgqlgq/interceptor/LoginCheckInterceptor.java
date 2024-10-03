package com.lgqlgq.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.lgqlgq.pojo.Result;
import com.lgqlgq.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求url
        String url = request.getRequestURI().toString();
        log.info("请求的url{}",url);

        //判断请求访问的是否为登录界面
        //contains()方法属于java.lang.String类。它用于检查一个字符串是否包含指定的字符序列（子字符串）
        if (url.contains("login")){
            log.info("访问登录界面放行");
            return true;
        }

        //获取令牌
        String jwt = request.getHeader("token");

        //判断令牌是否为空
        if (jwt == null || jwt.equals("")){
            log.info("请求头token为空，返回未登录信息");
            Result result = Result.error("NOT_LOGIN");
            //filter不能自动将result转换为json字符串，需手动转换
            String jsonString = JSONObject.toJSONString(result);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(jsonString);
            return false;
        }

        //校验令牌，解析token
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            log.info("令牌解析失败");
            Result result = Result.error("NOT_LOGIN");
            String jsonString = JSONObject.toJSONString(result);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(jsonString);
            return false;
        }

        log.info("令牌合法放行");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

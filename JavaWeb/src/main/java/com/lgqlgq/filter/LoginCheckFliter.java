package com.lgqlgq.filter;

import com.alibaba.fastjson.JSONObject;
import com.lgqlgq.pojo.Result;
import com.lgqlgq.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginCheckFliter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取请求url
        String url = request.getRequestURI().toString();
        log.info("请求的url{}",url);

        //判断请求访问的是否为登录界面
        //contains()方法属于java.lang.String类。它用于检查一个字符串是否包含指定的字符序列（子字符串）
        if (url.contains("login")){
            log.info("访问登录界面放行");
            filterChain.doFilter(request, response);
            return;
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
            return;
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
            return;
        }

        log.info("令牌合法放行");
        filterChain.doFilter(request,response);
    }
}

package com.lgqlgq.controller;


import com.lgqlgq.pojo.Emp;
import com.lgqlgq.pojo.Result;
import com.lgqlgq.service.EmpService;
import com.lgqlgq.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
@Slf4j
public class LoginController {

    @Autowired
    EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
      log.info("账户名为{}尝试登录",emp.getUsername());
      Emp emp1 = empService.login(emp);
      //登陆成功，返回令牌
      if (emp1 != null) {
          Map<String, Object> claims=new HashMap<>();
          claims.put("id",emp1.getId());
          claims.put("name",emp1.getName());
          claims.put("username",emp1.getUsername());
          String jwt = JwtUtils.generateJwt(claims);
          return Result.success(jwt);
      }
      //登陆失败 返回异常信息
      return Result.error("用户名或密码错误");
    }
}

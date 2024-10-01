package com.lgqlgq.controller;

import com.lgqlgq.pojo.Emp;
import com.lgqlgq.pojo.PageBean;
import com.lgqlgq.pojo.Result;
import com.lgqlgq.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@ResponseBody
@Slf4j
public class EmpController {

    @Autowired
    private EmpService empService;

    //分页显示页面信息、模糊查询用户
    @GetMapping("/emps")
    public Result emps(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name,Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {

        log.info("分页查询,参数：第{}页，共{}行数据,姓名：{},性别为：{},{},{}", page, pageSize,name, gender, begin, end);
        PageBean pageBean = empService.page(page, pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }

    //删除、批量删除员工
    @DeleteMapping("/emps/{ids}")
    public Result deleteEmps(@PathVariable("ids") List<Integer> ids) {
        log.info("删除员工id为{}：",ids);
        empService.delete(ids);
        return Result.success();
    }

    //新增员工
    @PostMapping("/emps")
    public Result addEmp(@RequestBody Emp emp) {
        log.info("新增员工：{}",emp);
        empService.addEmp(emp);
        return Result.success();
    }

}

package com.lgqlgq.controller;


import com.lgqlgq.mapper.DeptMapper;
import com.lgqlgq.pojo.Dept;
import com.lgqlgq.pojo.Result;
import com.lgqlgq.service.DeptService;
import com.lgqlgq.service.serviceImpl.DeptServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.VarHandle;
import java.util.List;

@Controller
@ResponseBody
@Slf4j
public class DeptController {

//    private static Logger log= LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;
    @Autowired
    private DeptMapper deptMapper;


    //查询部门所有信息
    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    public Result list() {
        log.info("查询全部部门数据");
        List<Dept> list=deptService.list();
        return Result.success(list);
    }

    //根据id删除部门信息
    @DeleteMapping("/depts/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除部门id为的"+ id +"部门");
        deptService.delete(id);
        return Result.success();
    }

    //添加一个部门
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept) {
        log.info("新增部门:{}",dept);
        deptService.add(dept);
        return Result.success();
    }

    //修改部门信息
    //前端响应数据有误，只响应了name属性，未提供Id，且请求模式为post请求
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept) {
        log.info("修改部门{}:",dept.getId());
        deptService.updata(dept);
        return Result.success();
    }

}

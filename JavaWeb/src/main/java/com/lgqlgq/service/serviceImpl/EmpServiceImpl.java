package com.lgqlgq.service.serviceImpl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lgqlgq.mapper.EmpMapper;
import com.lgqlgq.pojo.Emp;
import com.lgqlgq.pojo.PageBean;
import com.lgqlgq.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
//    @Override
//    public PageBean page(Integer page, Integer pageSize) {
////        分页查询未使用插件
////        int count = empMapper.count();
////
////        page=(page-1)*pageSize;
////        List<Emp> emps = empMapper.psge(page, pageSize);
////
////        PageBean pageBean = new PageBean(emps, count);
////        return pageBean;
//
//    }

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {

        //使用插件
        PageHelper.startPage(page, pageSize);

        List<Emp> list = empMapper.list(name, gender, begin, end);
        Page<Emp> page1 = (Page<Emp>) list;

        PageBean pageBean = new PageBean(page1.getResult(), page1.getTotal());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void addEmp(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.addEmp(emp);
    }

    @Override
    public Emp getEmp(Integer id) {
        return empMapper.getEmp(id);
    }

    @Override
    public void updateEmp(Emp emp) {
        empMapper.updateEmp(emp);
    }

    //用户登录查询账户以及密码是否正确
    @Override
    public Emp login(Emp emp) {
        Emp emp1=empMapper.getByUsernameAndPassword(emp);
        return emp1;
    }
}

package com.lgqlgq.service;

import com.lgqlgq.pojo.Emp;
import com.lgqlgq.pojo.PageBean;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {


    PageBean page(Integer page, Integer pageSize,String name,Short gender, LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);

    void addEmp(Emp emp);

    Emp getEmp(Integer id);

    void updateEmp(Emp emp);

    Emp login(Emp emp);
}

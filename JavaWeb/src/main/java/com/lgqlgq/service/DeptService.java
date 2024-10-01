package com.lgqlgq.service;

import com.lgqlgq.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> list();

    void delete(Integer id);

    void add(Dept dept);

    void updata(Dept dept);
}

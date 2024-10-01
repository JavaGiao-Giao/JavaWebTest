package com.lgqlgq.mapper;

import com.lgqlgq.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select * from dept")
    List<Dept> list();

    @Delete("delete from dept where id=#{id}")
    void delete(int id);


    @Insert("insert into dept (name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
    void addDept(Dept dept);


    @Update("update dept set name=#{name},create_time=#{createTime},update_time=#{updateTime} where id=#{id}")
    void updeata(Dept dept);
}

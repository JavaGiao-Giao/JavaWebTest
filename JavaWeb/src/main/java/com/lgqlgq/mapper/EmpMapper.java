package com.lgqlgq.mapper;

import com.lgqlgq.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

//    @Select("select count(*) from emp")
//    public int count();
//
//    @Select("select * from emp limit #{page},#{pageSize}")
//    public List<Emp> psge(Integer page, Integer pageSize);

//    @Select("select * from emp")
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    //批量删除员工
    void delete(List<Integer> ids);

    //添加用户
    @Insert("insert into emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time) values (#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
    void addEmp(Emp emp);

    //查询用户
    Emp getEmp(Integer id);


    //编辑用户信息
    void updateEmp(Emp emp);

    @Select("select id, username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time " +
            "from emp " +
            "where username=#{username} and password =#{password}")
    Emp getByUsernameAndPassword(Emp emp);
}

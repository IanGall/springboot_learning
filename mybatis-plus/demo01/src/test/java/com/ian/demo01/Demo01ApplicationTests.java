package com.ian.demo01;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ian.demo01.bean.Employee;
import com.ian.demo01.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class Demo01ApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    EmployeeMapper mapper;

    @Test
    void test1() {
        System.out.println(mapper.selectById(1));
    }

    @Test
    void testCommonInsert() {
        Employee employee = new Employee();
        employee.setLastName("MP");
        employee.setEmail("mp@atguigu.com");
        employee.setGender("1");
        employee.setAge(22);
        employee.setSalary(2000.0);

        //插入到数据库
        int res = mapper.insert(employee);
        System.out.println("result: " + res);

        //获取当前数据在数据库中的主键值
        System.out.println("key: " + employee.getId());

    }

    @Test
    void testCommonUpdate() {
        Employee employee = new Employee();
        employee.setId(5);
        employee.setLastName("MybatisPlus");
        employee.setEmail("mybatisPlus@sina.com");
        employee.setGender("0");
        employee.setAge(30);

        int res = mapper.updateById(employee);
        System.out.println("result: " + res);
    }

    @Test
    void testCommonSelect() {
        //1.通过id查询
        //System.out.println(mapper.selectById(7));

        //2.通过多条列进行查询，只能查一条记录
        //Employee employee = new Employee();
        //employee.setLastName("MP");
        //employee.setGender("1");
        //employee.setAge(25);
        //employee.setId(7);

        //System.out.println(mapper.selectOne(Wrappers.query(employee)));

        //3.通过多个id进行查询
        //List<Integer> list = new ArrayList<>();
        //list.add(4);
        //list.add(5);
        //list.add(6);
        //list.add(7);
        //System.out.println(mapper.selectBatchIds(list));

        //4.通过Map封装条件查询
        //Map<String, Object> columnMap = new HashMap<>();
        //columnMap.put("last_name", "MP");
        //columnMap.put("gender", "1");
        //System.out.println(mapper.selectByMap(columnMap));

        //5.分页查询
        Page<Employee> page = mapper.selectPage(new Page<>(2, 2), null);
        System.out.println(page.getRecords());


    }

    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getConnection());
        System.out.println();
    }
}

package com.ian.demo02;

import java.util.Date;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ian.demo02.bean.User;
import com.ian.demo02.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SelectTest {

    @Autowired
    private UserMapper mapper;

    QueryWrapper<User> query;

    @BeforeEach
    void setUp() {
        query = Wrappers.query();
    }

    @Test
    void select() {
        List<User> list = mapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    void insert() {
        /*
         * MP的主键策略默认是雪花算法，就是如果你不设置主键的话，MP通过代码给你自动通过雪花算法算出一个值，给你填上，
         * 插入的时候就会把它插入。*/
        User user = new User();
        user.setName("张三");
        user.setAge(31);
        //user.setEmail("zs@baomidou.com");
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(new Date());
        user.setRemark("备注信息");

        int rows = mapper.insert(user);
        System.out.println("rows: " + rows);
    }

    @Test
    void selectById() {
        User user = mapper.selectById(1094590409767661570L);
        System.out.println(user);
    }

    @Test
    void selectBatchIds() {
        List<Long> ids = Arrays.asList(1088248166370832385L, 1094592041087729666L, 1087982257332887553L);
        List<User> users = mapper.selectBatchIds(ids);
        System.out.println(users);
    }

    @Test
    void selectByMap() {
        /*
         * 其中键是表中的列名*/
        Map<String, Object> map = new HashMap<>();
        map.put("name", "王天风");
        map.put("age", 25);

        List<User> list = mapper.selectByMap(map);
        System.out.println(list);
    }

    /**
     * 查询名字中包含'雨'并且年龄小于40
     * where name like '%雨%' and age < 40
     */
    @Test
    void wrapper1() {
        QueryWrapper<User> query = Wrappers.query();
        query.like("name", "雨").lt("age", 40);

        List<User> users = mapper.selectList(query);
        System.out.println(users);
    }

    /**
     * 名字中包含雨年并且龄大于等于20且小于等于40并且email不为空
     */
    @Test
    void wrapper2() {
        QueryWrapper<User> query = Wrappers.query();
        query.like("name", "雨").between("age", 20, 40);

        List<User> users = mapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     * 名字为王姓或者年龄大于等于25，按照年龄降序排列，年龄相同按照id升序排列
     */
    @Test
    void wrapper3() {
        QueryWrapper<User> query = Wrappers.query();
        query.likeRight("name", "王").or().ge("age", 25)
                .orderByDesc("age").orderByAsc("user_id");

        List<User> users = mapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     * 创建日期为2019年2月14日并且直属上级为名字为王姓
     * date_format(create_time,'%Y-%m-%d')='2019-02-14'
     * and manager_id in (select id from user where name like '王%')
     */
    @Test
    void wrapper4() {
        //1.该方法可用于数据库函数 动态入参的params对应前面applySql内部的{index}部分.
        // 这样是不会有sql注入风险的,反之会有!
        query.apply("date_format(create_time,'%Y-%m-%d') = {0}", "2019-02-14")
                //2.会有sql注入的风险
                //query.apply("date_format(create_time,'%Y-%m-%d') = 2019-02-14")
                .inSql("manager_id", "select user_id from mp_user where name like '王%'");

        List<User> users = mapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     * 名字为王姓并且（年龄小于40或邮箱不为空）
     * name like '王%' and (age<40 or email is not null)
     */
    @Test
    void wrapper5() {
        query.likeRight("name", "王").and(wrapper ->
                wrapper.lt("age", 40).or().isNotNull("email"));

        List<User> users = mapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     * 名字为王姓或者（年龄小于40并且年龄大于20并且邮箱不为空）
     * name like '王%' or (age<40 and age>20 and email is not null)
     */
    @Test
    void wrapper6() {
        query.likeRight("name", "王").or(wrapper ->
                wrapper.lt("age", 40).gt("age", 20).isNotNull("email"));

        List<User> users = mapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     * （年龄小于40或邮箱不为空）并且名字为王姓
     * (age<40 or email is not null) and name like '王%'
     */
    @Test
    void wrapper7() {
        query.nested(wrapper -> wrapper.lt("age", 40).or().isNotNull("email"))
                .likeRight("name", "王");

        List<User> users = mapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     * 年龄为30、31、34、35
     * age in (30、31、34、35)
     */
    @Test
    void wrapper8() {
        query.in("age", 30, 31, 34, 35);

        List<User> users = mapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     * 只返回满足条件的其中一条语句即可
     * limit 1
     */
    @Test
    void wrapper9() {
        query.in("age", 30, 31, 34, 35).last("limit 1");

        List<User> users = mapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     * 指定字段
     */
    @Test
    void wrapper10() {
        query.in("age", 30, 31, 34, 35).last("limit 1")
                //方法1
                //.select("name", "age")
                //方法2
                .select(User.class, info -> !info.getColumn().equals("create_time") &&
                        !info.getColumn().equals("manager_id"));

        List<User> users = mapper.selectList(query);
        users.forEach(System.out::println);
    }

    void condition(String name, String email) {
        //if (StringUtils.isNotBlank(name)) {
        //    query.like("name", name);
        //}
        //
        //if (StringUtils.isNotBlank(email)) {
        //    query.like("email", email);
        //}

        query.like(StringUtils.isNotBlank(name), "name", name)
                .like(StringUtils.isNotBlank(email), "email", email);

        List<User> users = mapper.selectList(query);
        users.forEach(System.out::println);
    }

    @Test
    void testCondition() {
        String name = "";
        String email = "x";
        condition(name, email);
    }

    @Test
    void selectByWrapperEntity() {
        User user = new User();
        user.setName("刘雨红");
        user.setAge(32);

        List<User> users = mapper.selectList(query);
        users.forEach(System.out::println);
    }

    @Test
    void selectByWrapperAllEq() {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "王天风");
        params.put("age", null);
        query.allEq(params, false);

        List<User> users = mapper.selectList(query);
        users.forEach(System.out::println);
    }

    @Test
    void selectByWrapperMaps() {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "王天风");
        params.put("age", null);
        query.allEq(params, false);

        List<Map<String, Object>> maps = mapper.selectMaps(query);
        maps.forEach(System.out::println);
    }

    /**
     * 11、按照直属上级分组，查询每组的平均年龄、最大年龄、最小年龄。
     * 并且只取年龄总和小于500的组。
     * select avg(age) avg_age,min(age) min_age,max(age) max_age
     * from user
     * group by manager_id
     * having sum(age) <500
     */
    @Test
    void selectByWrapperMaps2() {
        query.select("avg(age) avg_age,min(age) min_age,max(age) max_age")
                .groupBy("manager_id").having("sum(age)<{0}", 500);

        List<Map<String, Object>> maps = mapper.selectMaps(query);
        maps.forEach(System.out::println);
    }

    @Test
    void selectByWrapperObjs() {
        query.select("user_id", "name").like("name", "雨").lt("age", 40);

        List<Object> maps = mapper.selectObjs(query);
        maps.forEach(System.out::println);
    }

    @Test
    void selectByWrapperCount() {
        query.like("name", "雨").lt("age", 40);

        Integer count = mapper.selectCount(query);
        System.out.println(count);
    }

    LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();

    @Test
    void selectLambda() {
        lambdaQuery.like(User::getName, "雨").lt(User::getAge, 40);

        List<User> users = mapper.selectList(lambdaQuery);
        users.forEach(System.out::println);
    }

    /**
     * 使用xml进行查询
     */
    @Test
    void selectLambda2() {
        lambdaQuery.like(User::getName, "雨").lt(User::getAge, 40);

        List<User> users = mapper.selectAll(lambdaQuery);
        users.forEach(System.out::println);
    }

    //分页查询

    @Test
    void selectByPage() {
        query.ge("age", 26);

        Page<User> page = new Page<>(1, 2);
        IPage<User> userPage = mapper.selectPage(page, query);
        System.out.println("总页数: " + userPage.getPages());
        System.out.println("总记录数: " + userPage.getTotal());
        List<User> records = userPage.getRecords();
        records.forEach(System.out::println);
    }

    @Test
    void selectByMapPage() {
        query.ge("age", 26);

        Page<Map<String,Object>> page = new Page<>(1, 2);
        IPage<Map<String, Object>> userPage = mapper.selectMapsPage(page, query);
        System.out.println("总页数: " + userPage.getPages());
        System.out.println("总记录数: " + userPage.getTotal());
        List<Map<String, Object>> records = userPage.getRecords();
        records.forEach(System.out::println);
    }

}

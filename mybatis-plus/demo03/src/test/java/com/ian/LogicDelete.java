package com.ian;
import java.util.Date;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ian.bean.User;
import com.ian.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LogicDelete {
    @Autowired
    private UserMapper userMapper;

    @Test
    void deleteById() {
        int rows = userMapper.deleteById(2346);
        System.out.println("影响行数： " + rows);
    }

    @Test
    void select() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void updateById() {
        User user = new User();
        user.setId(2345L);
        user.setAge(27);
        user.setUpdateTime(new Date());

        int rows = userMapper.updateById(user);
        System.out.println("影响行数： " + rows);
    }

    @Test
    void insert() {
        User user = new User();
        user.setName("刘三");
        user.setAge(30);
        user.setEmail("lmc@baomidou.com");
        user.setManagerId(4566L);

        int rows = userMapper.insert(user);
        System.out.println("影响行数： " + rows);
    }

    @Test
    void mySelect() {
        List<User> users = userMapper.mySelectList(Wrappers.<User>lambdaQuery().gt(User::getAge, 25));
        users.forEach(System.out::println);
    }
}

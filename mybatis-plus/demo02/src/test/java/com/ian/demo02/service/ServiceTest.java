package com.ian.demo02.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ian.demo02.bean.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void getOne() {
        User user = userService.getOne(Wrappers.<User>lambdaQuery().gt(User::getAge, 25));
        System.out.println(user);
    }

    @Test
    void Batch() {
        User user1 = new User();
        user1.setName("徐丽3");
        user1.setAge(28);

        User user2 = new User();
        user1.setName("徐丽2");
        user1.setAge(28);

        boolean saveBatch = userService.saveOrUpdateBatch(Arrays.asList(user1, user2));
        System.out.println(saveBatch);
    }

    @Test
    void chain() {
        List<User> userList = userService.lambdaQuery().gt(User::getAge, 25).like(User::getName, "雨").list();
        userList.forEach(System.out::println);
    }

}

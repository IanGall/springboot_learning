package com.ian.demo02;

import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.ian.demo02.bean.User;
import com.ian.demo02.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class Update {
    @Autowired
    private UserMapper mapper;

    @Test
    void updateByIds() {
        User user = new User();
        user.setUserId(1345927766837080065L);
        user.setAge(26);
        user.setEmail("wtf2@baomidou.com");
        int rows = mapper.updateById(user);
        System.out.println(rows);
    }

    @Test
    void updateByWrapper() {
        UpdateWrapper<User> update = Wrappers.update();
        update.eq("name", "张三").eq("age", 26);

        User user = new User();
        user.setEmail("wtf3@baomidou.com");
        user.setAge(29);

        int rows = mapper.update(user, update);
        System.out.println(rows);
    }

    @Test
    void updateByWrapper2() {
        User whereUser = new User();
        whereUser.setName("张三");

        UpdateWrapper<User> update = Wrappers.update(whereUser);
        update.eq("name", "张三").eq("age", 26);

        User user = new User();
        user.setEmail("wtf3@baomidou.com");
        user.setAge(29);

        int rows = mapper.update(user, update);
        System.out.println(rows);
    }

    @Test
    void updateByWrapper3() {
        UpdateWrapper<User> update = Wrappers.update();
        update.eq("name", "张三").eq("age", 26).set("age", 30);

        User user = new User();
        user.setEmail("wtf3@baomidou.com");
        user.setAge(29);

        int rows = mapper.update(user, update);
        System.out.println(rows);
    }

    @Test
    void updateByWrapperLambda() {
        LambdaUpdateWrapper<User> lambdaUpdate = Wrappers.lambdaUpdate();
        lambdaUpdate.eq(User::getName, "张三").eq(User::getAge, 30).set(User::getAge, 31);

        int rows = mapper.update(null, lambdaUpdate);
        System.out.println(rows);
    }

    @Test
    void updateByWrapperLambdaChain() {
        boolean update = new LambdaUpdateChainWrapper<>(mapper)
                .eq(User::getName, "张三").eq(User::getAge, 29).set(User::getAge, 31).update();

        System.out.println(update);
    }
}

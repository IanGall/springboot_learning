package com.ian.demo02;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ian.demo02.bean.User;
import com.ian.demo02.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class Delete {
    @Autowired
    private UserMapper mapper;

    @Test
    void deleteById() {
        int res = mapper.deleteById(1346357326753878017L);

        System.out.println(res);
    }

    @Test
    void deleteByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "李四");

        int res = mapper.deleteByMap(map);

        System.out.println(res);
    }

    @Test
    void deleteBatchIds() {
        int res = mapper.deleteBatchIds(Arrays.asList(1346636241804288001L, 1346636214826541057L));

        System.out.println(res);
    }

    @Test
    void deleteByWrapper() {
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.eq(User::getName, "张三");

        int res = mapper.delete(lambdaQuery);

        System.out.println(res);
    }
}

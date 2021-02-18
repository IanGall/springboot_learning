package com.ian;

import com.ian.bean.User;
import com.ian.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OptTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void updateById() {
        User select = userMapper.selectById(3456);

        User user = new User();
        user.setId(3456L);
        user.setEmail("ly2@baomidou.com");
        user.setVersion(select.getVersion());

        System.out.println(user);

        int rows = userMapper.updateById(user);
        System.out.println("影响行数： " + rows);
    }
}

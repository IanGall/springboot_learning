package com.ian.demo02;

import com.ian.demo02.bean.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ARTest {

    @Test
    void insert() {
        User user = new User();
        user.setName("张三");
        user.setAge(31);
        //user.setEmail("zs@baomidou.com");
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(new Date());
        user.setRemark("备注信息");

        boolean insert = user.insert();
        System.out.println(insert);
    }

    @Test
    void selectById() {
        User user = new User();
        user.setUserId(1346640038106591234L);

        User select = user.selectById();
        System.out.println(select);
    }

    @Test
    void updateById() {
        User user = new User();
        user.setUserId(1346640038106591234L);
        user.setName("李四");

        boolean update = user.updateById();
        System.out.println(update);
    }
}

package com.ian.demo02.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;import com.ian.demo02.bean.User;import org.apache.ibatis.annotations.Param;import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<User> selectAll(@Param(Constants.WRAPPER) Wrapper<User> wrapper);
}
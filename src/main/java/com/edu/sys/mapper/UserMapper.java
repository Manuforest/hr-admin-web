package com.edu.sys.mapper;

import com.edu.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cwq
 * @since 2023-09-18
 */
public interface UserMapper extends BaseMapper<User> {
    List<String> getRoleNameByUserId(Integer userId);
}

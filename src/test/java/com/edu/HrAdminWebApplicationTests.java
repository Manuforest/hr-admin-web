package com.edu;

import com.edu.sys.entity.Role;
import com.edu.sys.entity.User;
import com.edu.sys.mapper.RoleMapper;
import com.edu.sys.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class HrAdminWebApplicationTests {

    @Resource
    private UserMapper userMapper ;
    private RoleMapper roleMapper ;

    @Test
    void testMapper() {
        List<User> users=userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void testMapper2() {
        List<Role> roles=roleMapper.selectList(null);
        roles.forEach(System.out::println);
    }

}

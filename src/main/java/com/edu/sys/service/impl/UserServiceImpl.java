package com.edu.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.common.utils.JwtUtil;
import com.edu.sys.entity.Menu;
import com.edu.sys.entity.User;
import com.edu.sys.entity.UserRole;
import com.edu.sys.mapper.UserMapper;
import com.edu.sys.mapper.UserRoleMapper;
import com.edu.sys.service.IMenuService;
import com.edu.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cwq
 * @since 2023-09-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    //@Autowired
    //private RedisTemplate<String, User> redisTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private IMenuService menuService;

    @Override
    public Map<String, Object> login(User user) {
        //查询
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,user.getUsername());
        User loginUser = this.baseMapper.selectOne(wrapper);
        //结果不为空并且密码和传入信息匹配,生成token,用户信息存入redis
        if (loginUser != null && passwordEncoder.matches(user.getPassword(), loginUser.getPassword())){
            //之后替换成JWT
            //String key ="user:" + UUID.randomUUID();
            //存入Redis
            loginUser.setPassword(null);
            //redisTemplate.opsForValue().set(key,loginUser,30, TimeUnit.MINUTES);

            //JWT
            String token = jwtUtil.createToken(loginUser);
            //返回数据
            Map<String,Object> data = new HashMap<>();
            data.put("token",token);
            return data;
        }
        return null;
    }

    @Override
    public Map<String, Object> getUserInfo(String token) {
        //Object obj = redisTemplate.opsForValue().get(token);
        User loginUser = null;
        try {
            loginUser = jwtUtil.parseToken(token,User.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (loginUser != null){
            Map<String, Object> data = new HashMap<>();
            data.put("name",loginUser.getUsername());
            data.put("avatar",loginUser.getAvatar());

            List<String> roleList = this.baseMapper.getRoleNameByUserId(loginUser.getId());
            data.put("roles",roleList);

            List<Menu> menuList = menuService.getMenuListByUserId(loginUser.getId());
            data.put("menuList",menuList);

            return data;
        }
        return null;
    }

    @Override
    public void logout(String token) {
        //redisTemplate.delete(token);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        this.baseMapper.insert(user);
        List<Integer> roleIdList = user.getRoleIdList();
        if (roleIdList != null){
            for (Integer roleId : roleIdList){
                userRoleMapper.insert(new UserRole(null,user.getId(),roleId));
            }
        }
    }

    @Override
    public User getUserById(Integer id) {
        User user = this.baseMapper.selectById(id);
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId,id);
        List<UserRole> userRoleList = userRoleMapper.selectList(wrapper);
        List<Integer> roleIdList = userRoleList.stream()
                .map(UserRole::getRoleId)
                .collect(Collectors.toList());
        user.setRoleIdList(roleIdList);
        return user;
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        this.baseMapper.updateById(user);
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId,user.getId());
        userRoleMapper.delete(wrapper);
        List<Integer> roleIdList = user.getRoleIdList();
        if (roleIdList != null){
            for (Integer roleId : roleIdList){
                userRoleMapper.insert(new UserRole(null,user.getId(),roleId));
            }
        }
    }

    @Override
    public void deleteUserById(Integer id) {
        this.baseMapper.deleteById(id);
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId,id);
        userRoleMapper.delete(wrapper);
    }
}

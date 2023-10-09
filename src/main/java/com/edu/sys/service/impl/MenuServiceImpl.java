package com.edu.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edu.sys.entity.Menu;
import com.edu.sys.mapper.MenuMapper;
import com.edu.sys.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cwq
 * @since 2023-09-18
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public List<Menu> getAllMenu() {
        //获取父菜单
        LambdaQueryWrapper<Menu> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getParentId,0);
        List<Menu> menuList = this.list(wrapper);
        //设置子菜单
        setMenuChildren(menuList);

        return menuList;
    }

    private void setMenuChildren(List<Menu> menuList) {
        if (menuList != null) {
            for (Menu menu : menuList){
                LambdaQueryWrapper<Menu> subWrapper=new LambdaQueryWrapper<>();
                subWrapper.eq(Menu::getParentId,menu.getMenuId());
                List<Menu> subMenuList =this.list(subWrapper);
                menu.setChildren(subMenuList);
                // 多层菜单可以用递归
                setMenuChildren(subMenuList);
            }
        }
    }

    @Override
    public List<Menu> getMenuListByUserId(Integer userId) {
        List<Menu> menuList = this.baseMapper.getMenuListByUserId(userId, 0);
        setMenuChildrenByUserId(userId, menuList);
        return menuList;
    }

    private void setMenuChildrenByUserId(Integer userId, List<Menu> menuList) {
        if (menuList != null) {
            for (Menu menu : menuList){
                List<Menu> subMenuList = this.baseMapper.getMenuListByUserId(userId, menu.getMenuId());
                menu.setChildren(subMenuList);
                // 多层菜单可以用递归
                setMenuChildrenByUserId(userId, subMenuList);
            }
        }
    }

}

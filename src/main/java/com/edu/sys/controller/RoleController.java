package com.edu.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.common.vo.Result;
import com.edu.sys.entity.Role;
import com.edu.sys.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cwq
 * @since 2023-09-18
 */
@Api(tags = "角色接口列表")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @ApiOperation("查询所有角色")
    @GetMapping("/all")
    public Result<List<Role>> getAllRole(){
        List<Role> roleList= roleService.list();
        if (roleList != null) {
            return Result.success(roleList,"查询成功");
        }
        else return Result.fail("查询失败");
    }

    @ApiOperation("获取角色列表接口")
    @GetMapping("/list")
    public Result<Map<String,Object>> getRoleList(@RequestParam(value = "roleName",required = false) String roleName,
                                                  @RequestParam(value = "pageNo") Long pageNo,
                                                  @RequestParam(value = "pageSize") Long pageSize){

        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(roleName),Role::getRoleName,roleName);
        wrapper.orderByDesc(Role::getRoleId);

        Page<Role> page = new Page<>(pageNo,pageSize);
        roleService.page(page,wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());

        return Result.success(data);
    }

    @ApiOperation("增加角色接口")
    @PostMapping
    public Result<?> addRole(@RequestBody Role role){
        roleService.addRole(role);
        return Result.success("新增角色成功");
    }

    @ApiOperation("角色信息更新接口")
    @PutMapping
    public Result<?> updateRole(@RequestBody Role role){
        roleService.updateRole(role);
        return Result.success("修改角色成功");
    }

    @ApiOperation("以ID查询角色接口")
    @GetMapping("/{id}")
    public Result<Role> getRoleById(@PathVariable("id") Integer id){
        Role role = roleService.getRoleById(id);
        return Result.success(role);
    }

    @ApiOperation("角色删除接口")
    @DeleteMapping("/{id}")
    public Result<Role> deleteRoleById(@PathVariable("id") Integer id){
        roleService.deleteRoleById(id);
        return Result.success("删除角色成功!");
    }


}

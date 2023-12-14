package com.edu.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.common.vo.Result;
import com.edu.sys.entity.VolunteerWork;
import com.edu.sys.entity.VolunteerWork;
import com.edu.sys.entity.User;
import com.edu.sys.entity.VolunteerWork;
import com.edu.sys.mapper.VolunteerWorkMapper;
import com.edu.sys.service.IVolunteerWorkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cwq
 * @since 2023-12-14
 */
@RestController
@Api(tags = "志愿活动管理接口列表")
@RequestMapping("/sys/volunteerWork")
public class VolunteerWorkController {

    @Autowired
    private IVolunteerWorkService VolunteerWorkService;

    @ApiOperation("查询所有志愿活动信息")
    @GetMapping("/all")
    public Result<List<VolunteerWork>> getAllVolunteerWorks(){
        List<VolunteerWork> list= VolunteerWorkService.list();
        if (list != null) {
            return Result.success(list,"查询成功");
        }
        else return Result.fail("查询失败");
    }

    @ApiOperation("获取志愿活动信息列表接口")
    @GetMapping("/list")
    public Result<Map<String,Object>> getVolunteerWorkList(@RequestParam(value = "VolunteerWorkName",required = false) String VolunteerWorkName,
                                                  @RequestParam(value = "pageNo") Long pageNo,
                                                  @RequestParam(value = "pageSize") Long pageSize){

        LambdaQueryWrapper<VolunteerWork> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasLength(VolunteerWorkName),VolunteerWork::getVolunteerWorkName,VolunteerWorkName);
        wrapper.orderByDesc(VolunteerWork::getVolunteerWorkId);

        Page<VolunteerWork> page = new Page<>(pageNo,pageSize);
        VolunteerWorkService.page(page,wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());

        return Result.success(data);
    }

    @ApiOperation("增加志愿信息接口")
    @PostMapping
    public Result<?> addVolunteerWork(@RequestBody VolunteerWork VolunteerWork){
        VolunteerWorkService.addVolunteerWork(VolunteerWork);
        return Result.success("新增志愿活动成功");
    }

    @ApiOperation("志愿信息更新接口")
    @PutMapping
    public Result<?> updateVolunteerWork(@RequestBody VolunteerWork VolunteerWork){
        VolunteerWorkService.updateVolunteerWork(VolunteerWork);
        return Result.success("修改志愿信息成功");
    }

    @ApiOperation("以ID查询志愿信息接口")
    @GetMapping("/{id}")
    public Result<VolunteerWork> getVolunteerWorkById(@PathVariable("id") Integer id){
        VolunteerWork VolunteerWork = VolunteerWorkService.getVolunteerWorkById(id);
        return Result.success(VolunteerWork);
    }

    @ApiOperation("志愿信息删除接口")
    @DeleteMapping("/{id}")
    public Result<VolunteerWork> deleteVolunteerWorkById(@PathVariable("id") Integer id){
        VolunteerWorkService.deleteVolunteerWorkById(id);
        return Result.success("删除志愿活动成功!");
    }
}

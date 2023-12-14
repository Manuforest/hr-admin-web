package com.edu.sys.controller;

import com.edu.sys.service.IApplicationInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cwq
 * @since 2023-12-14
 */
@RestController
@Api(tags = "活动信息")
@RequestMapping("/sys/applicationInfo")
public class ApplicationInfoController {
    @Autowired
    private IApplicationInfoService  applicationInfoService;


}

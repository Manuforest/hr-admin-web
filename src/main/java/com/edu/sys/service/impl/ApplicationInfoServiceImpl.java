package com.edu.sys.service.impl;

import com.edu.sys.entity.ApplicationInfo;
import com.edu.sys.mapper.ApplicationInfoMapper;
import com.edu.sys.service.IApplicationInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cwq
 * @since 2023-12-14
 */
@Service
public class ApplicationInfoServiceImpl extends ServiceImpl<ApplicationInfoMapper, ApplicationInfo> implements IApplicationInfoService {

    @Override
    public void addApplicationInfo(ApplicationInfo applicationInfo) {
        this.baseMapper.insert(applicationInfo);
    }

}

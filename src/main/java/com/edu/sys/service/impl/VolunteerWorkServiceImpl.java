package com.edu.sys.service.impl;

import com.edu.sys.entity.VolunteerWork;
import com.edu.sys.mapper.VolunteerWorkMapper;
import com.edu.sys.service.IVolunteerWorkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.index.PathBasedRedisIndexDefinition;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cwq
 * @since 2023-12-14
 */
@Service
public class VolunteerWorkServiceImpl extends ServiceImpl<VolunteerWorkMapper, VolunteerWork> implements IVolunteerWorkService {

    @Override
    public void addVolunteerWork(VolunteerWork volunteerWork) {
        this.baseMapper.insert(volunteerWork);
    }

    @Override
    public void updateVolunteerWork(VolunteerWork volunteerWork) {
        this.baseMapper.updateById(volunteerWork);
    }

    @Override
    public VolunteerWork getVolunteerWorkById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public void deleteVolunteerWorkById(Integer id) {
        this.baseMapper.deleteById(id);
    }
}

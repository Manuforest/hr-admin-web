package com.edu.sys.service;

import com.edu.sys.entity.VolunteerWork;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cwq
 * @since 2023-12-14
 */
public interface IVolunteerWorkService extends IService<VolunteerWork> {

    void addVolunteerWork(VolunteerWork volunteerWork);

    void updateVolunteerWork(VolunteerWork volunteerWork);

    VolunteerWork getVolunteerWorkById(Integer id);

    void deleteVolunteerWorkById(Integer id);
}

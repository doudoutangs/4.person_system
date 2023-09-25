/**
 *
 */package com.person.modules.person.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.person.common.utils.PageUtils;
import com.person.modules.person.entity.MeritEntity;
import com.person.modules.person.entity.UserPlanEntity;

import java.util.Map;

/**
 * 数据字典
 *
 * @author
 */
public interface MeritService extends IService<MeritEntity> {

    PageUtils queryPage(Map<String, Object> params);
    void deleteBatch(Long[] ids);

    void update(MeritEntity record);
}

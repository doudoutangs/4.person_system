package com.person.modules.person.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.person.modules.person.entity.WorkDailyEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色管理
 *
 * @author 
 */
@Mapper
public interface WorkDailyDao extends BaseMapper<WorkDailyEntity> {
    int deleteBatchByUsers(Long[] ids);
}

package com.person.modules.person.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.person.modules.person.entity.ConvertApplyEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据字典
 *
 * @author 
 */
@Mapper
public interface ConvertApplyDao extends BaseMapper<ConvertApplyEntity> {
    int deleteBatchByUsers(Long[] ids);
}

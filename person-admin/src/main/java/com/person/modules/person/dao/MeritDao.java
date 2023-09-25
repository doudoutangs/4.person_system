package com.person.modules.person.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.person.modules.person.entity.ContractEntity;
import com.person.modules.person.entity.MeritEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据字典
 *
 * @author 
 */
@Mapper
public interface MeritDao extends BaseMapper<MeritEntity> {
	
}

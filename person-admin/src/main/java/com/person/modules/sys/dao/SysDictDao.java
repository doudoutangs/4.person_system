package com.person.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.person.modules.sys.entity.SysDictEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据字典
 *
 * @author 
 */
@Mapper
public interface SysDictDao extends BaseMapper<SysDictEntity> {
	
}

package com.person.modules.person.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.person.modules.person.entity.UserDocEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 数据字典
 *
 * @author 
 */
@Mapper
public interface UserDocDao extends BaseMapper<UserDocEntity> {
    int deleteBatchByUsers(Long[] ids);
}

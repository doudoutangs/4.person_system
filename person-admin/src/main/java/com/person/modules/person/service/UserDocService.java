package com.person.modules.person.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.person.common.utils.PageUtils;
import com.person.modules.person.entity.ContractEntity;
import com.person.modules.person.entity.UserDocEntity;

import java.util.Map;

/**
 * 数据字典
 *
 * @author 
 */
public interface UserDocService  extends IService<UserDocEntity> {

    PageUtils queryPage(Map<String, Object> params);
    void deleteBatch(Long[] ids);
    int deleteBatchByUsers(Long[] ids);

    void update(UserDocEntity record);
}

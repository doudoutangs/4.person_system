package com.person.modules.person.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.person.common.utils.PageUtils;
import com.person.modules.person.entity.WorkRecordEntity;

import java.util.Map;

/**
 * 系统日志
 *
 * @author 
 */
public interface WorkRecordService  extends IService<WorkRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
    void deleteBatch(Long[] ids);

    void update(WorkRecordEntity record);
}

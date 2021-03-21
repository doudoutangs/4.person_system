/**
 *
 *
 *
 *
 *
 */

package com.person.modules.person.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.person.common.utils.PageUtils;
import com.person.modules.person.entity.WorkDailyEntity;

import java.util.Map;

/**
 * 角色管理
 *
 * @author
 */
public interface WorkDailyService extends IService<WorkDailyEntity> {

    PageUtils queryPage(Map<String, Object> params);
    void deleteBatch(Long[] ids);
    void update(WorkDailyEntity record);
}

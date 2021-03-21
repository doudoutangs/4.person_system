/**
 * <p>
 * 员工
 * <p>
 */

package com.person.modules.person.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.person.common.utils.PageUtils;
import com.person.modules.person.entity.InterviewPlanEntity;

import java.util.Map;

/**
 * 数据字典
 *
 * @author
 */
public interface InterviewPlanService extends IService<InterviewPlanEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void deleteBatch(Long[] ids);

    void update(InterviewPlanEntity record);
}

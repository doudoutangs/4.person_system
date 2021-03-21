/**
 *
 *
 *
 *
 *
 */

package com.person.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.person.common.utils.PageUtils;
import com.person.modules.sys.entity.SysLogEntity;

import java.util.Map;


/**
 * 系统日志
 *
 * @author
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}

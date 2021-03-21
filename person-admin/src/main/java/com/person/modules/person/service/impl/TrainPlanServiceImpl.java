/**
 *
 */

package com.person.modules.person.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.person.common.utils.Constant;
import com.person.common.utils.PageUtils;
import com.person.common.utils.Query;
import com.person.modules.person.dao.TrainPlanDao;
import com.person.modules.person.entity.TrainPlanEntity;
import com.person.modules.person.service.TrainPlanService;
import com.person.modules.sys.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service("trainPlanService")
public class TrainPlanServiceImpl extends ServiceImpl<TrainPlanDao, TrainPlanEntity> implements TrainPlanService {
    @Autowired
    SysUserService userService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String title = (String) params.get("title");

        IPage<TrainPlanEntity> page = this.page(
                new Query<TrainPlanEntity>().getPage(params),
                new QueryWrapper<TrainPlanEntity>()
                        .like(StringUtils.isNotBlank(title), "title", title)
                        .apply(params.get(Constant.SQL_FILTER) != null, (String) params.get(Constant.SQL_FILTER))
        );
        return new PageUtils(page);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        this.removeByIds(Arrays.asList(ids));
    }

    @Override
    public void update(TrainPlanEntity record) {
        this.updateById(record);

    }
}

package com.person.modules.person.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.person.common.utils.Constant;
import com.person.common.utils.PageUtils;
import com.person.common.utils.Query;
import com.person.modules.person.dao.WorkDailyDao;
import com.person.modules.person.entity.WorkDailyEntity;
import com.person.modules.person.entity.WorkRecordEntity;
import com.person.modules.person.service.WorkDailyService;
import com.person.modules.sys.entity.SysUserEntity;
import com.person.modules.sys.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service("workDailyService")
public class WorkDailyServiceImpl extends ServiceImpl<WorkDailyDao, WorkDailyEntity> implements WorkDailyService {
    @Autowired
    SysUserService userService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String workDate = (String) params.get("workDate");
        String workMonth = (String) params.get("workMonth");
        Long userId = (Long) params.get("userId");

        IPage<WorkDailyEntity> page = this.page(
                new Query<WorkDailyEntity>().getPage(params),
                new QueryWrapper<WorkDailyEntity>()
                        .eq(StringUtils.isNotBlank(workDate), "work_date", workDate)
                        .eq(StringUtils.isNotBlank(workMonth), "work_month", workMonth)
                        .eq(userId != null, "user_id", userId)
                        .apply(params.get(Constant.SQL_FILTER) != null, (String) params.get(Constant.SQL_FILTER))
        );
        List<WorkDailyEntity> records = page.getRecords();

        List<WorkDailyEntity> list = new ArrayList<WorkDailyEntity>();
        for (WorkDailyEntity r : records) {
            SysUserEntity u = userService.getById(r.getUserId());
            if (u != null) {
                r.setUserName(u.getName());
            }
            list.add(r);
        }
        page.setRecords(list);
        return new PageUtils(page);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        this.removeByIds(Arrays.asList(ids));

    }

    @Override
    public void update(WorkDailyEntity record) {
        this.updateById(record);

    }
}

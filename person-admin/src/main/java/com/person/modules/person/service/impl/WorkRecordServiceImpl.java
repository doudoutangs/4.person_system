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
import com.person.modules.person.dao.WorkRecordDao;
import com.person.modules.person.entity.UserDocEntity;
import com.person.modules.person.entity.UserPlanEntity;
import com.person.modules.person.entity.WorkRecordEntity;
import com.person.modules.person.service.WorkRecordService;
import com.person.modules.sys.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service("workRecordService")
public class WorkRecordServiceImpl extends ServiceImpl<WorkRecordDao, WorkRecordEntity> implements WorkRecordService {
    @Autowired
    SysUserService userService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String workMonth = (String) params.get("workMonth");
        String workDate = (String) params.get("workDate");
        String status = (String) params.get("status");
        Long userId = (Long) params.get("userId");

        IPage<WorkRecordEntity> page = this.page(
                new Query<WorkRecordEntity>().getPage(params),
                new QueryWrapper<WorkRecordEntity>()
                        .eq(StringUtils.isNotBlank(workDate), "work_date", workDate)
                        .eq(StringUtils.isNotBlank(workMonth), "work_month", workMonth)
                        .eq(StringUtils.isNotBlank(status), "status", status)
                        .eq(userId != null, "user_id", userId)
                        .apply(params.get(Constant.SQL_FILTER) != null, (String) params.get(Constant.SQL_FILTER))
        );
        List<WorkRecordEntity> records = page.getRecords();

        List<WorkRecordEntity> list = new ArrayList<WorkRecordEntity>();
        for (WorkRecordEntity r : records) {
            r.setUserName(userService.getById(r.getUserId()).getName());
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
    public void update(WorkRecordEntity record) {
        this.updateById(record);

    }
}

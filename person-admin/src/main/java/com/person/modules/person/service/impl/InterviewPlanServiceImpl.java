/**
 * 
 *
 * 
 *
 * 
 */

package com.person.modules.person.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.person.common.utils.Constant;
import com.person.common.utils.PageUtils;
import com.person.common.utils.Query;
import com.person.modules.person.dao.InterviewPlanDao;
import com.person.modules.person.entity.InterviewPlanEntity;
import com.person.modules.person.entity.SalaryRecordEntity;
import com.person.modules.person.entity.UserDocEntity;
import com.person.modules.person.service.InterviewPlanService;
import com.person.modules.sys.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service("interviewPlanService")
public class InterviewPlanServiceImpl extends ServiceImpl<InterviewPlanDao, InterviewPlanEntity> implements InterviewPlanService {
    @Autowired
    SysUserService userService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String candidate = (String)params.get("candidate");
        String candidateMobile = (String)params.get("candidateMobile");
        IPage<InterviewPlanEntity> page = this.page(
                new Query<InterviewPlanEntity>().getPage(params),
                new QueryWrapper<InterviewPlanEntity>()
                        .like(StringUtils.isNotBlank(candidate), "candidate", candidate)
                        .like(StringUtils.isNotBlank(candidateMobile), "candidate_mobile", candidateMobile)
                        .apply(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
        );
        List<InterviewPlanEntity> records = page.getRecords();

        List<InterviewPlanEntity> list = new ArrayList<InterviewPlanEntity>();
        for (InterviewPlanEntity r : records) {
            r.setMeetName(userService.getById(r.getMeetUserId()).getName());
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
    public void update(InterviewPlanEntity record) {
        this.updateById(record);
    }
}

package com.person.modules.person.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.person.common.utils.Constant;
import com.person.common.utils.PageUtils;
import com.person.common.utils.Query;
import com.person.modules.person.dao.UserPlanDao;
import com.person.modules.person.entity.UserPlanEntity;
import com.person.modules.person.entity.WorkDailyEntity;
import com.person.modules.person.service.UserPlanService;
import com.person.modules.sys.entity.SysUserEntity;
import com.person.modules.sys.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service("userPlanService")
public class UserPlanServiceImpl extends ServiceImpl<UserPlanDao, UserPlanEntity> implements UserPlanService {
    @Autowired
    SysUserService userService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = (String) params.get("name");
        Long userId = (Long) params.get("userId");

        IPage<UserPlanEntity> page = this.page(
                new Query<UserPlanEntity>().getPage(params),
                new QueryWrapper<UserPlanEntity>()
                        .like(StringUtils.isNotBlank(name), "name", name)
                        .eq(userId != null,"user_id", userId)
                        .apply(params.get(Constant.SQL_FILTER) != null, (String) params.get(Constant.SQL_FILTER))
        );
        List<UserPlanEntity> records = page.getRecords();

        List<UserPlanEntity> list = new ArrayList<UserPlanEntity>();
        for (UserPlanEntity r : records) {
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
    public void update(UserPlanEntity record) {
        this.updateById(record);

    }
}

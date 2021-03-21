/**
 *
 */

package com.person.modules.person.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.person.common.utils.Constant;
import com.person.common.utils.DateUtils;
import com.person.common.utils.PageUtils;
import com.person.common.utils.Query;
import com.person.modules.person.dao.UserDocDao;
import com.person.modules.person.entity.SalaryRecordEntity;
import com.person.modules.person.entity.UserDocEntity;
import com.person.modules.person.service.UserDocService;
import com.person.modules.sys.entity.SysDeptEntity;
import com.person.modules.sys.service.SysDeptService;
import com.person.modules.sys.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;


@Service("userDocService")
public class UserDocServiceImpl extends ServiceImpl<UserDocDao, UserDocEntity> implements UserDocService {
    @Autowired
    SysUserService userService;
    @Autowired
    SysDeptService sysDeptService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Object id = params.get("userId");
        Long userId = null;
        if (!ObjectUtils.isEmpty(id)) {
            userId = (Long) id;
        }
        Object dId = params.get("deptId");
        Long deptId = null;
        if (!ObjectUtils.isEmpty(dId)) {
            deptId = Long.parseLong(dId.toString());
        }

        IPage<UserDocEntity> page = this.page(
                new Query<UserDocEntity>().getPage(params),
                new QueryWrapper<UserDocEntity>()
                        .eq(null != userId, "user_id", userId)
                        .eq(null != deptId, "dept_id", deptId)
                        .apply(params.get(Constant.SQL_FILTER) != null, (String) params.get(Constant.SQL_FILTER))

        );
        List<UserDocEntity> records = page.getRecords();

        List<UserDocEntity> list = new ArrayList<UserDocEntity>();
        for (UserDocEntity r : records) {
            String birthYear = r.getBirth().substring(0, 4);
            String year = DateUtils.format(new Date(), "YYYY");
            int age = Integer.valueOf(year) - Integer.valueOf(birthYear);
            r.setAge(age);
            r.setUserName(userService.getById(r.getUserId()).getName());
            SysDeptEntity sysDeptEntity = sysDeptService.getById(r.getDeptId());
            r.setDeptName(sysDeptEntity.getName());
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
    public void update(UserDocEntity record) {
        this.updateById(record);
    }
}

package com.person.modules.person.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.person.common.utils.Constant;
import com.person.common.utils.PageUtils;
import com.person.common.utils.Query;
import com.person.modules.person.dao.SalaryRecordDao;
import com.person.modules.person.entity.ConvertApplyEntity;
import com.person.modules.person.entity.SalaryRecordEntity;
import com.person.modules.person.service.SalaryRecordService;
import com.person.modules.sys.entity.SysDeptEntity;
import com.person.modules.sys.entity.SysUserEntity;
import com.person.modules.sys.service.SysDeptService;
import com.person.modules.sys.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("salaryRecordService")
public class SalaryRecordServiceImpl extends ServiceImpl<SalaryRecordDao, SalaryRecordEntity> implements SalaryRecordService {
    @Autowired
    SysUserService userService;
    @Autowired
    SysDeptService deptService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        IPage<SalaryRecordEntity> page = this.page(
                new Query<SalaryRecordEntity>().getPage(params),
                getQueryWrapper(params)
                        .apply(params.get(Constant.SQL_FILTER) != null, (String) params.get(Constant.SQL_FILTER))
        );
        List<SalaryRecordEntity> records = page.getRecords();

        List<SalaryRecordEntity> list = new ArrayList<SalaryRecordEntity>();
        for (SalaryRecordEntity r : records) {
            SysUserEntity user = userService.getById(r.getUserId());
            SysDeptEntity dept = deptService.getById(user.getDeptId());
            if (dept != null) {
                r.setDeptName(dept.getName());
            }
            if (user != null) {
                r.setUserName(user.getName());
            }
            list.add(r);
        }
        page.setRecords(list);
        return new PageUtils(page);
    }

    private QueryWrapper getQueryWrapper(Map<String, Object> params) {
        String salaryMonth = (String) params.get("salaryMonth");
        Long userId = (Long) params.get("userId");
        QueryWrapper<SalaryRecordEntity> q = new QueryWrapper<>();

        //查询部门下的员工
        Object dId = params.get("deptId");
        if (null != dId&&StringUtils.isNotBlank(dId.toString())) {
            Long deptId = Long.parseLong(dId.toString());
            QueryWrapper query = new QueryWrapper();
            query.eq("dept_id", deptId);
            StringBuffer users = new StringBuffer();
            List<SysUserEntity> list = userService.list(query);
            String userIds = "";
            if (!CollectionUtils.isEmpty(list)) {
                for (SysUserEntity u : list) {
                    users.append(u.getUserId()).append(",");
                }
                if (StringUtils.isNotBlank(users.toString())) {
                    userIds = users.substring(0, users.lastIndexOf(","));
                }
            }
            q.in(StringUtils.isNotBlank(userIds), "user_id", userIds);
        }
        q.eq(StringUtils.isNotBlank(salaryMonth), "salary_month", salaryMonth);
        q.eq(userId != null, "user_id", userId);
        return q;
    }


    @Override
    public void deleteBatch(Long[] ids) {
        this.removeByIds(Arrays.asList(ids));

    }

    @Override
    public void update(SalaryRecordEntity record) {
        this.updateById(record);
    }
}

package com.person.modules.person.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.person.common.utils.Constant;
import com.person.common.utils.PageUtils;
import com.person.common.utils.Query;
import com.person.modules.person.dao.ConvertApplyDao;
import com.person.modules.person.entity.ConvertApplyEntity;
import com.person.modules.person.service.ConvertApplyService;
import com.person.modules.sys.entity.SysUserEntity;
import com.person.modules.sys.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service("convertApplyService")
public class ConvertApplyServiceImpl extends ServiceImpl<ConvertApplyDao, ConvertApplyEntity> implements ConvertApplyService {
    @Autowired
    SysUserService userService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Object applyId = params.get("applyUserId");
        Long applyUserId = applyId == null ? null : Long.valueOf(applyId.toString());

        IPage<ConvertApplyEntity> page = this.page(
                new Query<ConvertApplyEntity>().getPage(params),
                new QueryWrapper<ConvertApplyEntity>().
                        eq((applyUserId != null && applyUserId != -1), "apply_user_id", applyUserId)
                        .apply(params.get(Constant.SQL_FILTER) != null, (String) params.get(Constant.SQL_FILTER))
        );
        List<ConvertApplyEntity> records = page.getRecords();
        List<ConvertApplyEntity> list = new ArrayList<ConvertApplyEntity>();
        for (ConvertApplyEntity r : records) {
            Long userId = r.getApplyUserId();
            Long approvalUserId = r.getApprovalUserId();
            if (null != userId) {
                SysUserEntity u = userService.getById(userId);
                if (u != null) {
                    r.setApplyName(u.getName());
                }
            }
            if (null != approvalUserId) {
                SysUserEntity u = userService.getById(approvalUserId);
                if (u != null) {
                    r.setApprovalName(u.getName());
                }
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
    public void update(ConvertApplyEntity record) {
        this.updateById(record);
    }
}

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
import com.person.modules.person.dao.MeritDao;
import com.person.modules.person.entity.MeritEntity;
import com.person.modules.person.service.MeritService;
import com.person.modules.sys.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service("meritService")
public class MeritServiceImpl extends ServiceImpl<MeritDao, MeritEntity> implements MeritService {
    @Autowired
    SysUserService userService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        IPage<MeritEntity> page = this.page(
                new Query<MeritEntity>().getPage(params),
                new QueryWrapper<MeritEntity>()
//                        .like(StringUtils.isNotBlank(name), "name", name)
//                        .eq(userId != null,"user_id", userId)
                        .apply(params.get(Constant.SQL_FILTER) != null, (String) params.get(Constant.SQL_FILTER))
        );
        return new PageUtils(page);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        this.removeByIds(Arrays.asList(ids));
    }

    @Override
    public void update(MeritEntity record) {
        this.updateById(record);

    }
}

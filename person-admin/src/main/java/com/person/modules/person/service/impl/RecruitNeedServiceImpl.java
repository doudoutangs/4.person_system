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
import com.person.common.utils.PageUtils;
import com.person.common.utils.Query;
import com.person.modules.person.dao.RecruitNeedDao;
import com.person.modules.person.entity.RecruitNeedEntity;
import com.person.modules.person.entity.UserDocEntity;
import com.person.modules.person.service.RecruitNeedService;
import com.person.modules.sys.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service("recruitNeedService")
public class RecruitNeedServiceImpl extends ServiceImpl<RecruitNeedDao, RecruitNeedEntity> implements RecruitNeedService {
    @Autowired
    SysUserService userService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String status = (String)params.get("status");

        IPage<RecruitNeedEntity> page = this.page(
            new Query<RecruitNeedEntity>().getPage(params),
            new QueryWrapper<RecruitNeedEntity>().eq(StringUtils.isNotBlank(status),"status", status)
        );
        List<RecruitNeedEntity> records = page.getRecords();

        List<RecruitNeedEntity> list = new ArrayList<RecruitNeedEntity>();
        for (RecruitNeedEntity r : records) {
            Long needUserId = r.getNeedUserId();
            Long recruitUserId = r.getRecruitUserId();
            if(null != needUserId){
                r.setNeedName(userService.getById(needUserId).getName());
            }
            if(null != recruitUserId){
                r.setRecruitName(userService.getById(recruitUserId).getName());
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
    public void update(RecruitNeedEntity record) {
        this.updateById(record);
    }
}

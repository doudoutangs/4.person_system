/**
 *
 */

package com.person.modules.person.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.person.common.annotation.SysLog;
import com.person.common.utils.DateUtils;
import com.person.common.utils.PageUtils;
import com.person.common.utils.R;
import com.person.common.validator.ValidatorUtils;
import com.person.modules.person.entity.WorkDailyEntity;
import com.person.modules.person.service.WorkDailyService;
import com.person.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.person.modules.sys.shiro.ShiroUtils.getUserId;

/**
 * 日报
 *
 * @author
 */
@RestController
@RequestMapping("/person/daily")

public class WorkDailyController extends AbstractController {
    @Autowired
    private WorkDailyService workDailyService;

    /**
     * 所有日报列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("person:daily:list")
    public R list(@RequestParam Map<String, Object> params) {
        if (getUserId() != 1) {
            //不是管理员只能查看自己工资记录
            params.put("userId", getUserId());
        }
        PageUtils page = workDailyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 日报信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("person:daily:info")
    @ResponseBody
    public R info(@PathVariable("id") Long id) {
        WorkDailyEntity daily = workDailyService.getById(id);

        return R.ok().put("daily", daily);
    }

    /**
     * 保存日报
     */
    @SysLog("保存日报")
    @RequestMapping("/save")
    @RequiresPermissions("person:daily:save")
    public R save(@RequestBody WorkDailyEntity daily) {
        ValidatorUtils.validateEntity(daily);
        Long userId = getUserId();

        //查询当日记录是否已存在
        WorkDailyEntity d = new WorkDailyEntity();
        d.setUserId(userId);
        String workDate = daily.getWorkDate();
        d.setWorkDate(workDate);
        QueryWrapper q = new QueryWrapper();
        q.setEntity(d);
        WorkDailyEntity one = workDailyService.getOne(q);
        if (null != one) {
            return R.error(workDate + "日已上报日报");
        }
        daily.setUserId(userId);
        daily.setCreateTime(DateUtils.currentTimeFormat());
        daily.setWorkMonth(workDate.substring(0, 7));
        workDailyService.save(daily);

        return R.ok();
    }

    /**
     * 修改日报
     */
    @SysLog("修改日报")
    @RequestMapping("/update")
    @RequiresPermissions("person:daily:update")
    public R update(@RequestBody WorkDailyEntity daily) {
        ValidatorUtils.validateEntity(daily);
        daily.setUpdateTime(DateUtils.currentTimeFormat());
        workDailyService.update(daily);

        return R.ok();
    }

    /**
     * 删除日报
     */
    @SysLog("删除日报")
    @RequestMapping("/delete")
    @RequiresPermissions("person:daily:delete")
    public R delete(@RequestBody Long[] ids) {
        workDailyService.deleteBatch(ids);
        return R.ok();
    }

}
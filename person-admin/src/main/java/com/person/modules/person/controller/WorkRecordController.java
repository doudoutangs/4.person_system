/**
 *
 */package com.person.modules.person.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.person.common.annotation.SysLog;
import com.person.common.utils.DateUtils;
import com.person.common.utils.PageUtils;
import com.person.common.utils.R;
import com.person.common.validator.ValidatorUtils;
import com.person.modules.person.constant.WorkStatusEnum;
import com.person.modules.person.entity.WorkDailyEntity;
import com.person.modules.person.entity.WorkRecordEntity;
import com.person.modules.person.service.WorkRecordService;
import com.person.modules.sys.controller.AbstractController;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 考勤管理
 *
 * @author
 */
@RestController
@RequestMapping("/person/work")
public class WorkRecordController extends AbstractController {
    @Autowired
    private WorkRecordService workRecordService;
    @Value("${work-up-time}")
    String upTimeStr;

    @Value("${work-down-time}")
    String downTimeStr;
    @Value("${work-over-time}")
    String overTimeStr;

    /**
     * 所有考勤列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("person:work:list")
    public R list(@RequestParam Map<String, Object> params) {
        if(getRoleId() == 5){
            //不是管理员只能查看自己工资记录
            params.put("userId", getUserId());
        }
        PageUtils page = workRecordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 考勤信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("person:work:info")
    @ResponseBody
    public R info(@PathVariable("id") Long id) {
        WorkRecordEntity work = workRecordService.getById(id);

        return R.ok().put("work", work);
    }

    /**
     * 保存考勤
     */
    @SysLog("保存考勤")
    @RequestMapping("/save")
    @RequiresPermissions("person:work:save")
    public R save(@RequestBody WorkRecordEntity work) {
        ValidatorUtils.validateEntity(work);
        Long userId = getUserId();

        //查询当日记录是否已存在
        String workDate = work.getWorkDate();
        if(StringUtils.isBlank(workDate)){
            return R.error( "工作日期不能为空");
        }
        if(StringUtils.isBlank(work.getUpTime())&&StringUtils.isBlank(work.getDownTime())){
            return R.error( "时间不能为空");
        }
        WorkRecordEntity d = new WorkRecordEntity();
        d.setUserId(userId);
        d.setWorkDate(workDate);
        QueryWrapper q = new QueryWrapper();
        q.setEntity(d);
        WorkRecordEntity one = workRecordService.getOne(q);
        if (null != one) {
            return R.error(workDate + "日已打卡");
        }
        int upTime = Integer.valueOf(upTimeStr);
        Integer up = Integer.valueOf(work.getUpTime().substring(0, 2));
        if (up > upTime || (up == upTime && (Integer.valueOf(work.getUpTime().substring(3, 5))) > 0)) {
            work.setStatus(WorkStatusEnum.LATE.getCode());
        }
        work.setUserId(userId);
        work.setCreateTime(DateUtils.currentTimeFormat());
        work.setWorkMonth(workDate.substring(0,7));
        workRecordService.save(work);
        return R.ok();
    }

    /**
     * 修改考勤
     */
    @SysLog("修改考勤")
    @RequestMapping("/update")
    @RequiresPermissions("person:work:update")
    public R update(@RequestBody WorkRecordEntity work) {
        ValidatorUtils.validateEntity(work);
        work.setUpdateTime(DateUtils.currentTimeFormat());
        int downTime = Integer.valueOf(downTimeStr);
        int overTime = Integer.valueOf(overTimeStr);
        Integer down = Integer.valueOf(work.getDownTime().substring(0, 2));
        if (down < downTime) {
            work.setStatus(WorkStatusEnum.LEAVE.getCode());
        } else if (down >= overTime) {
            work.setStatus(WorkStatusEnum.OVERTIME.getCode());
        }

        workRecordService.update(work);

        return R.ok();
    }

    /**
     * 删除考勤
     */
    @SysLog("删除考勤")
    @RequestMapping("/delete")
    @RequiresPermissions("person:work:delete")
    public R delete(@RequestBody Long[] ids) {
        workRecordService.deleteBatch(ids);
        return R.ok();
    }

}
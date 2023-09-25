/**
 *
 */package com.person.modules.person.controller;

import com.person.common.annotation.SysLog;
import com.person.common.utils.DateUtils;
import com.person.common.utils.PageUtils;
import com.person.common.utils.R;
import com.person.common.validator.ValidatorUtils;
import com.person.modules.person.entity.UserPlanEntity;
import com.person.modules.person.service.UserPlanService;
import com.person.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 个人计划
 */
@RestController
@RequestMapping("/person/plan")
public class UserPlanController extends AbstractController {
    @Autowired
    private UserPlanService userPlanService;

    /**
     * 所有个人计划列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("person:plan:list")
    public R list(@RequestParam Map<String, Object> params) {
        if(getRoleId() == 5){
            //不是管理员只能查看自己工资记录
            params.put("userId", getUserId());
        }
        PageUtils page = userPlanService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 个人计划信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("person:plan:info")
    @ResponseBody
    public R info(@PathVariable("id") Long id) {
        UserPlanEntity plan = userPlanService.getById(id);

        return R.ok().put("plan", plan);
    }

    /**
     * 保存个人计划
     */
    @SysLog("保存个人计划")
    @RequestMapping("/save")
    @RequiresPermissions("person:plan:save")
    public R save(@RequestBody UserPlanEntity plan) {
        ValidatorUtils.validateEntity(plan);
        plan.setUserId(getUserId());
        plan.setCreateTime(DateUtils.currentTimeFormat());
        userPlanService.save(plan);

        return R.ok();
    }

    /**
     * 修改个人计划
     */
    @SysLog("修改个人计划")
    @RequestMapping("/update")
    @RequiresPermissions("person:plan:update")
    public R update(@RequestBody UserPlanEntity plan) {
        ValidatorUtils.validateEntity(plan);
        plan.setUpdateTime(DateUtils.currentTimeFormat());

        userPlanService.update(plan);

        return R.ok();
    }

    /**
     * 删除个人计划
     */
    @SysLog("删除个人计划")
    @RequestMapping("/delete")
    @RequiresPermissions("person:plan:delete")
    public R delete(@RequestBody Long[] ids) {
        userPlanService.deleteBatch(ids);
        return R.ok();
    }

}
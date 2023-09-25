package com.person.modules.sys.controller;


import com.person.common.annotation.SysLog;
import com.person.common.utils.PageUtils;
import com.person.common.utils.R;
import com.person.common.validator.Assert;
import com.person.common.validator.ValidatorUtils;
import com.person.common.validator.group.AddGroup;
import com.person.common.validator.group.UpdateGroup;
import com.person.modules.person.dao.*;
import com.person.modules.person.service.UserDocService;
import com.person.modules.sys.entity.SysUserEntity;
import com.person.modules.sys.service.SysUserRoleService;
import com.person.modules.sys.service.SysUserService;
import com.person.modules.sys.shiro.ShiroUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private UserDocService userDocService;

    /**
     * 所有用户列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("sys:user:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysUserService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 所有用户列表
     */
    @RequestMapping("/users")
    public R users() {
        List<SysUserEntity> list = sysUserService.list();
        return R.ok().put("users", list);

    }

    /**
     * 获取登录的用户信息
     */
    @RequestMapping("/info")
    public R info() {
        return R.ok().put("user", getUser());
    }

    /**
     * 修改登录用户密码
     */
    @SysLog("修改密码")
    @RequestMapping("/password")
    public R password(String password, String newPassword) {
        Assert.isBlank(newPassword, "新密码不为能空");

        //原密码
        password = ShiroUtils.sha256(password, getUser().getSalt());
        //新密码
        newPassword = ShiroUtils.sha256(newPassword, getUser().getSalt());

        //更新密码
        boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
        if (!flag) {
            return R.error("原密码不正确");
        }

        return R.ok();
    }

    /**
     * 用户信息
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("sys:user:info")
    public R info(@PathVariable("userId") Long userId) {
        SysUserEntity user = sysUserService.getById(userId);

        //获取用户所属的角色列表
        List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);

        return R.ok().put("user", user);
    }

    /**
     * 保存用户
     */
    @SysLog("保存用户")
    @RequestMapping("/save")
    @RequiresPermissions("sys:user:save")
    public R save(@RequestBody SysUserEntity user) {
        ValidatorUtils.validateEntity(user, AddGroup.class);

        sysUserService.saveUser(user);

        return R.ok();
    }

    /**
     * 修改用户
     */
    @SysLog("修改用户")
    @RequestMapping("/update")
    @RequiresPermissions("sys:user:update")
    public R update(@RequestBody SysUserEntity user) {
        ValidatorUtils.validateEntity(user, UpdateGroup.class);

        sysUserService.update(user);

        return R.ok();
    }

    @Autowired
    ConvertApplyDao convertApplyDao;
    @Autowired
    InterviewPlanDao interviewPlanDao;
    @Autowired
    RecruitNeedDao recruitNeedDao;
    @Autowired
    SalaryRecordDao salaryRecordDao;

    @Autowired
    UserPlanDao userPlanDao;

    @Autowired
    WorkDailyDao workDailyDao;

    @Autowired
    WorkRecordDao workRecordDao;

    /**
     * 删除用户
     */
    @SysLog("删除用户")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    public R delete(@RequestBody Long[] userIds) {
        if (ArrayUtils.contains(userIds, 1L)) {
            return R.error("系统管理员不能删除");
        }

        if (ArrayUtils.contains(userIds, getUserId())) {
            return R.error("当前用户不能删除");
        }
        convertApplyDao.deleteBatchByUsers(userIds);
        interviewPlanDao.deleteBatchByUsers(userIds);
        recruitNeedDao.deleteBatchByUsers(userIds);
        salaryRecordDao.deleteBatchByUsers(userIds);
        userPlanDao.deleteBatchByUsers(userIds);
        workDailyDao.deleteBatchByUsers(userIds);
        workRecordDao.deleteBatchByUsers(userIds);
        int result = userDocService.deleteBatchByUsers(userIds);
        if (userIds.length == result) {
            sysUserService.removeByIds(Arrays.asList(userIds));
        }

        return R.ok();
    }
}

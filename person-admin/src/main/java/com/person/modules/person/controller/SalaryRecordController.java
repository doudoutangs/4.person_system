/**
 *
 */package com.person.modules.person.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.person.common.annotation.SysLog;
import com.person.common.utils.DateUtils;
import com.person.common.utils.PageUtils;
import com.person.common.utils.R;
import com.person.common.validator.ValidatorUtils;
import com.person.modules.person.entity.SalaryRecordEntity;
import com.person.modules.person.entity.WorkRecordEntity;
import com.person.modules.person.service.SalaryRecordService;
import com.person.modules.sys.controller.AbstractController;
import com.person.modules.sys.entity.SysUserEntity;
import com.person.modules.sys.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 工资
 *
 * @author
 */
@RestController
@RequestMapping("/person/salary")
public class SalaryRecordController extends AbstractController {
    @Autowired
    private SalaryRecordService salaryRecordService;
    @Autowired
    SysUserService userService;
    /**
     * 所有工资列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("person:salary:list")
    public R list(@RequestParam Map<String, Object> params) {
        if(getRoleId() == 5){
            //不是管理员只能查看自己工资记录
            params.put("userId", getUserId());
        }

        PageUtils page = salaryRecordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 工资信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("person:salary:info")
    @ResponseBody
    public R info(@PathVariable("id") Long id) {
        SalaryRecordEntity salary = salaryRecordService.getById(id);

        return R.ok().put("salary", salary);
    }

    /**
     * 保存工资
     */
    @SysLog("保存工资")
    @RequestMapping("/save")
    @RequiresPermissions("person:salary:save")
    public R save(@RequestBody SalaryRecordEntity salary) {
        ValidatorUtils.validateEntity(salary);
        salary.setCreateTime(DateUtils.currentTimeFormat());
        //查询当日记录是否已存在
        SalaryRecordEntity d = new SalaryRecordEntity();
        String salaryMonth = salary.getSalaryMonth();
        d.setUserId(salary.getUserId());
        d.setSalaryMonth(salaryMonth);
        QueryWrapper q = new QueryWrapper();
        q.setEntity(d);
        SalaryRecordEntity one = salaryRecordService.getOne(q);
        if (null != one) {
            return R.error(salaryMonth + "月工资已核算");
        }
        salaryRecordService.save(salary);
        return R.ok();
    }

    /**
     * 修改工资
     */
    @SysLog("修改工资")
    @RequestMapping("/update")
    @RequiresPermissions("person:salary:update")
    public R update(@RequestBody SalaryRecordEntity salary) {
        ValidatorUtils.validateEntity(salary);
        salaryRecordService.update(salary);

        return R.ok();
    }

    /**
     * 删除工资
     */
    @SysLog("删除工资")
    @RequestMapping("/delete")
    @RequiresPermissions("person:salary:delete")
    public R delete(@RequestBody Long[] ids) {
        salaryRecordService.deleteBatch(ids);
        return R.ok();
    }

}
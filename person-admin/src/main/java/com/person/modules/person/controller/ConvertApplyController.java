/**
 *
 */package com.person.modules.person.controller;


import com.person.common.annotation.SysLog;
import com.person.common.utils.DateUtils;
import com.person.common.utils.PageUtils;
import com.person.common.utils.R;
import com.person.common.validator.ValidatorUtils;
import com.person.modules.person.constant.ApplyStatusEnum;
import com.person.modules.person.constant.ApprovalStatusEnum;
import com.person.modules.person.entity.ConvertApplyEntity;
import com.person.modules.person.service.ConvertApplyService;
import com.person.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 系统申请信息
 *
 * @author
 */
@RestController
@RequestMapping("/person/apply")
public class ConvertApplyController extends AbstractController {
    @Autowired
    private ConvertApplyService convertApplyService;

    /**
     * 所有申请列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("person:apply:list")
    public R list(@RequestParam Map<String, Object> params) {
        if(getRoleId() == 5){
            //不是管理员只能查看自己工资记录
            params.put("applyUserId", getUserId());
        }
        PageUtils page = convertApplyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 申请信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("person:apply:info")
    @ResponseBody
    public R info(@PathVariable("id") Long id) {
        ConvertApplyEntity apply = convertApplyService.getById(id);

        return R.ok().put("apply", apply);
    }

    /**
     * 保存申请
     */
    @SysLog("保存申请")
    @RequestMapping("/save")
    @RequiresPermissions("person:apply:save")
    public R save(@RequestBody ConvertApplyEntity apply) {
        ValidatorUtils.validateEntity(apply);
        apply.setApplyUserId(getUserId());
        apply.setCreateTime(DateUtils.currentTimeFormat());
        convertApplyService.save(apply);

        return R.ok();
    }

    /**
     * 修改申请
     */
    @SysLog("修改申请")
    @RequestMapping("/update")
    @RequiresPermissions("person:apply:update")
    public R update(@RequestBody ConvertApplyEntity apply) {
        ValidatorUtils.validateEntity(apply);
        if (!apply.getStatus() .equals(ApplyStatusEnum.WAIT.getCode())) {
            return R.error("已审批不可修改");
        }
        apply.setUpdateTime(DateUtils.currentTimeFormat());
        convertApplyService.update(apply);
        return R.ok();
    }

    /**
     * 审核
     */
    @SysLog("审核")
    @RequestMapping("/approval")
    @RequiresPermissions("person:apply:approval")
    public R approval(@RequestBody ConvertApplyEntity apply) {
        ValidatorUtils.validateEntity(apply);
        if(apply.getApprovalUserId()==getUserId()){
            return R.error("自己不能审批自己的申请");
        }
        if(ApprovalStatusEnum.ADOPT.getCode()== apply.getApprovalResult()){
            apply.setStatus(ApplyStatusEnum.ADOPT.getCode());
        }else{
            apply.setStatus(ApplyStatusEnum.FAIL.getCode());
        }
        apply.setApprovalUserId(getUserId());
        apply.setUpdateTime(DateUtils.currentTimeFormat());
        convertApplyService.update(apply);
        return R.ok();
    }

    /**
     * 删除申请
     */
    @SysLog("删除申请")
    @RequestMapping("/delete")
    @RequiresPermissions("person:apply:delete")
    public R delete(@RequestBody Long[] ids) {
        convertApplyService.deleteBatch(ids);
        return R.ok();
    }

}

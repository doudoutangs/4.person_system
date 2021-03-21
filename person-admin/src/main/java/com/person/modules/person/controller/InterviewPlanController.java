/**
 * 
 *
 * 
 *
 * 
 */

package com.person.modules.person.controller;

import com.person.common.annotation.SysLog;
import com.person.common.utils.DateUtils;
import com.person.common.utils.PageUtils;
import com.person.common.utils.R;
import com.person.common.validator.ValidatorUtils;
import com.person.modules.person.entity.InterviewPlanEntity;
import com.person.modules.person.service.InterviewPlanService;
import com.person.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 面试管理
 *
 * @author 
 */
@RestController
@RequestMapping("/person/interview")
public class InterviewPlanController extends AbstractController {
	@Autowired
	private InterviewPlanService interviewPlanService;

	/**
	 * 所有面试列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("person:apply:list")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = interviewPlanService.queryPage(params);

		return R.ok().put("page", page);
	}


	/**
	 * 面试信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("person:apply:info")
	@ResponseBody
	public R info(@PathVariable("id") Long id){
		InterviewPlanEntity interview = interviewPlanService.getById(id);

		return R.ok().put("interview", interview);
	}

	/**
	 * 保存面试
	 */
	@SysLog("保存面试")
	@RequestMapping("/save")
	@RequiresPermissions("person:apply:save")
	public R save(@RequestBody InterviewPlanEntity apply){
		ValidatorUtils.validateEntity(apply);
		apply.setCreateTime(DateUtils.currentTimeFormat() );
		interviewPlanService.save(apply);

		return R.ok();
	}

	/**
	 * 修改面试
	 */
	@SysLog("修改面试")
	@RequestMapping("/update")
	@RequiresPermissions("person:apply:update")
	public R update(@RequestBody InterviewPlanEntity apply){
		ValidatorUtils.validateEntity(apply);
		interviewPlanService.update(apply);

		return R.ok();
	}

	/**
	 * 删除面试
	 */
	@SysLog("删除面试")
	@RequestMapping("/delete")
	@RequiresPermissions("person:apply:delete")
	public R delete(@RequestBody Long[] ids){
		interviewPlanService.deleteBatch(ids);
		return R.ok();
	}
}

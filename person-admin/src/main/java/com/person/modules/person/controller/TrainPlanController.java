package com.person.modules.person.controller;


import com.person.common.annotation.SysLog;
import com.person.common.utils.PageUtils;
import com.person.common.utils.R;
import com.person.common.validator.ValidatorUtils;
import com.person.modules.person.entity.TrainPlanEntity;
import com.person.modules.person.service.TrainPlanService;
import com.person.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 培训计划
 *
 * @author 
 */
@RestController
@RequestMapping("/person/train")
public class TrainPlanController extends AbstractController {
	@Autowired
	private TrainPlanService trainPlanService;

	/**
	 * 所有培训计划列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("person:train:list")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = trainPlanService.queryPage(params);

		return R.ok().put("page", page);
	}


	/**
	 * 培训计划信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("person:train:info")
	@ResponseBody
	public R info(@PathVariable("id") Long id){
		TrainPlanEntity train = trainPlanService.getById(id);

		return R.ok().put("train", train);
	}

	/**
	 * 保存培训计划
	 */
	@SysLog("保存培训计划")
	@RequestMapping("/save")
	@RequiresPermissions("person:train:save")
	public R save(@RequestBody TrainPlanEntity train){
		ValidatorUtils.validateEntity(train);
		trainPlanService.save(train);
		return R.ok();
	}

	/**
	 * 修改培训计划
	 */
	@SysLog("修改培训计划")
	@RequestMapping("/update")
	@RequiresPermissions("person:train:update")
	public R update(@RequestBody TrainPlanEntity train){
		ValidatorUtils.validateEntity(train);
		trainPlanService.update(train);
		return R.ok();
	}

	/**
	 * 删除培训计划
	 */
	@SysLog("删除培训计划")
	@RequestMapping("/delete")
	@RequiresPermissions("person:train:delete")
	public R delete(@RequestBody Long[] ids){
		trainPlanService.deleteBatch(ids);
		return R.ok();
	}

}
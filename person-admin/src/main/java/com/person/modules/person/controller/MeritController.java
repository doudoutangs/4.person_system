package com.person.modules.person.controller;


import com.person.common.annotation.SysLog;
import com.person.common.utils.PageUtils;
import com.person.common.utils.R;
import com.person.common.validator.ValidatorUtils;
import com.person.modules.person.entity.MeritEntity;
import com.person.modules.person.service.MeritService;
import com.person.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 绩效设置
 *
 * @author 
 */
@RestController
@RequestMapping("/person/merit")
public class MeritController extends AbstractController {
	@Autowired
	private MeritService meritService;

	/**
	 * 所有绩效设置列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("person:merit:list")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = meritService.queryPage(params);

		return R.ok().put("page", page);
	}


	/**
	 * 绩效设置信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("person:merit:info")
	@ResponseBody
	public R info(@PathVariable("id") Long id){
		MeritEntity merit = meritService.getById(id);

		return R.ok().put("merit", merit);
	}

	/**
	 * 保存绩效设置
	 */
	@SysLog("保存绩效设置")
	@RequestMapping("/save")
	@RequiresPermissions("person:merit:save")
	public R save(@RequestBody MeritEntity merit){
		ValidatorUtils.validateEntity(merit);
		meritService.save(merit);
		return R.ok();
	}

	/**
	 * 修改绩效设置
	 */
	@SysLog("修改绩效设置")
	@RequestMapping("/update")
	@RequiresPermissions("person:merit:update")
	public R update(@RequestBody MeritEntity merit){
		ValidatorUtils.validateEntity(merit);
		meritService.update(merit);
		return R.ok();
	}

	/**
	 * 删除绩效设置
	 */
	@SysLog("删除绩效设置")
	@RequestMapping("/delete")
	@RequiresPermissions("person:merit:delete")
	public R delete(@RequestBody Long[] ids){
		meritService.deleteBatch(ids);
		return R.ok();
	}

}
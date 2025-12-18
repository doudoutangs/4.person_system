package com.person.modules.person.controller;


import com.person.common.annotation.SysLog;
import com.person.common.utils.PageUtils;
import com.person.common.utils.R;
import com.person.common.validator.ValidatorUtils;
import com.person.modules.person.entity.ContractEntity;
import com.person.modules.person.service.ContractService;
import com.person.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: QQ:553039957
 * @Date: 2023/9/25 16:21
 * @Description:
 * 1. gitcode主页： https://gitcode.net/tbb414 （推荐）
 * 2. github主页：https://github.com/doudoutangs
 * 
 */
@RestController
@RequestMapping("/person/contract")
public class ContractController extends AbstractController {
	@Autowired
	private ContractService contractService;

	/**
	 * 所有合同档案列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("person:contract:list")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = contractService.queryPage(params);

		return R.ok().put("page", page);
	}


	/**
	 * 合同档案信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("person:contract:info")
	@ResponseBody
	public R info(@PathVariable("id") Long id){
		ContractEntity contract = contractService.getById(id);

		return R.ok().put("contract", contract);
	}

	/**
	 * 保存合同档案
	 */
	@SysLog("保存合同档案")
	@RequestMapping("/save")
	@RequiresPermissions("person:contract:save")
	public R save(@RequestBody ContractEntity contract){
		ValidatorUtils.validateEntity(contract);
		contractService.save(contract);
		return R.ok();
	}

	/**
	 * 修改合同档案
	 */
	@SysLog("修改合同档案")
	@RequestMapping("/update")
	@RequiresPermissions("person:contract:update")
	public R update(@RequestBody ContractEntity contract){
		ValidatorUtils.validateEntity(contract);
		contractService.update(contract);
		return R.ok();
	}

	/**
	 * 删除合同档案
	 */
	@SysLog("删除合同档案")
	@RequestMapping("/delete")
	@RequiresPermissions("person:contract:delete")
	public R delete(@RequestBody Long[] ids){
		contractService.deleteBatch(ids);
		return R.ok();
	}

}
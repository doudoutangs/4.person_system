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
import com.person.modules.person.entity.RecruitNeedEntity;
import com.person.modules.person.service.RecruitNeedService;
import com.person.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 招聘要求
 *
 * @author 
 */
@RestController
@RequestMapping("/person/need")
public class RecruitNeedController  extends AbstractController {
    @Autowired
    private RecruitNeedService recruitNeedService;

    /**
     * 所有招聘要求列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("person:need:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = recruitNeedService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 招聘要求信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("person:need:info")
    @ResponseBody
    public R info(@PathVariable("id") Long id){
        RecruitNeedEntity need = recruitNeedService.getById(id);

        return R.ok().put("need", need);
    }

    /**
     * 保存招聘要求
     */
    @SysLog("保存招聘要求")
    @RequestMapping("/save")
    @RequiresPermissions("person:need:save")
    public R save(@RequestBody RecruitNeedEntity need){
        ValidatorUtils.validateEntity(need);
        need.setCreateTime(DateUtils.currentTimeFormat() );

        recruitNeedService.save(need);

        return R.ok();
    }

    /**
     * 修改招聘要求
     */
    @SysLog("修改招聘要求")
    @RequestMapping("/update")
    @RequiresPermissions("person:need:update")
    public R update(@RequestBody RecruitNeedEntity need){
        ValidatorUtils.validateEntity(need);
        recruitNeedService.update(need);

        return R.ok();
    }

    /**
     * 删除招聘要求
     */
    @SysLog("删除招聘要求")
    @RequestMapping("/delete")
    @RequiresPermissions("person:need:delete")
    public R delete(@RequestBody Long[] ids){
        recruitNeedService.deleteBatch(ids);
        return R.ok();
    }

}
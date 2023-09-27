package com.cdut.tiktok.order.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdut.tiktok.order.entity.RechargeRecordEntity;
import com.cdut.tiktok.order.service.RechargeRecordService;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.R;



/**
 * 
 *
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:23:33
 */
@RestController
@RequestMapping("order/rechargerecord")
public class RechargeRecordController {
    @Autowired
    private RechargeRecordService rechargeRecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("order:rechargerecord:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = rechargeRecordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("order:rechargerecord:info")
    public R info(@PathVariable("id") Integer id){
		RechargeRecordEntity rechargeRecord = rechargeRecordService.getById(id);

        return R.ok().put("rechargeRecord", rechargeRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("order:rechargerecord:save")
    public R save(@RequestBody RechargeRecordEntity rechargeRecord){
		rechargeRecordService.save(rechargeRecord);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("order:rechargerecord:update")
    public R update(@RequestBody RechargeRecordEntity rechargeRecord){
		rechargeRecordService.updateById(rechargeRecord);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("order:rechargerecord:delete")
    public R delete(@RequestBody Integer[] ids){
		rechargeRecordService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

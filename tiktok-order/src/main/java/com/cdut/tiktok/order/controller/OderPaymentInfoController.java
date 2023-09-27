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

import com.cdut.tiktok.order.entity.OderPaymentInfoEntity;
import com.cdut.tiktok.order.service.OderPaymentInfoService;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.R;



/**
 * 支付信息表
 *
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:23:33
 */
@RestController
@RequestMapping("order/oderpaymentinfo")
public class OderPaymentInfoController {
    @Autowired
    private OderPaymentInfoService oderPaymentInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("order:oderpaymentinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = oderPaymentInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("order:oderpaymentinfo:info")
    public R info(@PathVariable("id") Long id){
		OderPaymentInfoEntity oderPaymentInfo = oderPaymentInfoService.getById(id);

        return R.ok().put("oderPaymentInfo", oderPaymentInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("order:oderpaymentinfo:save")
    public R save(@RequestBody OderPaymentInfoEntity oderPaymentInfo){
		oderPaymentInfoService.save(oderPaymentInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("order:oderpaymentinfo:update")
    public R update(@RequestBody OderPaymentInfoEntity oderPaymentInfo){
		oderPaymentInfoService.updateById(oderPaymentInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("order:oderpaymentinfo:delete")
    public R delete(@RequestBody Long[] ids){
		oderPaymentInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

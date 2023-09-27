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

import com.cdut.tiktok.order.entity.OderGiftEntity;
import com.cdut.tiktok.order.service.OderGiftService;
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
@RequestMapping("order/odergift")
public class OderGiftController {
    @Autowired
    private OderGiftService oderGiftService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("order:odergift:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = oderGiftService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("order:odergift:info")
    public R info(@PathVariable("id") Long id){
		OderGiftEntity oderGift = oderGiftService.getById(id);

        return R.ok().put("oderGift", oderGift);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("order:odergift:save")
    public R save(@RequestBody OderGiftEntity oderGift){
		oderGiftService.save(oderGift);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("order:odergift:update")
    public R update(@RequestBody OderGiftEntity oderGift){
		oderGiftService.updateById(oderGift);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("order:odergift:delete")
    public R delete(@RequestBody Long[] ids){
		oderGiftService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

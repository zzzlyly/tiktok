package com.cdut.tiktok.livestream.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cdut.tiktok.livestream.entity.GiftEntity;
import com.cdut.tiktok.livestream.service.GiftService;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.R;



/**
 * 
 *
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:00:56
 */
@RestController
@RequestMapping("livestream/gift")
public class GiftController {
    @Autowired
    private GiftService giftService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("livestream:gift:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = giftService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("livestream:gift:info")
    public R info(@PathVariable("id") Integer id){
		GiftEntity gift = giftService.getById(id);

        return R.ok().put("gift", gift);
    }

    /**
     * 保存
     */
    @PutMapping
    @RequiresPermissions("livestream:gift:save")
    public R save(@RequestBody GiftEntity gift){
		giftService.save(gift);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping
    @RequiresPermissions("livestream:gift:update")
    public R update(@RequestBody GiftEntity gift){
		giftService.updateById(gift);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("livestream:gift:delete")
    public R delete(@RequestBody Integer[] ids){
		giftService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

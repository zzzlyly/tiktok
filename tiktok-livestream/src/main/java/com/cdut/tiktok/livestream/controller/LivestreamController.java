package com.cdut.tiktok.livestream.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cdut.tiktok.livestream.entity.LivestreamEntity;
import com.cdut.tiktok.livestream.service.LivestreamService;
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
@RequestMapping("livestream/livestream")
public class LivestreamController {
    @Autowired
    private LivestreamService livestreamService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("livestream:livestream:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = livestreamService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("livestream:livestream:info")
    public R info(@PathVariable("id") Long id){
		LivestreamEntity livestream = livestreamService.getById(id);

        return R.ok().put("livestream", livestream);
    }

    /**
     * 保存
     */
    @PostMapping
    @RequiresPermissions("livestream:livestream:save")
    public R save(@RequestBody LivestreamEntity livestream){
		livestreamService.save(livestream);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping
    @RequiresPermissions("livestream:livestream:update")
    public R update(@RequestBody LivestreamEntity livestream){
		livestreamService.updateById(livestream);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping
    @RequiresPermissions("livestream:livestream:delete")
    public R delete(@RequestBody Long[] ids){
		livestreamService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

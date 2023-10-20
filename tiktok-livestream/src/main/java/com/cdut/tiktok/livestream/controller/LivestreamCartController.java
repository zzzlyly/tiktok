package com.cdut.tiktok.livestream.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cdut.tiktok.livestream.entity.LivestreamCartEntity;
import com.cdut.tiktok.livestream.service.LivestreamCartService;
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
@RequestMapping("livestream/livestreamcart")
public class LivestreamCartController {
    @Autowired
    private LivestreamCartService livestreamCartService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("livestream:livestreamcart:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = livestreamCartService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("livestream:livestreamcart:info")
    public R info(@PathVariable("id") Long id){
		LivestreamCartEntity livestreamCart = livestreamCartService.getById(id);

        return R.ok().put("livestreamCart", livestreamCart);
    }

    /**
     * 保存
     */
    @PostMapping
    @RequiresPermissions("livestream:livestreamcart:save")
    public R save(@RequestBody LivestreamCartEntity livestreamCart){
		livestreamCartService.save(livestreamCart);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping
    @RequiresPermissions("livestream:livestreamcart:update")
    public R update(@RequestBody LivestreamCartEntity livestreamCart){
		livestreamCartService.updateById(livestreamCart);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping
    @RequiresPermissions("livestream:livestreamcart:delete")
    public R delete(@RequestBody Long[] ids){
		livestreamCartService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

package com.cdut.tiktok.livestream.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping("/list")
    @RequiresPermissions("livestream:livestreamcart:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = livestreamCartService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("livestream:livestreamcart:info")
    public R info(@PathVariable("id") Long id){
		LivestreamCartEntity livestreamCart = livestreamCartService.getById(id);

        return R.ok().put("livestreamCart", livestreamCart);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("livestream:livestreamcart:save")
    public R save(@RequestBody LivestreamCartEntity livestreamCart){
		livestreamCartService.save(livestreamCart);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("livestream:livestreamcart:update")
    public R update(@RequestBody LivestreamCartEntity livestreamCart){
		livestreamCartService.updateById(livestreamCart);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("livestream:livestreamcart:delete")
    public R delete(@RequestBody Long[] ids){
		livestreamCartService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

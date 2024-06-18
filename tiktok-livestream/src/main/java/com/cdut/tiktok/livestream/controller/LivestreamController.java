package com.cdut.tiktok.livestream.controller;

import java.util.Arrays;
import java.util.Map;

import com.cdut.tiktok.common.annotation.UserId;
import com.cdut.tiktok.common.utils.TokenService;
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
    private TokenService tokenService;

    @Autowired
    private LivestreamService livestreamService;



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

    @GetMapping("/getstreamkey")
    public R getStreamKey(@UserId String userId){

        //String userId = tokenService.getUserIdFromToken(token);

        String key = livestreamService.getStreamKey(userId);

        return R.ok().put("stream_key", key);
    }

    @PostMapping("/validate")
    public R validateStream(@RequestParam("stream_key") String key) {
        // 实现认证逻辑
        // 检查 key 是否有效
        String[] parts = key.split(",");
        if (livestreamService.isValidKey(parts[1])) {
            // 返回 HTTP 状态 200，允许推流
            return R.ok();
        } else {
            // 返回 HTTP 状态 403，拒绝推流
            return R.error();
        }
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("livestream:livestream:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = livestreamService.queryPage(params);

        return R.ok().put("page", page);
    }

}

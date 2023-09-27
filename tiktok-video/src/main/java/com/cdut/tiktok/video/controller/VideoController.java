package com.cdut.tiktok.video.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdut.tiktok.video.entity.VideoEntity;
import com.cdut.tiktok.video.service.VideoService;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.R;



/**
 * 
 *
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:28:53
 */
@RestController
@RequestMapping("video/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("video:video:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = videoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("video:video:info")
    public R info(@PathVariable("id") Long id){
		VideoEntity video = videoService.getById(id);

        return R.ok().put("video", video);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("video:video:save")
    public R save(@RequestBody VideoEntity video){
		videoService.save(video);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("video:video:update")
    public R update(@RequestBody VideoEntity video){
		videoService.updateById(video);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("video:video:delete")
    public R delete(@RequestBody Long[] ids){
		videoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

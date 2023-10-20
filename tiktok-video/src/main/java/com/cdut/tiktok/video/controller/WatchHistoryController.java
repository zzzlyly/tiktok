package com.cdut.tiktok.video.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cdut.tiktok.video.entity.WatchHistoryEntity;
import com.cdut.tiktok.video.service.WatchHistoryService;
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
@RequestMapping("video/watchhistory")
public class WatchHistoryController {
    @Autowired
    private WatchHistoryService watchHistoryService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("video:watchhistory:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = watchHistoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("video:watchhistory:info")
    public R info(@PathVariable("id") Integer id){
		WatchHistoryEntity watchHistory = watchHistoryService.getById(id);

        return R.ok().put("watchHistory", watchHistory);
    }

    /**
     * 保存
     */
    @PostMapping
    @RequiresPermissions("video:watchhistory:save")
    public R save(@RequestBody WatchHistoryEntity watchHistory){
		watchHistoryService.save(watchHistory);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping
    @RequiresPermissions("video:watchhistory:update")
    public R update(@RequestBody WatchHistoryEntity watchHistory){
		watchHistoryService.updateById(watchHistory);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping
    @RequiresPermissions("video:watchhistory:delete")
    public R delete(@RequestBody Integer[] ids){
		watchHistoryService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

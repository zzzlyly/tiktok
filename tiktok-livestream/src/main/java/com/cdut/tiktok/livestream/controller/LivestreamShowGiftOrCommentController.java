package com.cdut.tiktok.livestream.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cdut.tiktok.livestream.entity.LivestreamShowGiftOrCommentEntity;
import com.cdut.tiktok.livestream.service.LivestreamShowGiftOrCommentService;
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
@RequestMapping("livestream/livestreamshowgiftorcomment")
public class LivestreamShowGiftOrCommentController {
    @Autowired
    private LivestreamShowGiftOrCommentService livestreamShowGiftOrCommentService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("livestream:livestreamshowgiftorcomment:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = livestreamShowGiftOrCommentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("livestream:livestreamshowgiftorcomment:info")
    public R info(@PathVariable("id") Long id){
		LivestreamShowGiftOrCommentEntity livestreamShowGiftOrComment = livestreamShowGiftOrCommentService.getById(id);

        return R.ok().put("livestreamShowGiftOrComment", livestreamShowGiftOrComment);
    }

    /**
     * 保存
     */
    @PostMapping
    @RequiresPermissions("livestream:livestreamshowgiftorcomment:save")
    public R save(@RequestBody LivestreamShowGiftOrCommentEntity livestreamShowGiftOrComment){
		livestreamShowGiftOrCommentService.save(livestreamShowGiftOrComment);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping
    @RequiresPermissions("livestream:livestreamshowgiftorcomment:update")
    public R update(@RequestBody LivestreamShowGiftOrCommentEntity livestreamShowGiftOrComment){
		livestreamShowGiftOrCommentService.updateById(livestreamShowGiftOrComment);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping
    @RequiresPermissions("livestream:livestreamshowgiftorcomment:delete")
    public R delete(@RequestBody Long[] ids){
		livestreamShowGiftOrCommentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

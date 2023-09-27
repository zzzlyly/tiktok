package com.cdut.tiktok.user.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdut.tiktok.user.entity.LikeRelationEntity;
import com.cdut.tiktok.user.service.LikeRelationService;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.R;



/**
 * 
 *
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:27:51
 */
@RestController
@RequestMapping("user/likerelation")
public class LikeRelationController {
    @Autowired
    private LikeRelationService likeRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("user:likerelation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = likeRelationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("user:likerelation:info")
    public R info(@PathVariable("id") Long id){
		LikeRelationEntity likeRelation = likeRelationService.getById(id);

        return R.ok().put("likeRelation", likeRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("user:likerelation:save")
    public R save(@RequestBody LikeRelationEntity likeRelation){
		likeRelationService.save(likeRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("user:likerelation:update")
    public R update(@RequestBody LikeRelationEntity likeRelation){
		likeRelationService.updateById(likeRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("user:likerelation:delete")
    public R delete(@RequestBody Long[] ids){
		likeRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

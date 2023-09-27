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

import com.cdut.tiktok.user.entity.FavoriteRelationEntity;
import com.cdut.tiktok.user.service.FavoriteRelationService;
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
@RequestMapping("user/favoriterelation")
public class FavoriteRelationController {
    @Autowired
    private FavoriteRelationService favoriteRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("user:favoriterelation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = favoriteRelationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("user:favoriterelation:info")
    public R info(@PathVariable("id") Long id){
		FavoriteRelationEntity favoriteRelation = favoriteRelationService.getById(id);

        return R.ok().put("favoriteRelation", favoriteRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("user:favoriterelation:save")
    public R save(@RequestBody FavoriteRelationEntity favoriteRelation){
		favoriteRelationService.save(favoriteRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("user:favoriterelation:update")
    public R update(@RequestBody FavoriteRelationEntity favoriteRelation){
		favoriteRelationService.updateById(favoriteRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("user:favoriterelation:delete")
    public R delete(@RequestBody Long[] ids){
		favoriteRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

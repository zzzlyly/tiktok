package com.cdut.tiktok.user.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.cdut.tiktok.user.pojo.dto.LIkeListDto;
import com.cdut.tiktok.user.pojo.dto.LikeActionDto;
import com.cdut.tiktok.user.pojo.vo.LikeListVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("user/like")
public class LikeRelationController {
    @Autowired
    private LikeRelationService likeRelationService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("user:likerelation:list")
    public R list(@RequestParam String token, @RequestParam Long user_id) {
        LIkeListDto likeListDto = new LIkeListDto();
        likeListDto.setUserId(user_id);
        likeListDto.setToken(token);
        List<LikeListVo> likeListVo = likeRelationService.list(likeListDto);

        return R.ok().put("code",200).put("message","success").put("video_list", likeListVo);
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

    @PostMapping
    public R like(@RequestBody LikeActionDto likeActionDto){

        likeRelationService.likeAction(likeActionDto);

        return R.ok().put("code",200).put("message","success");
    }

}

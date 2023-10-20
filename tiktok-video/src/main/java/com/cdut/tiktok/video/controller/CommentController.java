package com.cdut.tiktok.video.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.cdut.tiktok.video.pojo.dto.AddCommentDto;
import com.cdut.tiktok.video.pojo.dto.DeleteCommentDto;
import com.cdut.tiktok.video.pojo.vo.GetCommentVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cdut.tiktok.video.entity.CommentEntity;
import com.cdut.tiktok.video.service.CommentService;
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
@RequestMapping("video/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 列表
     */
    @GetMapping
    @RequiresPermissions("video:comment:list")
    public R list(@RequestParam Map<String, Object> params){
        List<GetCommentVO> comment = commentService.getCommnet(params);
        return R.ok().put("code",200).put("msg","success").put("comment_list", comment);
    }


    /**
     * 信息
     */
    @GetMapping("/{id}")
    @RequiresPermissions("video:comment:info")
    public R info(@PathVariable("id") Long id){
		CommentEntity comment = commentService.getById(id);

        return R.ok().put("comment", comment);
    }

    /**
     * 保存
     */
    @PostMapping
    @RequiresPermissions("video:comment:save")
    public R save(@RequestBody AddCommentDto comment){

        commentService.saveComment(comment);
        return R.ok().put("code",200);
    }

    /**
     * 修改
     */
    @PutMapping
    @RequiresPermissions("video:comment:update")
    public R update(@RequestBody CommentEntity comment){

		commentService.updateById(comment);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping
    @RequiresPermissions("video:comment:delete")
    public R delete(@RequestBody DeleteCommentDto deleteCommentDto){
		commentService.removeByIds(Arrays.asList(deleteCommentDto.getIds()));
        return R.ok();
    }

    /**
     * 删除单个评论
     */
    @DeleteMapping("/{id}")
    public R delete(@RequestParam Integer id){
        return R.ok();
    }
}

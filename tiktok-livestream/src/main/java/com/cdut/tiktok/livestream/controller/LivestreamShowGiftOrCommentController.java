package com.cdut.tiktok.livestream.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.cdut.tiktok.common.annotation.UserId;
import com.cdut.tiktok.common.utils.JwtUtils;
import com.cdut.tiktok.livestream.pojo.dto.GetGiftOrCommentDto;
import com.cdut.tiktok.livestream.pojo.dto.SendGiftOrCommentDto;
import com.cdut.tiktok.livestream.pojo.vo.GetGiftOrCommentVo;
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

    @Autowired
    private JwtUtils jwtUtils;

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
    @GetMapping("/info/id")
    @RequiresPermissions("livestream:livestreamshowgiftorcomment:info")
    public R info(@PathVariable("id") Long id){
		LivestreamShowGiftOrCommentEntity livestreamShowGiftOrComment = livestreamShowGiftOrCommentService.getById(id);

        return R.ok().put("livestreamShowGiftOrComment", livestreamShowGiftOrComment);
    }

    @PostMapping("/send")
    public R sendGiftOrComment(@RequestBody SendGiftOrCommentDto sendGiftOrCommentDto){

        Long userId = jwtUtils.getUserIdFromToken(sendGiftOrCommentDto.getToken());
        sendGiftOrCommentDto.setUserId(userId);
        livestreamShowGiftOrCommentService.sendGiftOrComment(sendGiftOrCommentDto);
        return R.ok("送礼或者评论成功");
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

    @PostMapping("/get")
    public R getGiftOrComment(@RequestBody GetGiftOrCommentDto getGiftOrCommentDto){
        Long userId = jwtUtils.getUserIdFromToken(getGiftOrCommentDto.getToken());
        getGiftOrCommentDto.setUserId(userId);

        List<GetGiftOrCommentVo> giftOrCommentVo = livestreamShowGiftOrCommentService.getGiftOrComment(getGiftOrCommentDto);
        return R.ok().put("giftOrComment", giftOrCommentVo);
    }

}

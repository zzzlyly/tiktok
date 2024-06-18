package com.cdut.tiktok.video.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.aliyuncs.exceptions.ClientException;
import com.cdut.tiktok.common.annotation.UserId;
import com.cdut.tiktok.common.exception.ExceptionCode;
import com.cdut.tiktok.common.exception.MyException;
import com.cdut.tiktok.common.utils.JwtUtils;
import com.cdut.tiktok.video.pojo.dto.UploadVideoDto;
import com.cdut.tiktok.video.pojo.to.LikeActionTo;
import com.cdut.tiktok.video.pojo.to.LikeListVideoTo;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cdut.tiktok.video.entity.VideoEntity;
import com.cdut.tiktok.video.service.VideoService;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.R;
import org.springframework.web.multipart.MultipartFile;


/**
 * 
 *
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:28:53
 */
@RestController
@ControllerAdvice
@RequestMapping("video/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("video:video:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = videoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("video:video:info")
    public R info(@PathVariable("id") Long id){
		VideoEntity video = videoService.getById(id);

        return R.ok().put("video", video);
    }

    /**
     * 保存
     */
    @PostMapping
    @RequiresPermissions("video:video:save")
    public R save(@RequestBody VideoEntity video){
		videoService.save(video);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping
    @RequiresPermissions("video:video:update")
    public R update(@RequestBody VideoEntity video){
		videoService.updateById(video);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping
    @RequiresPermissions("video:video:delete")
    public R delete(@RequestBody Long[] ids){
		videoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 远程调用，获取视频的作者id
     * @param likeActionto
     * @return
     */
    @PostMapping("/userid")
    public Long getUserIdByVideoId(@RequestBody LikeActionTo likeActionto){

        return videoService.getUserIdByVideoId(likeActionto);
    };

    /**
     * 远程调用，用videoids_获取视频的信息
     * @param videoIds
     * @return
     */
    @PostMapping("/list/ids")
    public List<LikeListVideoTo> getVideoListByVideoId(@RequestBody List<Long> videoIds){
        return videoService.getVideoListByVideoIds(videoIds);
    };





    @PostMapping("/upload")
    public R uploadVideo(@RequestParam("video") MultipartFile video, @RequestParam("video_cover") MultipartFile videoImage, @UserId  String userId,
                         @RequestParam("video_title") String videoTitle, @RequestParam("video_format") String videoFormat, @RequestParam("video_cover_format") String videoCoverFormat) throws ClientException {

        UploadVideoDto uploadVideoDto = UploadVideoDto.builder()
                .video(video)
                .videoImage(videoImage)
                .videoTitle(videoTitle)
                .videoFormat(videoFormat)
                .videoCoverFormat(videoCoverFormat)
                .authorId(userId)
                .build();

        // 尝试上传视频
        videoService.uploadVideo(uploadVideoDto);
        return R.ok("上传成功");
    }
}



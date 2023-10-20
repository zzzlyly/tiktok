package com.cdut.tiktok.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.video.entity.VideoEntity;
import com.cdut.tiktok.video.pojo.to.LikeActionTo;
import com.cdut.tiktok.video.pojo.to.LikeListVideoTo;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:28:53
 */
public interface VideoService extends IService<VideoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Long getUserIdByVideoId(LikeActionTo likeActionto);

    List<LikeListVideoTo> getVideoListByVideoIds(List<Long> videoIds);
}


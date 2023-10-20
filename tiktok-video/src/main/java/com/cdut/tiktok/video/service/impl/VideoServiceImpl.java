package com.cdut.tiktok.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cdut.tiktok.video.pojo.to.LikeActionTo;
import com.cdut.tiktok.video.pojo.to.LikeListVideoTo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.Query;

import com.cdut.tiktok.video.dao.VideoDao;
import com.cdut.tiktok.video.entity.VideoEntity;
import com.cdut.tiktok.video.service.VideoService;
import org.springframework.transaction.annotation.Transactional;


@Service("videoService")
public class VideoServiceImpl extends ServiceImpl<VideoDao, VideoEntity> implements VideoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<VideoEntity> page = this.page(
                new Query<VideoEntity>().getPage(params),
                new QueryWrapper<VideoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public Long getUserIdByVideoId(LikeActionTo likeActionto) {

        UpdateWrapper<VideoEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", likeActionto.getVideoId());
        if (likeActionto.getActionType() == 1){
        updateWrapper.setSql("likes_count = likes_count + 1");
        }else {
            updateWrapper.setSql("likes_count = likes_count - 1");
        }
        this.update(updateWrapper);

        QueryWrapper<VideoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", likeActionto.getVideoId());
        return this.getOne(queryWrapper).getAuthorId();
    }

    @Override
    public List<LikeListVideoTo> getVideoListByVideoIds(List<Long> videoIds) {
        QueryWrapper<VideoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",videoIds);
        List<VideoEntity> videoEntityList = this.list(queryWrapper);

        return videoEntityList.stream()
                .map(videoEntity -> {
                    LikeListVideoTo likeListVideoTo = new LikeListVideoTo();
                    BeanUtils.copyProperties(videoEntity, likeListVideoTo);
                    return likeListVideoTo;
                }).collect(Collectors.toList());
    }

}
package com.cdut.tiktok.livestream.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.Query;

import com.cdut.tiktok.livestream.dao.LivestreamShowGiftOrCommentDao;
import com.cdut.tiktok.livestream.entity.LivestreamShowGiftOrCommentEntity;
import com.cdut.tiktok.livestream.service.LivestreamShowGiftOrCommentService;


@Service("livestreamShowGiftOrCommentService")
public class LivestreamShowGiftOrCommentServiceImpl extends ServiceImpl<LivestreamShowGiftOrCommentDao, LivestreamShowGiftOrCommentEntity> implements LivestreamShowGiftOrCommentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LivestreamShowGiftOrCommentEntity> page = this.page(
                new Query<LivestreamShowGiftOrCommentEntity>().getPage(params),
                new QueryWrapper<LivestreamShowGiftOrCommentEntity>()
        );

        return new PageUtils(page);
    }

}
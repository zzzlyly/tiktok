package com.cdut.tiktok.livestream.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.Query;

import com.cdut.tiktok.livestream.dao.LivestreamDao;
import com.cdut.tiktok.livestream.entity.LivestreamEntity;
import com.cdut.tiktok.livestream.service.LivestreamService;


@Service("livestreamService")
public class LivestreamServiceImpl extends ServiceImpl<LivestreamDao, LivestreamEntity> implements LivestreamService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LivestreamEntity> page = this.page(
                new Query<LivestreamEntity>().getPage(params),
                new QueryWrapper<LivestreamEntity>()
        );

        return new PageUtils(page);
    }

}
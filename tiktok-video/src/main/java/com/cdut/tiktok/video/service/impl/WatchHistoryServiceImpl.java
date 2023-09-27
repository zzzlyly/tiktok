package com.cdut.tiktok.video.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.Query;

import com.cdut.tiktok.video.dao.WatchHistoryDao;
import com.cdut.tiktok.video.entity.WatchHistoryEntity;
import com.cdut.tiktok.video.service.WatchHistoryService;


@Service("watchHistoryService")
public class WatchHistoryServiceImpl extends ServiceImpl<WatchHistoryDao, WatchHistoryEntity> implements WatchHistoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WatchHistoryEntity> page = this.page(
                new Query<WatchHistoryEntity>().getPage(params),
                new QueryWrapper<WatchHistoryEntity>()
        );

        return new PageUtils(page);
    }

}
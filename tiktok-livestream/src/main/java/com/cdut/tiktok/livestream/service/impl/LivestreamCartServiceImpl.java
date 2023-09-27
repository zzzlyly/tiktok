package com.cdut.tiktok.livestream.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.Query;

import com.cdut.tiktok.livestream.dao.LivestreamCartDao;
import com.cdut.tiktok.livestream.entity.LivestreamCartEntity;
import com.cdut.tiktok.livestream.service.LivestreamCartService;


@Service("livestreamCartService")
public class LivestreamCartServiceImpl extends ServiceImpl<LivestreamCartDao, LivestreamCartEntity> implements LivestreamCartService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LivestreamCartEntity> page = this.page(
                new Query<LivestreamCartEntity>().getPage(params),
                new QueryWrapper<LivestreamCartEntity>()
        );

        return new PageUtils(page);
    }

}
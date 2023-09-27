package com.cdut.tiktok.livestream.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.Query;

import com.cdut.tiktok.livestream.dao.GiftDao;
import com.cdut.tiktok.livestream.entity.GiftEntity;
import com.cdut.tiktok.livestream.service.GiftService;


@Service("giftService")
public class GiftServiceImpl extends ServiceImpl<GiftDao, GiftEntity> implements GiftService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GiftEntity> page = this.page(
                new Query<GiftEntity>().getPage(params),
                new QueryWrapper<GiftEntity>()
        );

        return new PageUtils(page);
    }

}
package com.cdut.tiktok.user.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.Query;

import com.cdut.tiktok.user.dao.FavoriteRelationDao;
import com.cdut.tiktok.user.entity.FavoriteRelationEntity;
import com.cdut.tiktok.user.service.FavoriteRelationService;


@Service("favoriteRelationService")
public class FavoriteRelationServiceImpl extends ServiceImpl<FavoriteRelationDao, FavoriteRelationEntity> implements FavoriteRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FavoriteRelationEntity> page = this.page(
                new Query<FavoriteRelationEntity>().getPage(params),
                new QueryWrapper<FavoriteRelationEntity>()
        );

        return new PageUtils(page);
    }

}
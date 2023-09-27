package com.cdut.tiktok.user.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.Query;

import com.cdut.tiktok.user.dao.LikeRelationDao;
import com.cdut.tiktok.user.entity.LikeRelationEntity;
import com.cdut.tiktok.user.service.LikeRelationService;


@Service("likeRelationService")
public class LikeRelationServiceImpl extends ServiceImpl<LikeRelationDao, LikeRelationEntity> implements LikeRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LikeRelationEntity> page = this.page(
                new Query<LikeRelationEntity>().getPage(params),
                new QueryWrapper<LikeRelationEntity>()
        );

        return new PageUtils(page);
    }

}
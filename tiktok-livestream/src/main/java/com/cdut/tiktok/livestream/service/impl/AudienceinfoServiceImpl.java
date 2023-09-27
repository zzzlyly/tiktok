package com.cdut.tiktok.livestream.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.Query;

import com.cdut.tiktok.livestream.dao.AudienceinfoDao;
import com.cdut.tiktok.livestream.entity.AudienceinfoEntity;
import com.cdut.tiktok.livestream.service.AudienceinfoService;


@Service("audienceinfoService")
public class AudienceinfoServiceImpl extends ServiceImpl<AudienceinfoDao, AudienceinfoEntity> implements AudienceinfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AudienceinfoEntity> page = this.page(
                new Query<AudienceinfoEntity>().getPage(params),
                new QueryWrapper<AudienceinfoEntity>()
        );

        return new PageUtils(page);
    }

}
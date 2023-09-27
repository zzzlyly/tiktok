package com.cdut.tiktok.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.Query;

import com.cdut.tiktok.order.dao.OderGiftDao;
import com.cdut.tiktok.order.entity.OderGiftEntity;
import com.cdut.tiktok.order.service.OderGiftService;


@Service("oderGiftService")
public class OderGiftServiceImpl extends ServiceImpl<OderGiftDao, OderGiftEntity> implements OderGiftService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OderGiftEntity> page = this.page(
                new Query<OderGiftEntity>().getPage(params),
                new QueryWrapper<OderGiftEntity>()
        );

        return new PageUtils(page);
    }

}
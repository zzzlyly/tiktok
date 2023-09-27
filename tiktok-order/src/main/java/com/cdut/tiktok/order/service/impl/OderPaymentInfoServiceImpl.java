package com.cdut.tiktok.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.Query;

import com.cdut.tiktok.order.dao.OderPaymentInfoDao;
import com.cdut.tiktok.order.entity.OderPaymentInfoEntity;
import com.cdut.tiktok.order.service.OderPaymentInfoService;


@Service("oderPaymentInfoService")
public class OderPaymentInfoServiceImpl extends ServiceImpl<OderPaymentInfoDao, OderPaymentInfoEntity> implements OderPaymentInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OderPaymentInfoEntity> page = this.page(
                new Query<OderPaymentInfoEntity>().getPage(params),
                new QueryWrapper<OderPaymentInfoEntity>()
        );

        return new PageUtils(page);
    }

}
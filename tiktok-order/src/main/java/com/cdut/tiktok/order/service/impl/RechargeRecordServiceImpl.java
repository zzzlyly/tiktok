package com.cdut.tiktok.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.Query;

import com.cdut.tiktok.order.dao.RechargeRecordDao;
import com.cdut.tiktok.order.entity.RechargeRecordEntity;
import com.cdut.tiktok.order.service.RechargeRecordService;


@Service("rechargeRecordService")
public class RechargeRecordServiceImpl extends ServiceImpl<RechargeRecordDao, RechargeRecordEntity> implements RechargeRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RechargeRecordEntity> page = this.page(
                new Query<RechargeRecordEntity>().getPage(params),
                new QueryWrapper<RechargeRecordEntity>()
        );

        return new PageUtils(page);
    }

}
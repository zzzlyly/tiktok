package com.cdut.tiktok.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.Query;

import com.cdut.tiktok.order.dao.OrderShoppingDao;
import com.cdut.tiktok.order.entity.OrderShoppingEntity;
import com.cdut.tiktok.order.service.OrderShoppingService;


@Service("orderShoppingService")
public class OrderShoppingServiceImpl extends ServiceImpl<OrderShoppingDao, OrderShoppingEntity> implements OrderShoppingService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderShoppingEntity> page = this.page(
                new Query<OrderShoppingEntity>().getPage(params),
                new QueryWrapper<OrderShoppingEntity>()
        );

        return new PageUtils(page);
    }

}
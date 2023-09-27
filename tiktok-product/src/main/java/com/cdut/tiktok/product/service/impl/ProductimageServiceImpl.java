package com.cdut.tiktok.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.Query;

import com.cdut.tiktok.product.dao.ProductimageDao;
import com.cdut.tiktok.product.entity.ProductimageEntity;
import com.cdut.tiktok.product.service.ProductimageService;


@Service("productimageService")
public class ProductimageServiceImpl extends ServiceImpl<ProductimageDao, ProductimageEntity> implements ProductimageService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProductimageEntity> page = this.page(
                new Query<ProductimageEntity>().getPage(params),
                new QueryWrapper<ProductimageEntity>()
        );

        return new PageUtils(page);
    }

}
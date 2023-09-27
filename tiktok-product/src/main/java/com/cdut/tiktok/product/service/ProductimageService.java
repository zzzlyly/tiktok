package com.cdut.tiktok.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.product.entity.ProductimageEntity;

import java.util.Map;

/**
 * 
 *
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:25:55
 */
public interface ProductimageService extends IService<ProductimageEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


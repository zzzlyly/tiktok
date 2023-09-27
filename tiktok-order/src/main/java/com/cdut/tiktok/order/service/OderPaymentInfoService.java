package com.cdut.tiktok.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.order.entity.OderPaymentInfoEntity;

import java.util.Map;

/**
 * 支付信息表
 *
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:23:33
 */
public interface OderPaymentInfoService extends IService<OderPaymentInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


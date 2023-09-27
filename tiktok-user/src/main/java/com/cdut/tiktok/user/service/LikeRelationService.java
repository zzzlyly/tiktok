package com.cdut.tiktok.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.user.entity.LikeRelationEntity;

import java.util.Map;

/**
 * 
 *
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:27:51
 */
public interface LikeRelationService extends IService<LikeRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


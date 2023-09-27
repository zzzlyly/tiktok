package com.cdut.tiktok.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.video.entity.WatchHistoryEntity;

import java.util.Map;

/**
 * 
 *
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:28:53
 */
public interface WatchHistoryService extends IService<WatchHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


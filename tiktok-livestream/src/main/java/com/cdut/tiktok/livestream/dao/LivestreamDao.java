package com.cdut.tiktok.livestream.dao;

import com.cdut.tiktok.livestream.entity.LivestreamEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:00:56
 */
@Mapper
public interface LivestreamDao extends BaseMapper<LivestreamEntity> {
	
}

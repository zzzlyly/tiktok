package com.cdut.tiktok.user.dao;

import com.cdut.tiktok.user.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:27:51
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}

package com.cdut.tiktok.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * 
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:27:51
 */
@Data
@TableName("like_relation")
public class LikeRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 点赞关系表id
	 */
	@TableId
	private Long id;
	/**
	 * 点赞者id
	 */
	private Long userId;
	/**
	 * 视频id
	 */
	private Long videoId;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 是否删除：0未删除、1已删除
	 */
	private Long isDeleted;

}

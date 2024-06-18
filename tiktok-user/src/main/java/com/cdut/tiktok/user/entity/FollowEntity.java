package com.cdut.tiktok.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
@TableName("follow")
public class FollowEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 关注表id
	 */
	@TableId
	private Long id;
	/**
	 * 被关注者id
	 */
	private Long userId;
	/**
	 * 关注者id
	 */
	private Long followUserId;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 是否删除：0未删除、1已删除
	 */
	@TableLogic
	private Integer isDeleted;

}

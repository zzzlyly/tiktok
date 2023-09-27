package com.cdut.tiktok.video.entity;

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
 * @date 2023-09-27 19:28:53
 */
@Data
@TableName("comment")
public class CommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 评论id
	 */
	@TableId
	private Long id;
	/**
	 * 视频id
	 */
	private Long videoId;
	/**
	 * 评论用户id
	 */
	private Long userId;
	/**
	 * 评论内容
	 */
	private String content;
	/**
	 * 居住地
	 */
	private String location;
	/**
	 * 作者id
	 */
	private Long videoAuthorId;
	/**
	 * 判断用户是否是评论的别人的评论，0代表不是，其他代表被评论的评论ID
	 */
	private Long subCommentId;
	/**
	 * 0代表不是评论主楼评论
	 */
	private Long toUserId;
	/**
	 * 被点赞数量
	 */
	private Integer likesCount;
	/**
	 * 0不是、1是
	 */
	private Integer isLikedByAuthor;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 是否删除：0未删除、1已删除
	 */
	private Integer isDeleted;
	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;

}

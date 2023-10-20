package com.cdut.tiktok.video.entity;

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
 * @date 2023-09-27 19:28:53
 */
@Data
@TableName("video")
public class VideoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 视频id
	 */
	@TableId
	private Long id;
	/**
	 * 作者id
	 */
	private Long authorId;
	/**
	 * 封面url
	 */
	private String coverUrl;
	/**
	 * 视频url
	 */
	private String playUrl;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 视频标签，最多5个，用逗号分割
	 */
	private String tag;
	/**
	 * 评论总数
	 */
	private Integer commentCount;
	/**
	 * 收藏数量
	 */
	private Integer favoriteCount;
	/**
	 * 分享数量
	 */
	private Integer sharesCount;
	/**
	 * 点赞数
	 */
	private Integer likesCount;
	/**
	 * 播放数量
	 */
	private Integer playsCount;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;
	/**
	 * 是否删除：0未删除、1已删除
	 */
	@TableLogic
	private Integer isDeleted;

}

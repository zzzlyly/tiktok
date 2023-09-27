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
@TableName("watch_history")
public class WatchHistoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 观看历史id
	 */
	@TableId
	private Integer id;
	/**
	 * 观看用户id
	 */
	private Integer userId;
	/**
	 * 观看的类型：1视频、2直播、3其他
	 */
	private Integer watchType;
	/**
	 * 直播或者视频id
	 */
	private Integer watchVideoOrLivestreamId;
	/**
	 * 创建时间
	 */
	private LocalDateTime creatTime;
	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;
	/**
	 * 是否删除：0未删除、1已删除
	 */
	private Integer isDeleted;

}

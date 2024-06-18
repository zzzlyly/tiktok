package com.cdut.tiktok.livestream.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

/**
 * 
 * 
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:00:56
 */
@Data
@Builder
@TableName("livestream")
public class LivestreamEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 直播场次id
	 */
	@TableId
	private Long id;
	/**
	 * 直播观看url
	 */
	private String videoUrl;
	/**
	 * 直播推流key
	 */
	private String streamKey;
	/**
	 * 主播id
	 */
	private Long hostId;
	/**
	 * 观众数量
	 */
	private Integer viewers;
	/**
	 * 直播封面url
	 */
	private String coverUrl;
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

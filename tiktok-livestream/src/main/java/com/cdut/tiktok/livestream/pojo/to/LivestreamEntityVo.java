package com.cdut.tiktok.livestream.pojo.to;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cdut.tiktok.livestream.entity.LivestreamEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * 
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:00:56
 */
@Data
public class LivestreamEntityVo implements Serializable {
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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;
	/**
	 * 更新时间
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime;

}

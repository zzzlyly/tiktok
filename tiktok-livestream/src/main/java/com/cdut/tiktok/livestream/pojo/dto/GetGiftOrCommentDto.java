package com.cdut.tiktok.livestream.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 
 * 
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:00:56
 */
@Data
public class GetGiftOrCommentDto {
	/**
	 * 获取评论的id
	 */
	private Long userId;
	/**
	 * 直播场次的id
	 */
	@JsonProperty("livestream_id")
	private Long livestreamId;

	private String token;

	@JsonProperty("last_time")
	private LocalDateTime lastTime;

}

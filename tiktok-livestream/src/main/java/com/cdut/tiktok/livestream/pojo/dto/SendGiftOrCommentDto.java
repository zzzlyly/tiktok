package com.cdut.tiktok.livestream.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 
 * 
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:00:56
 */
@Data

public class SendGiftOrCommentDto {
	/**
	 * 礼物或评论的id
	 */
	private Long id;
	/**
	 * 送礼或评论者的id
	 */
	private Long userId;
	/**
	 * 主播的id
	 */
	@JsonProperty("host_id")
	private Long hostId;
	/**
	 * 直播场次的id
	 */
	@JsonProperty("livestream_id")
	private Long livestreamId;
	/**
	 * 1评论、2礼物、3其他
	 */
	@JsonProperty("type")
	private String commentOrGift;
	/**
	 * 评论内容
	 */
	private String comment;
	/**
	 * 礼物id
	 */
	@JsonProperty("gift_id")
	private Long giftId;

	private String token;
	/**
	 * 礼物数量
	 */
	@JsonProperty("gift_number")
	private Integer giftNumber;

}

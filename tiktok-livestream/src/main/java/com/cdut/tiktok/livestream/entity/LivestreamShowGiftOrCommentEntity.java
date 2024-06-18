package com.cdut.tiktok.livestream.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("livestream_show_gift_or_comment")
public class LivestreamShowGiftOrCommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 礼物或评论的id
	 */
	@TableId
	private Long id;
	/**
	 * 送礼或评论者的id
	 */
	private Long userId;
	/**
	 * 主播的id
	 */
	private Long hostId;
	/**
	 * 直播场次的id
	 */
	private Long livestreamId;
	/**
	 * 1评论、2礼物、3其他
	 */
	private String commentOrGift;
	/**
	 * 评论内容
	 */
	private String comment;
	/**
	 * 礼物id
	 */
	private Long giftId;
	/**
	 * 礼物数量
	 */
	private Integer giftNumber;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 是否删除：0未删除、1已删除
	 */
	@TableLogic
	private String isDeleted;

}

package com.cdut.tiktok.order.entity;

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
 * @date 2023-09-27 19:23:33
 */
@Data
@TableName("oder_gift")
public class OderGiftEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 礼物订单id
	 */
	@TableId
	private Long id;
	/**
	 * 主播id
	 */
	private Long hostId;
	/**
	 * 直播场次id
	 */
	private Long livestreamId;
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
	private Integer isDeleted;

}

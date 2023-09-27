package com.cdut.tiktok.livestream.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * 
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:00:56
 */
@Data
@TableName("gift")
public class GiftEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 礼物表id
	 */
	@TableId
	private Integer id;
	/**
	 * 礼物图片url
	 */
	private String giftUrl;
	/**
	 * 礼物价格
	 */
	private BigDecimal giftPrice;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 是否删除：0未删除、1删除
	 */
	private Integer isDeleted;
	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;

}

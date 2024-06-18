package com.cdut.tiktok.livestream.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
@TableName("livestream_cart")
public class LivestreamCartEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 直播购物车表id
	 */
	@TableId
	private Long id;
	/**
	 * 对应直播间的id
	 */
	private Long livestreamId;
	/**
	 * 商品id
	 */
	private Long productId;
	/**
	 * 数量
	 */
	private String quantity;
	/**
	 * 价格
	 */
	private BigDecimal price;
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
	@TableLogic
	private Integer isDeleted;

}

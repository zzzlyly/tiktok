package com.cdut.tiktok.product.entity;

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
 * @date 2023-09-27 19:25:55
 */
@Data
@TableName("product")
public class ProductEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品id
	 */
	@TableId
	private Long id;
	/**
	 * 商品名称
	 */
	private String productName;
	/**
	 * 商品描述
	 */
	private String description;
	/**
	 * 商品价格
	 */
	private BigDecimal price;
	/**
	 * 分类
	 */
	private String category;
	/**
	 * 库存数量
	 */
	private String stockQuantity;
	/**
	 * 启用状态，1启用、2禁用
	 */
	private Integer enable;
	/**
	 * 锁定库存
	 */
	private Integer stockLocked;
	/**
	 * 支持7天无理由？1支持、2不支持
	 */
	private Integer isSevendays;
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
	private Integer isDeleted;

}

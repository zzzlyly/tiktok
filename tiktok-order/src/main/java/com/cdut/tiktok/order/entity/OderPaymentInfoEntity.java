package com.cdut.tiktok.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 支付信息表
 * 
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:23:33
 */
@Data
@TableName("oder_payment_info")
public class OderPaymentInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 订单号（对外业务号）
	 */
	private String orderSn;
	/**
	 * 订单id
	 */
	private Long orderId;
	/**
	 * 支付方式【1->支付宝；2->微信；3->银联； 4->货到付款；】
	 */
	private Integer payType;
	/**
	 * 交易流水号
	 */
	private String tradeNo;
	/**
	 * 支付总金额
	 */
	private BigDecimal totalAmount;
	/**
	 * 交易内容
	 */
	private String subject;
	/**
	 * 支付状态
	 */
	private String paymentStatus;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 确认时间
	 */
	private LocalDateTime confirmTime;
	/**
	 * 回调内容
	 */
	private String callbackContent;
	/**
	 * 回调时间
	 */
	private LocalDateTime callbackTime;

}

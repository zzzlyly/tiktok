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
@TableName("recharge_record")
public class RechargeRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 充值记录id
	 */
	@TableId
	private Integer id;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 1-支付宝 2-微信 3- 云闪付
	 */
	private Integer rechargeChannel;
	/**
	 * 1-已创建 2-已完成 3-已取消
	 */
	private Integer orderStatus;
	/**
	 * 完成时间
	 */
	private LocalDateTime finishTime;
	/**
	 * 取消时间
	 */
	private LocalDateTime cancellationTime;
	/**
	 * 是否删除：0未删除、1已删除
	 */
	private Integer idDeleted;

}

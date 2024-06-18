package com.cdut.tiktok.livestream.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 
 * 
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:00:56
 */
@Data
public class AudienceinfoEntityVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 观众信息表id
	 */
	@TableId
	private Long id;
	/**
	 * 观众id
	 */
	private Long userId;
	/**
	 * 主播id
	 */
	private Long hostId;
	/**
	 * 观众等级
	 */
	private Integer level;
	/**
	 * 成长值
	 */
	private Integer progressPoints;
	/**
	 * 创建时间
	 */
	private LocalDateTime createdTime;
	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;

}

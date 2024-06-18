package com.cdut.tiktok.user.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 
 * 
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:27:51
 */
@Data
public class UserEntityVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	private Long id;
	/**
	 * 用户名称
	 */
	private String name;
	/**
	 * 性别 0-未知 1-male,2-female
	 */
	private Integer gender;
	/**
	 * email
	 */
	private String email;
	/**
	 * 关注用户数量
	 */
	private Long followCount;

	/**
	 * 粉丝数量
	 */
	private Long followerCount;
	/**
	 * 用户头像
	 */
	private String avatar;
	/**
	 * 用户个人页顶部大图
	 */
	private String backgroundImage;
	/**
	 * 个人简介
	 */
	private String signature;
	/**
	 * 总获赞数
	 */
	private Integer totalLiked;
	/**
	 * 作品数
	 */
	private Integer workCount;
	/**
	 * 收藏数
	 */
	private Integer favoriteCount;
	/**
	 * 喜欢数
	 */
	private Integer likeCount;
	/**
	 * ip归属地
	 */
	private String iplocation;
	/**
	 * 出生日期
	 */
	private LocalDate dateOfBirth;
	/**
	 * 毕业院校
	 */
	private String almaMater;
	/**
	 * 当前所在地
	 */
	private String currentResidence;
	/**
	 * 金币数量
	 */
	private BigDecimal coins;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
}

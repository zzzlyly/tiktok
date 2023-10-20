package com.cdut.tiktok.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * 
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:27:51
 */
@Data
@TableName("user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	@TableId
	private Long id;
	/**
	 * 用户名称
	 */
	private String name;
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
	 * 用户密码
	 */
	private String password;
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
	/**
	 * 是否删除：0未删除、1已删除
	 */
	private Integer isDeleted;
	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;

}

package com.cdut.tiktok.user.pojo.dto;

import lombok.Data;

@Data
public class CommentUserInfoDTO {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 关注用户数量
     */
    private Long followCount;
    /**
     * 粉丝数量
     */
    private Long followerCount;
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
}

package com.cdut.tiktok.user.pojo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 视频作者信息
 * <p>
 * User
 */
@Data
public class LikeListUserInfo {
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 用户个人页顶部大图
     */
    @JsonProperty("background_image")
    private String backgroundImage;
    /**
     * 喜欢数
     */
    @JsonProperty("favorite_count")
    private long favoriteCount;
    /**
     * 关注总数
     */
    @JsonProperty("follow_count")
    private long followCount;
    /**
     * 粉丝总数
     */
    @JsonProperty("follower_count")
    private long followerCount;
    /**
     * 用户id
     */
    private long id;
    /**
     * true-已关注，false-未关注，判断当前登录用户是否关注被查询用户
     */
    @JsonProperty("is_follow")
    private boolean isFollow;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 个人简介
     */
    private String signature;
    /**
     * 获赞数量
     */
    @JsonProperty("total_liked")
    private String totalLiked;
    /**
     * 作品数
     */
    @JsonProperty("work_count")
    private long workCount;
}

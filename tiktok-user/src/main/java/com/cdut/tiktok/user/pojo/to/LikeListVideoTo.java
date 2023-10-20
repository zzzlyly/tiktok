package com.cdut.tiktok.user.pojo.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LikeListVideoTo {
    /**
     * 视频作者信息
     */
    @JsonProperty("author")
    private long authorId;
    /**
     * 视频的评论总数
     */
    @JsonProperty("comment_count")
    private long commentCount;
    /**
     * 视频封面地址
     */
    @JsonProperty("cover_url")
    private String coverurl;
    /**
     * 视频的点赞总数
     */
    @JsonProperty("favorite_count")
    private long favoriteCount;
    /**
     * 视频的点赞总数
     */
    @JsonProperty("shares_count")
    private long sharesCount;
    /**
     * 视频的播放总数
     */
    @JsonProperty("plays_count")
    private long playsCount;
    /**
     * 视频唯一标识
     */
    private long id;
    /**
     * true-已点赞，false-未点赞，是查询某个用户点赞的视频，而这个视频，对于当前登录用户是否点赞
     */
    @JsonProperty("is_like")
    private boolean isLike;
    /**
     * true-已点赞，false-未点赞，是查询某个用户点赞的视频，而这个视频，对于当前登录用户是否为收藏状态
     */
    @JsonProperty("is_favorite")
    private boolean isFavorite;
    /**
     * 视频播放地址
     */
    @JsonProperty("play_url")
    private String playurl;
    /**
     * 视频标题
     */
    private String title;

    /**
     * tag
     */
    private String tag;
}



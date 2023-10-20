package com.cdut.tiktok.video.pojo.vo;

import com.cdut.tiktok.video.pojo.to.CommentUserInfoTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetCommentVO {
    /**
     * 评论id
     */
    private Long id;
    /**
     * 评论用户id
     */
    @JsonProperty("user")
    private CommentUserInfoTO commentUserInfo;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 居住地
     */
    private String location;
    /**
     * 0代表不是评论主楼评论
     */
    @JsonProperty("to_user_id")
    private Long toUserId;
    /**
     * 0代表主楼评论，1代表次楼评论，2代表对次楼的评论
     */
    private Integer type;
    /**
     * 被评论数量
     */
    @JsonProperty("comment_count")
    private Integer commentCount;
    /**
     * 被点赞数量
     */
    @JsonProperty("like_count")
    private Integer likesCount;
    /**
     * 0不是、1是
     */
    @JsonProperty("is_liked_by_author")
    private Integer isLikedByAuthor;
    /**
     * 创建时间
     */
    @JsonProperty("create_time")
    private LocalDateTime createTime;
}

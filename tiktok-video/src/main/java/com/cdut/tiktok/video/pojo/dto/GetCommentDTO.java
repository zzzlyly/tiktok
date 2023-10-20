package com.cdut.tiktok.video.pojo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetCommentDTO {
    /**
     * 评论id
     */
    private Long id;
    /**
     * 评论用户id
     */
    private Long userId;
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
    private Long toUserId;
    /**
     * 0代表主楼评论，1代表次楼评论，2代表对次楼的评论
     */
    private Integer type;
    /**
     * 被评论数量
     */
    private Integer commentCount;
    /**
     * 被点赞数量
     */
    private Integer likesCount;
    /**
     * 0不是、1是
     */
    private Integer isLikedByAuthor;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}

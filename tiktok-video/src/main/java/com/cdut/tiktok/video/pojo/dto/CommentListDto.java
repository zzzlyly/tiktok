package com.cdut.tiktok.video.pojo.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CommentListDto {

    /**
     * token
     */
    private String token;

    /**
     * 视频id
     */
    @JsonProperty("video_id")
    private Long videoId;

    /**
     * 判断用户是否是评论的别人的评论，0代表不是，其他代表被评论的评论ID
     */
    @JsonProperty("sub_comment_id")
    private Long subCommentId;

    /**
     * 0代表主楼评论，1代表次楼评论，2代表对次楼的评论
     */
    private Integer type;
}

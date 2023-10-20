package com.cdut.tiktok.video.pojo.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddCommentDto {

    private String token;
    /**
     * 视频id
     */
    @JsonProperty("video_id")
    private Long videoId;
    /**
     * 评论用户id
     */
    @JsonProperty("user_id")
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
     * 作者id
     */
    @JsonProperty("video_author_id")
    private Long videoAuthorId;
    /**
     * 判断用户是否是评论的别人的评论，0代表不是，其他代表被评论的评论ID
     */
    @JsonProperty("sub_comment_id")
    private Long subCommentId;
    /**
     * 0代表不是评论主楼评论
     */
    @JsonProperty("to_user_id")
    private Long toUserId;
    /**
     * 0代表主楼评论，1代表次楼评论，2代表对次楼的评论
     */
    private Integer type;
}

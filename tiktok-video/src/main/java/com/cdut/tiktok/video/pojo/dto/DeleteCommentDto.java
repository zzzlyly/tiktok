package com.cdut.tiktok.video.pojo.dto;

import lombok.Data;

@Data
public class DeleteCommentDto {

    /**
     * token
     */
    private String token;
    /**
     * 评论id
     */
    private Long[] ids;

}

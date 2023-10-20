package com.cdut.tiktok.user.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LikeActionDto {
    /**
     * token
     */
    private String token;
    /**
     * video_id
     */
    @JsonProperty("video_id")
    private Long videoId;
    /**
     * action_type
     */
    @JsonProperty("action_type")
    private Integer actionType;
}

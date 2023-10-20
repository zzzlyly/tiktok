package com.cdut.tiktok.video.pojo.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LikeActionTo {
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

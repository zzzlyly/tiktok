package com.cdut.tiktok.user.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LIkeListDto {
    /**
     * token
     */
    private String token;
    /**
     * user_id
     */
    @JsonProperty("user_id")
    private Long userId;
}

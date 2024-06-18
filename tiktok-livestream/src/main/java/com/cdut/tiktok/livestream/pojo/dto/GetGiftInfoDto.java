package com.cdut.tiktok.livestream.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetGiftInfoDto {
    private String token;
    @JsonProperty("gift_id")
    private String giftId;

}

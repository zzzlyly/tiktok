package com.cdut.tiktok.livestream.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetGiftListDto {
    private String page;
    private String limit;
    private String token;
    @JsonProperty("order_field")
    private String orderField;
    private String order;
}

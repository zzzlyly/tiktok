package com.cdut.tiktok.livestream.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KeyDTO {

    @JsonProperty("stream_key")
    private String key;
}

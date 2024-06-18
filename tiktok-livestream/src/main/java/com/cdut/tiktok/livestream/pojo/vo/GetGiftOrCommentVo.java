package com.cdut.tiktok.livestream.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetGiftOrCommentVo {

    @JsonProperty("creat_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime creatTime;

    @JsonProperty("send_user_name")
    private String sendUserName;

    @JsonProperty("level")
    private String level;

    @JsonProperty("type")
    private String type;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("gift_id")
    private Long giftId;

    @JsonProperty("gift_number")
    private Integer giftNumber;
}


package com.cdut.tiktok.video.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class UploadVideoDto {

    private MultipartFile video;

    @JsonProperty("video_cover")
    private MultipartFile videoImage;

    private String token;

    @JsonProperty("video_title")
    private String videoTitle;

    private String authorId;

    @JsonProperty("video_format")
    private String videoFormat;

    @JsonProperty("video_cover_format")
    private String videoCoverFormat;
}

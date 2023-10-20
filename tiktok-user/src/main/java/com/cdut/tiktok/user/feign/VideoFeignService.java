package com.cdut.tiktok.user.feign;

import com.cdut.tiktok.user.pojo.dto.LikeActionDto;
import com.cdut.tiktok.user.pojo.to.LikeListVideoTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient("tiktok-video")
public interface VideoFeignService {

    @PostMapping("video/video/userid")
    public Long getUserIdByVideoId(LikeActionDto likeActionDto);

    @PostMapping("video/video/list/ids")
    public List<LikeListVideoTo> getVideoListByVideoId(List<Long> videoIds);


}

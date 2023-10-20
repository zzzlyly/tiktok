package com.cdut.tiktok.video.feign;


import com.cdut.tiktok.video.pojo.to.CommentUserInfoTO;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

@FeignClient("tiktok-user")
public interface UserFeignService {


    @PostMapping("user/user/commmentuser")
    public List<CommentUserInfoTO> getcommentUserInfoByIds(@RequestBody List<Long> userIds);
}

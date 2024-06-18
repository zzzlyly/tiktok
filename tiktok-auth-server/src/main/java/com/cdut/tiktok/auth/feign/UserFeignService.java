package com.cdut.tiktok.auth.feign;


import com.cdut.tiktok.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient("tiktok-user")
public interface UserFeignService {

    @GetMapping("/info/{id}")
    R getUserInfo(@PathVariable("id") String id);
}

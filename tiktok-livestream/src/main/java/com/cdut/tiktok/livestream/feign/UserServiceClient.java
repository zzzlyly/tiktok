package com.cdut.tiktok.livestream.feign;

import com.cdut.tiktok.common.utils.R;
import com.cdut.tiktok.livestream.pojo.to.GetUserEntityTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "tiktok-user")
public interface UserServiceClient {

    @GetMapping("/user/user/info")
    R getUserInfo(@RequestBody GetUserEntityTo getUserEntityTo);

}


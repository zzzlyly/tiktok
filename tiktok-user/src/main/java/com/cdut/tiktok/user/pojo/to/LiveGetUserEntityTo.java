package com.cdut.tiktok.user.pojo.to;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Builder
public class LiveGetUserEntityTo {

    /**
     * 用户id
     */
    private Long id;

    private BigDecimal giftAmount;
}

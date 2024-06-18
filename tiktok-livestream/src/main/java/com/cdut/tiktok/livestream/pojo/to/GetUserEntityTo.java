package com.cdut.tiktok.livestream.pojo.to;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Builder
public class GetUserEntityTo {

    /**
     * 用户id
     */
    private Long id;

    private BigDecimal giftAmount;
}

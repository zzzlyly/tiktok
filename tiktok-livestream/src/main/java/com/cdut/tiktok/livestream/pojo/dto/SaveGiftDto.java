package com.cdut.tiktok.livestream.pojo.dto;


import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;



/**
 * @author zzzlyly
 */
@Data
@Builder
public class SaveGiftDto {

	/**
	 * 礼物图片url
	 */
	private MultipartFile giftImage;
	/**
	 * 礼物价格
	 */
	private BigDecimal giftPrice;

	private String userId;

	private Long id;

	private String imageFormat;
}

package com.cdut.tiktok.livestream.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.livestream.entity.GiftEntity;
import com.cdut.tiktok.livestream.pojo.dto.SaveGiftDto;
import com.cdut.tiktok.livestream.pojo.to.GiftEntityVo;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 
 *
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:00:56
 */
public interface GiftService extends IService<GiftEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void savegiftDto(SaveGiftDto saveGiftDto);

    BigDecimal getGiftPriceById(Long id);


    GiftEntityVo getInfoById(String giftId);
}


package com.cdut.tiktok.livestream.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.cdut.tiktok.common.annotation.UserId;
import com.cdut.tiktok.common.utils.JwtUtils;
import com.cdut.tiktok.livestream.pojo.dto.GetGiftInfoDto;
import com.cdut.tiktok.livestream.pojo.dto.GetGiftListDto;
import com.cdut.tiktok.livestream.pojo.dto.SaveGiftDto;
import com.cdut.tiktok.livestream.pojo.to.GiftEntityVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cdut.tiktok.livestream.entity.GiftEntity;
import com.cdut.tiktok.livestream.service.GiftService;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.R;
import org.springframework.web.multipart.MultipartFile;


/**
 * 
 *
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:00:56
 */
@RestController
@RequestMapping("livestream/gift")
public class GiftController {
    @Autowired
    private GiftService giftService;

    @Autowired
    private JwtUtils jwtUtils;



    @PostMapping("/list")
    @RequiresPermissions("livestream:gift:list")
    public R list(@RequestBody GetGiftListDto giftListDto){
        String token = giftListDto.getToken();
        if (jwtUtils.getUserIdFromToken(token)!=null){
            Map<String, Object> params = new HashMap<>();
            params.put("page", giftListDto.getPage());
            params.put("limit", giftListDto.getLimit());
            params.put("orderField", giftListDto.getOrderField());
            params.put("order", giftListDto.getOrder());

            PageUtils page = giftService.queryPage(params);
            return R.ok().put("page", page);
        }
        else {
            return R.error();
        }

    }



    /**
     * 信息
     */
    @PostMapping("/info")
    @RequiresPermissions("livestream:gift:info")
    public R getInfoById(@RequestBody GetGiftInfoDto getGiftInfoDto) {
        if(jwtUtils.getUserIdFromToken(getGiftInfoDto.getToken())!= null){
            GiftEntityVo gift = giftService.getInfoById(getGiftInfoDto.getGiftId());
            return R.ok().put("gift", gift);
        }
        else {
            return R.error();
        }
    }




    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("livestream:gift:delete")
    public R delete(@RequestBody Integer[] ids){
		giftService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
    /**
     * 保存或修改
     */
    @PostMapping
    @RequiresPermissions("livestream:gift:save")
    public R save(@RequestParam("gift_image") MultipartFile image,
                  @RequestParam("price") BigDecimal giftPrice,
                  @RequestParam("image_format") String imageFormat,
                  @RequestParam("id") Long id,
                  @UserId String userId){
        SaveGiftDto saveGiftDto = SaveGiftDto.builder()
                .giftImage(image)
                .giftPrice(giftPrice)
                .userId(userId)
                .id(id)
                .imageFormat(imageFormat)
                .build();

        giftService.savegiftDto(saveGiftDto);

        return R.ok();
    }


}

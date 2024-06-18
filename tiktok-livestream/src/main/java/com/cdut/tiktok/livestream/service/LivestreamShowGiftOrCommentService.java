package com.cdut.tiktok.livestream.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.livestream.entity.LivestreamShowGiftOrCommentEntity;
import com.cdut.tiktok.livestream.pojo.dto.GetGiftOrCommentDto;
import com.cdut.tiktok.livestream.pojo.dto.SendGiftOrCommentDto;
import com.cdut.tiktok.livestream.pojo.vo.GetGiftOrCommentVo;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:00:56
 */
public interface LivestreamShowGiftOrCommentService extends IService<LivestreamShowGiftOrCommentEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void sendGiftOrComment(SendGiftOrCommentDto sendGiftOrCommentDto);

    List<GetGiftOrCommentVo> getGiftOrComment(GetGiftOrCommentDto getGiftOrCommentDto);

}


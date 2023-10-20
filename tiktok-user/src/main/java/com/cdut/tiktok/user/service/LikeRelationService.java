package com.cdut.tiktok.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.user.entity.LikeRelationEntity;
import com.cdut.tiktok.user.pojo.dto.LIkeListDto;
import com.cdut.tiktok.user.pojo.dto.LikeActionDto;
import com.cdut.tiktok.user.pojo.vo.LikeListVo;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:27:51
 */
public interface LikeRelationService extends IService<LikeRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void likeAction(LikeActionDto likeActionDto);

    List<LikeListVo> list(LIkeListDto likeListDto);
}


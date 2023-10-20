package com.cdut.tiktok.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.video.entity.CommentEntity;
import com.cdut.tiktok.video.pojo.dto.AddCommentDto;
import com.cdut.tiktok.video.pojo.vo.GetCommentVO;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:28:53
 */
public interface CommentService extends IService<CommentEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveComment(AddCommentDto comment);

    List<GetCommentVO> getCommnet(Map<String, Object> params);
}


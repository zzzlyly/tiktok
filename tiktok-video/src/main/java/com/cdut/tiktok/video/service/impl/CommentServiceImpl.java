package com.cdut.tiktok.video.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdut.tiktok.common.utils.R;
import com.cdut.tiktok.video.feign.UserFeignService;
import com.cdut.tiktok.video.pojo.dto.AddCommentDto;
import com.cdut.tiktok.video.pojo.to.CommentUserInfoTO;
import com.cdut.tiktok.video.pojo.vo.GetCommentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.Query;

import com.cdut.tiktok.video.dao.CommentDao;
import com.cdut.tiktok.video.entity.CommentEntity;
import com.cdut.tiktok.video.service.CommentService;


@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentDao, CommentEntity> implements CommentService {

    @Autowired
    private UserFeignService userFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CommentEntity> page = this.page(
                new Query<CommentEntity>().getPage(params),
                new QueryWrapper<CommentEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveComment(AddCommentDto commentEntity) {
        CommentEntity comment = new CommentEntity();

        BeanUtils.copyProperties(commentEntity,comment);

        comment.setLikesCount(0);
        comment.setIsLikedByAuthor(0);
        comment.setCreateTime(LocalDateTime.now());
        comment.setIsDeleted(0);
        comment.setUpdateTime(LocalDateTime.now());
        save(comment);
    }

    @Override
    public List<GetCommentVO> getCommnet(Map<String, Object> params) {
        int type = Integer.parseInt(params.get("type").toString());
        String token = (String)params.get("token");
        int current = Integer.parseInt((String) params.get("page"));
        int limit = Integer.parseInt((String)params.get("limit"));

        if (type != 1 && type != 2 && type != 0) {
            return null;
        } else if (type == 0){
            int videoId = Integer.parseInt((String)params.get("video_id"));
            Page page = new Page(current, limit);
            QueryWrapper<CommentEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("is_deleted", 0).eq("video_id",videoId).orderByDesc("comment_count");
            Page page1 = baseMapper.selectPage(page, queryWrapper);

            // 从Page对象中获取comment表的数据
            List<CommentEntity> commentEntityList = page1.getRecords();

            // 从comment数据中提取user_id字段的值
            List<Long> userIdList = commentEntityList.stream()
                    .map(CommentEntity::getUserId)
                    .collect(Collectors.toList());

            // 根据user_id值查询用户信息
            List<CommentUserInfoTO> commentUserInfoTOList = userFeignService.getcommentUserInfoByIds(userIdList);
            Map<Long, CommentUserInfoTO> commentUserInfoTOMap = commentUserInfoTOList.stream()
                    .collect(Collectors.toMap(CommentUserInfoTO::getId, commentUserInfoTo -> commentUserInfoTo));

            // 遍历评论列表，将用户信息与评论信息拼接
            return commentEntityList.stream()
                    .map(commentEntity -> {
                        // 获取评论的用户 ID
                        Long userId = commentEntity.getUserId();

                        // 根据用户 ID 从 Map 中获取用户信息
                        CommentUserInfoTO user = commentUserInfoTOMap.get(userId);

                        // 创建 CommentWithUser 对象，将评论信息和用户信息拼接
                        GetCommentVO commentVo = new GetCommentVO();
                        commentVo.setCommentUserInfo(user);
                        BeanUtils.copyProperties(commentEntity, commentVo);
                        return commentVo;
                    })
                    .collect(Collectors.toList());
        }


        return null;
    }


}
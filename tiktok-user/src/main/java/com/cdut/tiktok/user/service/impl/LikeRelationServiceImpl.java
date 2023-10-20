package com.cdut.tiktok.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cdut.tiktok.user.entity.UserEntity;
import com.cdut.tiktok.user.feign.VideoFeignService;
import com.cdut.tiktok.user.pojo.dto.LIkeListDto;
import com.cdut.tiktok.user.pojo.dto.LikeActionDto;
import com.cdut.tiktok.user.pojo.to.LikeActionTo;
import com.cdut.tiktok.user.pojo.to.LikeListVideoTo;
import com.cdut.tiktok.user.pojo.vo.LikeListUserInfo;
import com.cdut.tiktok.user.pojo.vo.LikeListVo;
import com.cdut.tiktok.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.Query;

import com.cdut.tiktok.user.dao.LikeRelationDao;
import com.cdut.tiktok.user.entity.LikeRelationEntity;
import com.cdut.tiktok.user.service.LikeRelationService;
import org.springframework.transaction.annotation.Transactional;


@Service("likeRelationService")
public class LikeRelationServiceImpl extends ServiceImpl<LikeRelationDao, LikeRelationEntity> implements LikeRelationService {

    @Autowired
    private UserService userService;

    @Autowired
    private VideoFeignService videoFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LikeRelationEntity> page = this.page(
                new Query<LikeRelationEntity>().getPage(params),
                new QueryWrapper<LikeRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public void likeAction(LikeActionDto likeActionDto) {
        //todo 获取userid
        long userId = 1;
        LikeRelationEntity likeRelationEntity = new LikeRelationEntity();
        BeanUtils.copyProperties(likeActionDto, likeRelationEntity);
        QueryWrapper<LikeRelationEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).eq("video_id", likeActionDto.getVideoId());
        List<LikeRelationEntity> likeRelationEntities = this.list(queryWrapper);
        likeRelationEntity.setUserId(userId);


        //如果之前已经点赞,且存在点赞记录则不做处理，无记录，则存入数据库，且在user表中更新被点赞总数和点赞总数
        if (likeActionDto.getActionType() == 1 && likeRelationEntities.isEmpty()){

            //更新点赞关系
            likeRelationEntity.setIsDeleted(0L);
            this.saveOrUpdate(likeRelationEntity);

            //todo 需要开启分布式事务
            //通过videoId查询到authorId,同时更新video的likes_count
            Long authorId = videoFeignService.getUserIdByVideoId(likeActionDto);

            //更新点赞着的点赞总数
            UpdateWrapper updateUserWrapper = new UpdateWrapper();
            updateUserWrapper.eq("id",userId);
            updateUserWrapper.setSql("like_count = like_count + 1");
            userService.update(updateUserWrapper);

            //更新被点赞着的被点赞数
            UpdateWrapper updateAuthorWrapper = new UpdateWrapper();
            updateAuthorWrapper.eq("id", authorId);
            updateAuthorWrapper.setSql("total_liked = total_liked + 1");
            userService.update(updateAuthorWrapper);


        }//存在点赞记录则删除，且在user表中更新被点赞总数和点赞总数，无记录，则不处理，且在user表中添加被点赞总数和点赞总数
        else if (likeActionDto.getActionType() == 2 && !likeRelationEntities.isEmpty()){
            likeRelationEntity.setId(likeRelationEntities.get(0).getId());
            this.removeById(likeRelationEntity);

            //todo 需要开启分布式事务
            //通过videoId查询到authorId,同时更新video的likes_count
            LikeActionTo likeActionTo = new LikeActionTo();
            BeanUtils.copyProperties(likeActionDto, likeActionTo);
            Long authorId = videoFeignService.getUserIdByVideoId(likeActionDto);

            //更新点赞着的点赞总数
            UpdateWrapper updateUserWrapper = new UpdateWrapper();
            updateUserWrapper.eq("id",userId);
            updateUserWrapper.setSql("like_count = like_count - 1");
            userService.update(updateUserWrapper);

            //更新被点赞着的被点赞数
            UpdateWrapper updateAuthorWrapper = new UpdateWrapper();
            updateAuthorWrapper.eq("id", authorId);
            updateAuthorWrapper.setSql("total_liked = total_liked - 1");
            userService.update(updateAuthorWrapper);
        }else {
            throw new IllegalStateException("Invalid action type: " + likeActionDto.getActionType());
        }

    }

    @Override
    public List<LikeListVo> list(LIkeListDto likeListDto) {
        QueryWrapper<LikeRelationEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", likeListDto.getUserId());
        List<LikeRelationEntity> likeRelationEntities = this.list(queryWrapper);

        List<Long> videoIds = likeRelationEntities.stream()
                .map(LikeRelationEntity::getVideoId)
                .collect(Collectors.toList());

        List<LikeListVideoTo> listVideoTos = videoFeignService.getVideoListByVideoId(videoIds);

        List<Long> userIds = new ArrayList<Long>(listVideoTos.stream()
                .map(LikeListVideoTo::getAuthorId)
                .collect(Collectors.toSet()));

        List<UserEntity> userEntities = userService.listByIds(userIds);

        Map<Long, LikeListUserInfo> LikeListUserInfos = userEntities.stream()
                .map(userEntity -> {
                    LikeListUserInfo likeListUserInfo = new LikeListUserInfo();
                    BeanUtils.copyProperties(userEntity, likeListUserInfo);
                    return likeListUserInfo;
                })
                .collect(Collectors.toMap(LikeListUserInfo::getId, likeListUserInfo -> likeListUserInfo));

        return listVideoTos.stream()
                .map(video -> {
                    LikeListVo likeListVo = new LikeListVo();
                    BeanUtils.copyProperties(video,likeListVo);
                    likeListVo.setAuthor(LikeListUserInfos.get(video.getAuthorId()));
                    return likeListVo;
                }).collect(Collectors.toList());
    }
}
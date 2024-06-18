package com.cdut.tiktok.livestream.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.cdut.tiktok.common.utils.R;
import com.cdut.tiktok.livestream.dao.AudienceinfoDao;
import com.cdut.tiktok.livestream.entity.AudienceinfoEntity;
import com.cdut.tiktok.livestream.feign.UserServiceClient;
import com.cdut.tiktok.livestream.messager.RabbitMQSender;
import com.cdut.tiktok.livestream.pojo.dto.GetGiftOrCommentDto;
import com.cdut.tiktok.livestream.pojo.dto.SendGiftOrCommentDto;
import com.cdut.tiktok.livestream.pojo.to.GetUserEntityTo;
import com.cdut.tiktok.livestream.pojo.to.UserEntityTo;
import com.cdut.tiktok.livestream.pojo.vo.GetGiftOrCommentVo;
import com.cdut.tiktok.livestream.service.GiftService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.Query;

import com.cdut.tiktok.livestream.dao.LivestreamShowGiftOrCommentDao;
import com.cdut.tiktok.livestream.entity.LivestreamShowGiftOrCommentEntity;
import com.cdut.tiktok.livestream.service.LivestreamShowGiftOrCommentService;


@Service("livestreamShowGiftOrCommentService")
public class LivestreamShowGiftOrCommentServiceImpl extends ServiceImpl<LivestreamShowGiftOrCommentDao, LivestreamShowGiftOrCommentEntity> implements LivestreamShowGiftOrCommentService {

    @Autowired
    private LivestreamShowGiftOrCommentDao giftOrCommentDao;

    @Autowired
    private AudienceinfoDao audienceinfoDao;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private GiftService giftService;



    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LivestreamShowGiftOrCommentEntity> page = this.page(
                new Query<LivestreamShowGiftOrCommentEntity>().getPage(params),
                new QueryWrapper<LivestreamShowGiftOrCommentEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void sendGiftOrComment(SendGiftOrCommentDto sendGiftOrCommentDto) {
        LivestreamShowGiftOrCommentEntity entity = new LivestreamShowGiftOrCommentEntity();
        BeanUtils.copyProperties(sendGiftOrCommentDto, entity);
        entity.setCreateTime(LocalDateTime.now());

        QueryWrapper<AudienceinfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", sendGiftOrCommentDto.getUserId())
                .eq("host_id", sendGiftOrCommentDto.getHostId());
        AudienceinfoEntity audienceinfoEntity = audienceinfoDao.selectOne(queryWrapper);
        Integer level = 0;
        if (audienceinfoEntity != null) {
            level = audienceinfoEntity.getLevel();
        }

        String key;
        String name;
        GetUserEntityTo getUserEntityTo = GetUserEntityTo.builder()
                .id(sendGiftOrCommentDto.getUserId())
                .build();
        Map<String, Object> userMap = (Map<String, Object>) userServiceClient.getUserInfo(getUserEntityTo).get("user");
        UserEntityTo userEntityTo = JSON.parseObject(JSON.toJSONString(userMap), UserEntityTo.class);

        name = userEntityTo.getName();
        if (name == null) {
            return;
        }
        key = sendGiftOrCommentDto.getCommentOrGift().equals("1") ? "livestreamgift:" + entity.getLivestreamId() : "livestreamcomment:" + entity.getLivestreamId();

        Map<String, Object> entityMap = new HashMap<>();
        entityMap.put("comment", entity.getComment());
        entityMap.put("creat_time", entity.getCreateTime().toString());
        entityMap.put("level", level.toString());
        entityMap.put("send_user_name", name);
        entityMap.put("type", entity.getCommentOrGift());
        entityMap.put("gift_id", entity.getGiftId().toString());
        entityMap.put("gift_number", entity.getGiftNumber().toString());

        String value = JSON.toJSONString(entityMap);
        redisTemplate.opsForZSet().add(key, value, entity.getCreateTime().toEpochSecond(ZoneOffset.UTC));

        // 序列化实体再发送
        rabbitMQSender.sendGiftOrCommentEntity(entity);
    }


    @Override
    public List<GetGiftOrCommentVo> getGiftOrComment(GetGiftOrCommentDto getGiftOrCommentDto) {
        String giftKey = "livestreamgift:" + getGiftOrCommentDto.getLivestreamId();
        String commentKey = "livestreamcomment:" + getGiftOrCommentDto.getLivestreamId();
        long startTime = getGiftOrCommentDto.getLastTime().toEpochSecond(ZoneOffset.UTC);

        Set<Object> giftResultSetRaw = redisTemplate.opsForZSet().rangeByScore(giftKey, startTime, Double.POSITIVE_INFINITY);
        Set<Object> commentResultSetRaw = redisTemplate.opsForZSet().reverseRangeByScore(commentKey, startTime, Double.POSITIVE_INFINITY, 0, 5);

        List<GetGiftOrCommentVo> gifts = giftResultSetRaw.stream()
                .map(object -> JSON.parseObject(Objects.toString(object, null), GetGiftOrCommentVo.class))
                .collect(Collectors.toList());

        List<GetGiftOrCommentVo> comments = commentResultSetRaw.stream()
                .map(object -> JSON.parseObject(Objects.toString(object, null), GetGiftOrCommentVo.class))
                .collect(Collectors.toList());

        gifts.addAll(comments);
        return gifts;
    }
}
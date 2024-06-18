package com.cdut.tiktok.livestream.messager;


import com.alibaba.fastjson.JSON;
import com.cdut.tiktok.livestream.entity.LivestreamShowGiftOrCommentEntity;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendGiftOrCommentEntity(LivestreamShowGiftOrCommentEntity entity) {
        String message = serializeEntity(entity);
        rabbitTemplate.convertAndSend("livestreamexchange", "my_routing_key_giftorcomment", message);
    }

    public String serializeEntity(LivestreamShowGiftOrCommentEntity entity) {
        return JSON.toJSONString(entity);
    }
}


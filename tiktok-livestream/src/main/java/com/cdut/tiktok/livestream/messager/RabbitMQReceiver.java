package com.cdut.tiktok.livestream.messager;

import com.alibaba.fastjson.JSON;
import com.cdut.tiktok.livestream.entity.LivestreamShowGiftOrCommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import com.cdut.tiktok.livestream.service.LivestreamShowGiftOrCommentService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RabbitMQReceiver {

    private static final int BATCH_SIZE = 10;

    @Autowired
    private LivestreamShowGiftOrCommentService giftOrCommentService;

    private final List<LivestreamShowGiftOrCommentEntity> batchList = Collections.synchronizedList(new ArrayList<>());

    @RabbitListener(queues = "queue_giftorcomment")
    public void receiveMessage(String json) {
        if (json == null || json.isEmpty()) {
            // 日志记录或错误处理
            return;
        }
        LivestreamShowGiftOrCommentEntity entity = deserializeEntity(json);

        synchronized (batchList) {
            batchList.add(entity);
            if (batchList.size() >= BATCH_SIZE) {
                processBatch();
            }
        }
    }

    private void processBatch() {
        synchronized (batchList) {
            if (!batchList.isEmpty()) {
                giftOrCommentService.saveBatch(new ArrayList<>(batchList));
                batchList.clear();
            }
        }
    }

    // 每10秒检查一次集合，如果有数据则处理
    @Scheduled(cron = "*/10 * * * * ?")
    public void scheduledBatchProcess() {
        if (!batchList.isEmpty()) {
            processBatch();
        }
    }

    public LivestreamShowGiftOrCommentEntity deserializeEntity(String json) {
        return JSON.parseObject(json, LivestreamShowGiftOrCommentEntity.class);
    }
}





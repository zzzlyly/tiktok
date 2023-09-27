package com.cdut.tiktok.livestream;

import com.cdut.tiktok.livestream.entity.AudienceinfoEntity;
import com.cdut.tiktok.livestream.service.AudienceinfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class TiktokLivestreamApplicationTests {

    @Autowired
    AudienceinfoService audienceinfoService;

    @Test
    public void contextLoads() {
        AudienceinfoEntity audienceinfoEntity = new AudienceinfoEntity();
        audienceinfoEntity.setCreatedTime(LocalDateTime.now());
        audienceinfoEntity.setId(6666L);
        audienceinfoService.updateById(audienceinfoEntity);
        System.out.println(audienceinfoEntity);
    }

}

package com.cdut.tiktok.livestream.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.model.PutObjectRequest;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdut.tiktok.common.exception.ExceptionCode;
import com.cdut.tiktok.common.exception.MyException;
import com.cdut.tiktok.livestream.pojo.dto.SaveGiftDto;
import com.cdut.tiktok.livestream.pojo.to.GiftEntityVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.Query;

import com.cdut.tiktok.livestream.dao.GiftDao;
import com.cdut.tiktok.livestream.entity.GiftEntity;
import com.cdut.tiktok.livestream.service.GiftService;


@Service("giftService")
@Slf4j
public class GiftServiceImpl extends ServiceImpl<GiftDao, GiftEntity> implements GiftService {

    @Autowired
    GiftDao giftDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GiftEntity> page = new Query<GiftEntity>().getPage(params);
        IPage<GiftEntity> resultPage = giftDao.selectPage(page, new QueryWrapper<>());

        // 将GiftEntity转换为GiftEntityVo
        List<GiftEntityVo> voList = resultPage.getRecords().stream()
                .map(entity -> {
                    GiftEntityVo vo = new GiftEntityVo();
                    BeanUtils.copyProperties(entity, vo);
                    return vo;
                }).collect(Collectors.toList());

        // 创建一个新的Page对象来返回，这个对象使用voList作为记录
        IPage<GiftEntityVo> voPage = new Page<>();
        voPage.setRecords(voList);
        voPage.setCurrent(resultPage.getCurrent());
        voPage.setSize(resultPage.getSize());
        voPage.setTotal(resultPage.getTotal());
        voPage.setPages(resultPage.getPages());

        return new PageUtils(voPage);
    }


    @Override
    public void savegiftDto(SaveGiftDto saveGiftDto) {
        String endpoint = "https://oss-cn-chengdu.aliyuncs.com";
        String bucketName = "web-zzzly";

        String giftImageName = "gift_image" + "/" + System.currentTimeMillis() + "_" + saveGiftDto.getUserId()+"."+ saveGiftDto.getImageFormat();

        // 创建OSSClient实例。
        OSS ossClient = null;

        try {
            // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
            EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

            ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);

            // 创建PutObjectRequest对象。

            PutObjectRequest putObjectRequestGiftImage = new PutObjectRequest(bucketName, giftImageName, new ByteArrayInputStream(saveGiftDto.getGiftImage().getBytes()));
            // 上传文件。

            ossClient.putObject(putObjectRequestGiftImage);

            String giftImageUrl = "https://" + bucketName + ".oss-cn-chengdu.aliyuncs.com" + "/" + giftImageName;

            GiftEntity giftEntity =  GiftEntity.builder()
                    .giftUrl(giftImageUrl)
                    .giftPrice(saveGiftDto.getGiftPrice())
                    .createTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build();

            if (saveGiftDto.getId()!=null){
                giftEntity.setId(saveGiftDto.getId());
                giftDao.updateById(giftEntity);
            }
            else {
                giftDao.insert(giftEntity);
            }


        } catch (OSSException oe) {
            log.info("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            log.info("Error Message:" + oe.getErrorMessage());
            log.info("Error Code:" + oe.getErrorCode());
            log.info("Request ID:" + oe.getRequestId());
            log.info("Host ID:" + oe.getHostId());
            throw new MyException(ExceptionCode.FILESTORAGE_ERROR);
        } catch (Exception e) {
            throw new MyException(ExceptionCode.FILESTORAGE_ERROR);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    @Override
    public BigDecimal getGiftPriceById(Long id) {
        GiftEntity gistEntity = giftDao.selectById(id);
        return gistEntity.getGiftPrice();
    }

    @Override
    public GiftEntityVo getInfoById(String giftId) {
        GiftEntity giftEntity = giftDao.selectById(giftId);
        GiftEntityVo vo = new GiftEntityVo();
        BeanUtils.copyProperties(giftEntity,vo);
        return vo;
    }


}
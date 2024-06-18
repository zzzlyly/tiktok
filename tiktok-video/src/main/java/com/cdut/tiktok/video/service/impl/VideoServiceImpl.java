package com.cdut.tiktok.video.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cdut.tiktok.common.exception.ExceptionCode;
import com.cdut.tiktok.common.exception.MyException;
import com.cdut.tiktok.video.pojo.dto.UploadVideoDto;
import com.cdut.tiktok.video.pojo.to.LikeActionTo;
import com.cdut.tiktok.video.pojo.to.LikeListVideoTo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.Query;

import com.cdut.tiktok.video.dao.VideoDao;
import com.cdut.tiktok.video.entity.VideoEntity;
import com.cdut.tiktok.video.service.VideoService;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("videoService")
public class VideoServiceImpl extends ServiceImpl<VideoDao, VideoEntity> implements VideoService {

    @Autowired
    VideoDao videoDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<VideoEntity> page = this.page(
                new Query<VideoEntity>().getPage(params),
                new QueryWrapper<VideoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public Long getUserIdByVideoId(LikeActionTo likeActionto) {

        UpdateWrapper<VideoEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", likeActionto.getVideoId());
        if (likeActionto.getActionType() == 1) {
            updateWrapper.setSql("likes_count = likes_count + 1");
        } else {
            updateWrapper.setSql("likes_count = likes_count - 1");
        }
        this.update(updateWrapper);

        QueryWrapper<VideoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", likeActionto.getVideoId());
        return this.getOne(queryWrapper).getAuthorId();
    }

    @Override
    public List<LikeListVideoTo> getVideoListByVideoIds(List<Long> videoIds) {
        QueryWrapper<VideoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", videoIds);
        List<VideoEntity> videoEntityList = this.list(queryWrapper);

        return videoEntityList.stream()
                .map(videoEntity -> {
                    LikeListVideoTo likeListVideoTo = new LikeListVideoTo();
                    BeanUtils.copyProperties(videoEntity, likeListVideoTo);
                    return likeListVideoTo;
                }).collect(Collectors.toList());
    }

    @Override
    public void uploadVideo(UploadVideoDto uploadVideoDto) throws MyException{

        String endpoint = "https://oss-cn-chengdu.aliyuncs.com";
        String bucketName = "web-zzzly";
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        String videofileName = "video"+ "/"+formatter.format(new Date(System.currentTimeMillis())) + "/" + System.currentTimeMillis() + "_" + uploadVideoDto.getAuthorId()+"."+ uploadVideoDto.getVideoFormat();
        String videoImageName = "video_image" + "/" + System.currentTimeMillis() + "_" + uploadVideoDto.getAuthorId()+"."+ uploadVideoDto.getVideoCoverFormat();

        // 创建OSSClient实例。
        OSS ossClient = null;

        try {
            // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
            EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

            ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);

            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequestVideo = new PutObjectRequest(bucketName, videofileName, new ByteArrayInputStream(uploadVideoDto.getVideo().getBytes()));
            PutObjectRequest putObjectRequestVideoImage = new PutObjectRequest(bucketName, videoImageName, new ByteArrayInputStream(uploadVideoDto.getVideoImage().getBytes()));
            // 上传文件。
            ossClient.putObject(putObjectRequestVideo);
            ossClient.putObject(putObjectRequestVideoImage);

            String videoUrl = "https://" + bucketName + ".oss-cn-chengdu.aliyuncs.com"  + "/" + videofileName;
            String videoImageUrl = "https://" + bucketName + ".oss-cn-chengdu.aliyuncs.com" + "/" + videoImageName;

            VideoEntity videoEntity =  VideoEntity.builder()
                    .coverUrl(videoImageUrl)
                    .playUrl(videoUrl)
                    .title(uploadVideoDto.getVideoTitle())
                    .authorId(Long.parseLong(uploadVideoDto.getAuthorId()))
                    .createTime(LocalDateTime.now())
                    .build();

            videoDao.insert(videoEntity);

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


}
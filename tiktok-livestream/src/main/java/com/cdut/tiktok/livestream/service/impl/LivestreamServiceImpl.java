package com.cdut.tiktok.livestream.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdut.tiktok.livestream.pojo.to.LivestreamEntityVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.Query;

import com.cdut.tiktok.livestream.dao.LivestreamDao;
import com.cdut.tiktok.livestream.entity.LivestreamEntity;
import com.cdut.tiktok.livestream.service.LivestreamService;


@Service("livestreamService")
public class LivestreamServiceImpl extends ServiceImpl<LivestreamDao, LivestreamEntity> implements LivestreamService {

    @Autowired
    private LivestreamDao livestreamDao;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LivestreamEntity> page = this.page(
                new Query<LivestreamEntity>().getPage(params),
                new QueryWrapper<LivestreamEntity>()
        );

        // 创建一个新的分页对象，用于存放转换后的VO数据
        List<LivestreamEntityVo> voList = page.getRecords().stream()
                .map(this::convertToVo)
                .collect(Collectors.toList());

        // 用新的VO列表创建分页数据
        Page<LivestreamEntityVo> voPage = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        voPage.setRecords(voList);

        return new PageUtils(voPage);
    }

    private LivestreamEntityVo convertToVo(LivestreamEntity entity) {
        LivestreamEntityVo vo = new LivestreamEntityVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    @Override
    public String getStreamKey(String userId) {

        LivestreamEntity livestream = livestreamDao.selectById(userId);
        if(livestream!=null){
            // 如果存在，直接返回
            return livestream.getStreamKey();
        }
        else{
            // 如果不存在，生成新的StreamKey
            String newStreamKey = UUID.randomUUID().toString().replace("-", "");
            LivestreamEntity livestreamEntity = LivestreamEntity.builder()
                    .streamKey(newStreamKey)
                    .videoUrl("rtmp://192.168.1.3/live/"+newStreamKey)
                    .hostId(Long.parseLong(userId))
                    .build();

            livestreamDao.insert(livestreamEntity);
            return newStreamKey;
        }

    }

    @Override
    public boolean isValidKey(String key) {
        QueryWrapper<LivestreamEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stream_key", key);
        LivestreamEntity livestream = livestreamDao.selectOne(queryWrapper);
        // 如果存在，直接返回
        return livestream != null;
    }

}
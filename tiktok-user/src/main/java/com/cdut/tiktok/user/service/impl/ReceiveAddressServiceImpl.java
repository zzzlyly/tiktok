package com.cdut.tiktok.user.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.Query;

import com.cdut.tiktok.user.dao.ReceiveAddressDao;
import com.cdut.tiktok.user.entity.ReceiveAddressEntity;
import com.cdut.tiktok.user.service.ReceiveAddressService;


@Service("receiveAddressService")
public class ReceiveAddressServiceImpl extends ServiceImpl<ReceiveAddressDao, ReceiveAddressEntity> implements ReceiveAddressService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ReceiveAddressEntity> page = this.page(
                new Query<ReceiveAddressEntity>().getPage(params),
                new QueryWrapper<ReceiveAddressEntity>()
        );

        return new PageUtils(page);
    }

}
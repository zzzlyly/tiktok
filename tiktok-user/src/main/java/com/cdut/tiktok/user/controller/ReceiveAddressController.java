package com.cdut.tiktok.user.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdut.tiktok.user.entity.ReceiveAddressEntity;
import com.cdut.tiktok.user.service.ReceiveAddressService;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.R;



/**
 * 
 *
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:27:51
 */
@RestController
@RequestMapping("user/receiveaddress")
public class ReceiveAddressController {
    @Autowired
    private ReceiveAddressService receiveAddressService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("user:receiveaddress:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = receiveAddressService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("user:receiveaddress:info")
    public R info(@PathVariable("id") Long id){
		ReceiveAddressEntity receiveAddress = receiveAddressService.getById(id);

        return R.ok().put("receiveAddress", receiveAddress);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("user:receiveaddress:save")
    public R save(@RequestBody ReceiveAddressEntity receiveAddress){
		receiveAddressService.save(receiveAddress);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("user:receiveaddress:update")
    public R update(@RequestBody ReceiveAddressEntity receiveAddress){
		receiveAddressService.updateById(receiveAddress);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("user:receiveaddress:delete")
    public R delete(@RequestBody Long[] ids){
		receiveAddressService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

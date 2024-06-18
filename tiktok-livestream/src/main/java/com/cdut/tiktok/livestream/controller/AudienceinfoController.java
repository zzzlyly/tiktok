package com.cdut.tiktok.livestream.controller;

import java.util.Arrays;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cdut.tiktok.livestream.entity.AudienceinfoEntity;
import com.cdut.tiktok.livestream.service.AudienceinfoService;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.R;




/**
 * 
 *
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:00:56
 */
@Slf4j
@RestController
@RequestMapping("livestream/audienceinfo")
public class AudienceinfoController {
    @Autowired
    private AudienceinfoService audienceinfoService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("livestream:audienceinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = audienceinfoService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("livestream:audienceinfo:info")
    public R info(@PathVariable("id") Long id){
		AudienceinfoEntity audienceinfo = audienceinfoService.getById(id);
        return R.ok().put("audienceinfo", audienceinfo);
    }

    /**
     * 保存
     */
    @PostMapping
    @RequiresPermissions("livestream:audienceinfo:save")
    public R save(@RequestBody AudienceinfoEntity audienceinfo){
		audienceinfoService.save(audienceinfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping
    @RequiresPermissions("livestream:audienceinfo:update")
    public R update(@RequestBody AudienceinfoEntity audienceinfo){
		audienceinfoService.updateById(audienceinfo);

        return R.ok();
    }

}

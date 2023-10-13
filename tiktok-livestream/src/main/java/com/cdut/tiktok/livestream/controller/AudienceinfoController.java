package com.cdut.tiktok.livestream.controller;

import java.util.Arrays;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping("/list")
    @RequiresPermissions("livestream:audienceinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = audienceinfoService.queryPage(params);
        log.info("转发成功");
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("livestream:audienceinfo:info")
    public R info(@PathVariable("id") Long id){
		AudienceinfoEntity audienceinfo = audienceinfoService.getById(id);
        return R.ok().put("audienceinfo", audienceinfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("livestream:audienceinfo:save")
    public R save(@RequestBody AudienceinfoEntity audienceinfo){
		audienceinfoService.save(audienceinfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("livestream:audienceinfo:update")
    public R update(@RequestBody AudienceinfoEntity audienceinfo){
		audienceinfoService.updateById(audienceinfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("livestream:audienceinfo:delete")
    public R delete(@RequestBody Long[] ids){
		audienceinfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

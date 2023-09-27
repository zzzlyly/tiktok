package com.cdut.tiktok.product.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdut.tiktok.product.entity.ProductimageEntity;
import com.cdut.tiktok.product.service.ProductimageService;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.R;



/**
 * 
 *
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:25:55
 */
@RestController
@RequestMapping("product/productimage")
public class ProductimageController {
    @Autowired
    private ProductimageService productimageService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:productimage:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = productimageService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:productimage:info")
    public R info(@PathVariable("id") Long id){
		ProductimageEntity productimage = productimageService.getById(id);

        return R.ok().put("productimage", productimage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:productimage:save")
    public R save(@RequestBody ProductimageEntity productimage){
		productimageService.save(productimage);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:productimage:update")
    public R update(@RequestBody ProductimageEntity productimage){
		productimageService.updateById(productimage);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:productimage:delete")
    public R delete(@RequestBody Long[] ids){
		productimageService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

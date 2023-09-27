package com.cdut.tiktok.order.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdut.tiktok.order.entity.OrderShoppingEntity;
import com.cdut.tiktok.order.service.OrderShoppingService;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.R;



/**
 * 
 *
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:23:33
 */
@RestController
@RequestMapping("order/ordershopping")
public class OrderShoppingController {
    @Autowired
    private OrderShoppingService orderShoppingService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("order:ordershopping:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderShoppingService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("order:ordershopping:info")
    public R info(@PathVariable("id") Long id){
		OrderShoppingEntity orderShopping = orderShoppingService.getById(id);

        return R.ok().put("orderShopping", orderShopping);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("order:ordershopping:save")
    public R save(@RequestBody OrderShoppingEntity orderShopping){
		orderShoppingService.save(orderShopping);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("order:ordershopping:update")
    public R update(@RequestBody OrderShoppingEntity orderShopping){
		orderShoppingService.updateById(orderShopping);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("order:ordershopping:delete")
    public R delete(@RequestBody Long[] ids){
		orderShoppingService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

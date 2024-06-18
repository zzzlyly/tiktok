package com.cdut.tiktok.user.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.cdut.tiktok.user.pojo.dto.LoginUserDto;
import com.cdut.tiktok.user.pojo.dto.RegisterDto;
import com.cdut.tiktok.user.pojo.to.CommentUserInfoTO;
import com.cdut.tiktok.user.pojo.to.LiveGetUserEntityTo;
import com.cdut.tiktok.user.pojo.vo.AuthCaptchaVo;
import com.cdut.tiktok.user.pojo.vo.UserEntityVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cdut.tiktok.user.entity.UserEntity;
import com.cdut.tiktok.user.service.UserService;
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
@RequestMapping("user/user")
public class UserController {
    @Autowired
    private UserService userService;


    /**
     *获取评论用户的信息
     */
    @PostMapping("/commmentuser")
    public List<CommentUserInfoTO> getcommentUserInfoByIds(@RequestBody List<Long> userIds){
        List<UserEntity> userEntities = userService.listByIds(userIds);
        return userEntities.stream()
                .map(userEntity -> {
                    CommentUserInfoTO commentUserInfoTo = new CommentUserInfoTO();
                    BeanUtils.copyProperties(userEntity, commentUserInfoTo);
                    return commentUserInfoTo;
                })
                .collect(Collectors.toList());
    }




    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("user:user:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/id")
    public R info(@RequestParam("id") String id){
		UserEntity user = userService.getById(id);
        return R.ok().put("user", user);
    }


    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("user:user:save")
    public R save(@RequestBody UserEntity user){
		userService.save(user);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("user:user:update")
    public R update(@RequestBody UserEntity user){
		userService.updateById(user);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("user:user:delete")
    public R delete(@RequestBody Long[] ids){
		userService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @PostMapping("/register")
    public R register(@RequestBody RegisterDto user) {
        boolean result = userService.registerUser(user);
        if (result){
            return R.ok();
        }
        else{
            return R.error("User registration failed");
        }

    }

    @PostMapping("/login")
    public R register(@RequestBody LoginUserDto user) {
        String token = userService.login(user);
        if (token !=null){
            return R.ok().put("token",token);
        }
        else {
            return R.error("User login failed");
        }
    }

    /**
     * 获取谷歌验证码
     * @param
     */
    @GetMapping("/captcha")
    public R getCaptcha(){
        AuthCaptchaVo authCaptchaVo = userService.getCaptcha();
        return R.ok().put("key",authCaptchaVo.getKey()).put("captcha",authCaptchaVo.getCaptcherImg());
    }

    @PostMapping("/info")
    public R getUserInfo(@RequestBody LiveGetUserEntityTo liveGetUserEntityTo) {

        UserEntityVo userEntity = userService.getUserForLive(liveGetUserEntityTo);

        return R.ok().put("user",userEntity);

    }
}

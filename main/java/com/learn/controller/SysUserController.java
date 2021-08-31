package com.learn.controller;

import com.learn.entity.SysUserEntity;
import com.learn.service.SysUserRoleService;
import com.learn.service.SysUserService;
import com.learn.utils.PageUtils;
import com.learn.utils.Query;
import com.learn.utils.R;
import com.learn.utils.ShiroUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @date 31日 上午10:40:10
 */
@RestController
//这个参数的意思是指 映射一个地址给前端调用 比用这里 前端调用的地址就是
//http://127.0.0.1:8080/sys/user 这个就是这个contaoller的前缀
// 要调用具体的方法 就还要在方法上面 配置这个参数
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 所有用户列表
     */
    //    映射一个地址给前端调用 http://127.0.0.1:8080/sys/user/list 里面就是具体的任务逻辑
    //@RequestParam 这个就是用来配置 接收前端的参数 将前端传的参数封装到一个map里面 比如前端传了一个id
    //这里就可以通过params.get("id")获得值
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {

        //查询列表数据
        Query query = new Query(params);
        List<SysUserEntity> userList = sysUserService.queryList(query);
        int total = sysUserService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    @RequestMapping("/list2")
    public R list2(@RequestParam Map<String, Object> params) {

        //查询列表数据
        Query query = new Query(params);
        List<SysUserEntity> userList = sysUserService.queryList(query);

        return R.ok().put("list", userList);
    }

    /**
     * 获取登录的用户信息
     */
    @RequestMapping("/info")
    public R info() {
        return R.ok().put("user", getUser());
    }


    @RequestMapping("/updateInfo")
    public R updateInfo(@RequestBody SysUserEntity user) {
        this.sysUserService.update(user);
        return R.ok();
    }

    /**
     * 修改登录用户密码
     */
    //    映射一个地址给前端调用 http://127.0.0.1:8080/sys/user/password 里面就是具体的任务逻辑
    //    这样写的话  前端传的值 它的key只能为password 和newPassword 才能意义对应

    //    public R password(@RequestParam("password11")String password, String newPassword)
    //      这样写的话 前端传的key就是password11 但是你可以自定义命名为password
    @RequestMapping("/password")
    public R password(String password, String newPassword) {
        Assert.notNull(newPassword, "The new password cannot be empty");

        //sha256加密
        password = new Sha256Hash(password).toHex();
        //sha256加密
        newPassword = new Sha256Hash(newPassword).toHex();

        //更新密码
        int count = sysUserService.updatePassword(getUserId(), password, newPassword);
        if (count == 0) {
            return R.error("The original password is incorrect");
        }

        //退出
        ShiroUtils.logout();

        return R.ok();
    }

    /**
     * 用户信息
     */
    @RequestMapping("/info/{userId}")
    public R info(@PathVariable("userId") Long userId) {
        SysUserEntity user = sysUserService.queryObject(userId);

        //获取用户所属的角色列表
        List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);

        return R.ok().put("user", user);
    }

    /**
     * 保存用户
     */
    @RequestMapping("/save")
    public R save(@RequestBody SysUserEntity user) {

        user.setCreateUserId(getUserId());
        sysUserService.save(user);

        return R.ok();
    }

    /**
     * 修改用户
     */
    @RequestMapping("/update")
    public R update(@RequestBody SysUserEntity user) {

        user.setCreateUserId(getUserId());
        sysUserService.update(user);

        return R.ok();
    }

    /**
     *delete
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] userIds) {
        if (ArrayUtils.contains(userIds, 1L) || ArrayUtils.contains(userIds, -1L)) {
            return R.error("The system administrator cannot delete it");
        }

        if (ArrayUtils.contains(userIds, getUserId())) {
            return R.error("The current user cannot be deleted");
        }

        sysUserService.deleteBatch(userIds);

        return R.ok();
    }
}

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
 * sys user
 *
 * @date 
 */
@RestController
// This parameter is used to map an address to the front-end call This is the contaoller prefix 
// http://127.0.0.1:8080/sys/user
// To call a specific method, you need to configure this parameter on the method
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * all user list
     */
    //   An address mapping to the front-end calls http://127.0.0.1:8080/sys/user/list inside logic is specific tasks
    //@requestParam this is used to configure the parameters of the receiving front end to encapsulate the parameters of the front end into a map, such as the front end to pass an ID
    //Get ("id") to get the value
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {

        //query list
        Query query = new Query(params);
        List<SysUserEntity> userList = sysUserService.queryList(query);
        int total = sysUserService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    @RequestMapping("/list2")
    public R list2(@RequestParam Map<String, Object> params) {

        //query list
        Query query = new Query(params);
        List<SysUserEntity> userList = sysUserService.queryList(query);

        return R.ok().put("list", userList);
    }

    /**
     * get login information
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
     * Change the password of a login user
     */
    //    An address mapping to the front-end calls http://127.0.0.1:8080/sys/user/password inside logic is specific tasks
    //   In this way, the key of the value passed by the front end can only be password and newPassword

    //    public R password(@RequestParam("password11")String password, String newPassword)
    //      So the key that's going to be passed in front is password11 but you can call it password if you want
    @RequestMapping("/password")
    public R password(String password, String newPassword) {
        Assert.notNull(newPassword, "The new password cannot be empty");

        //sha256
        password = new Sha256Hash(password).toHex();
        //sha256
        newPassword = new Sha256Hash(newPassword).toHex();

        //update password
        int count = sysUserService.updatePassword(getUserId(), password, newPassword);
        if (count == 0) {
            return R.error("The original password is incorrect");
        }

        //exit
        ShiroUtils.logout();

        return R.ok();
    }

    /**
     * information for user
     */
    @RequestMapping("/info/{userId}")
    public R info(@PathVariable("userId") Long userId) {
        SysUserEntity user = sysUserService.queryObject(userId);

        //Gets the role list to which the user belongs
        List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);

        return R.ok().put("user", user);
    }

    /**
     * save user
     */
    @RequestMapping("/save")
    public R save(@RequestBody SysUserEntity user) {

        user.setCreateUserId(getUserId());
        sysUserService.save(user);

        return R.ok();
    }

    /**
     * modify user
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

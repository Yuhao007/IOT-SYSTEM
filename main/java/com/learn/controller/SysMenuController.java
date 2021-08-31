package com.learn.controller;

import com.learn.entity.SysMenuEntity;
import com.learn.service.SysMenuService;
import com.learn.utils.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * system menu
 * 
 *
 *
 * @date 27 9:58:15
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {
	@Autowired
	private SysMenuService sysMenuService;
	
	/**
	 * menu list
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//data list
		Query query = new Query(params);
		List<SysMenuEntity> menuList = sysMenuService.queryList(query);
		int total = sysMenuService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(menuList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * Select menu (Add, modify menu)
	 */
	@RequestMapping("/select")
	public R select(){
		//Query list data
		List<SysMenuEntity> menuList = sysMenuService.queryNotButtonList();
		
		//Adding top-level menus
		SysMenuEntity root = new SysMenuEntity();
		root.setMenuId(0L);
		root.setName("Level 1 menu");
		root.setParentId(-1L);
		root.setOpen(true);
		menuList.add(root);
		
		return R.ok().put("menuList", menuList);
	}
	
	/**
	 * Role Authorization Menu
	 */
	@RequestMapping("/perms")
	public R perms(){
		//Query list data
		List<SysMenuEntity> menuList = null;
		
		//Only the super administrator can view the administrator list
		if(getUserId()  == Constant.SUPER_ADMIN){
			menuList = sysMenuService.queryList(new HashMap<String, Object>());
		}else{
			menuList = sysMenuService.queryUserList(getUserId());
		}
		
		return R.ok().put("menuList", menuList);
	}
	
	/**
	 * menu
	 */
	@RequestMapping("/info/{menuId}")
	public R info(@PathVariable("menuId") Long menuId){
		SysMenuEntity menu = sysMenuService.queryObject(menuId);
		return R.ok().put("menu", menu);
	}
	
	/**
	 * save
	 */
	@RequestMapping("/save")
	public R save(@RequestBody SysMenuEntity menu){
		//verify
		verifyForm(menu);
		
		sysMenuService.save(menu);
		
		return R.ok();
	}
	
	/**
	 * config
	 */
	@RequestMapping("/update")
	public R update(@RequestBody SysMenuEntity menu){
		//verify
		verifyForm(menu);
				
		sysMenuService.update(menu);
		
		return R.ok();
	}
	
	/**
	 * dalete
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Long[] menuIds){
		for(Long menuId : menuIds){
			if(menuId.longValue() <= 30){
				return R.error("This menu can not be remove");
			}
		}
		sysMenuService.deleteBatch(menuIds);
		
		return R.ok();
	}
	
	/**
	 * user list
	 */
	@RequestMapping("/user")
	public R user(){
		List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(getUserId());
		
		return R.ok().put("menuList", menuList);
	}
	
	/**
	 * check
	 */
	private void verifyForm(SysMenuEntity menu){
		if(StringUtils.isBlank(menu.getName())){
			throw new RRException("The menu name cannot be empty");
		}
		
		if(menu.getParentId() == null){
			throw new RRException("The superior menu cannot be empty");
		}
		
		//
		if(menu.getType() == Constant.MenuType.MENU.getValue()){
			if(StringUtils.isBlank(menu.getUrl())){
				throw new RRException("The menu URL cannot be empty");
			}
		}
		
		//Upper menu type
		int parentType = Constant.MenuType.CATALOG.getValue();
		if(menu.getParentId() != 0){
			SysMenuEntity parentMenu = sysMenuService.queryObject(menu.getParentId());
			parentType = parentMenu.getType();
		}
		
		//Contents, menus
		if(menu.getType() == Constant.MenuType.CATALOG.getValue() ||
				menu.getType() == Constant.MenuType.MENU.getValue()){
			if(parentType != Constant.MenuType.CATALOG.getValue()){
				throw new RRException("The upper-level menu can only be a directory type");
			}
			return ;
		}
		
		//button
		if(menu.getType() == Constant.MenuType.BUTTON.getValue()){
			if(parentType != Constant.MenuType.MENU.getValue()){
				throw new RRException("The upper-level menu can only be of the menu type");
			}
			return ;
		}
	}
}

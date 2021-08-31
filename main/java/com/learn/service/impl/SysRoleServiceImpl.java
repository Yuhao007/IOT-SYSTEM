package com.learn.service.impl;

import com.learn.entity.SysRoleEntity;
import com.learn.service.SysRoleMenuService;
import com.learn.service.SysRoleService;
import com.learn.service.SysUserRoleService;
import com.learn.service.SysUserService;
import com.learn.utils.Constant;
import com.learn.utils.RRException;
import com.learn.dao.SysRoleDao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * rolw
 * 
 *
 *
 * @date #9##1118  9:45:12
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysUserService sysUserService;

	@Override
	public SysRoleEntity queryObject(Long roleId) {
		return sysRoleDao.queryObject(roleId);
	}

	@Override
	public List<SysRoleEntity> queryList(Map<String, Object> map) {
		return sysRoleDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysRoleDao.queryTotal(map);
	}

	@Override
	@Transactional
	public void save(SysRoleEntity role) {
		role.setCreateTime(new Date());
		sysRoleDao.save(role);
		
		//Check whether the authority is exceeded
		checkPrems(role);
		
		//Save the role and menu relationship
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
	}

	@Override
	@Transactional
	public void update(SysRoleEntity role) {
		sysRoleDao.update(role);
		
		//Check whether the authority is exceeded
		checkPrems(role);
		
		//Updated the relationship between roles and menus
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
	}

	@Override
	@Transactional
	public void deleteBatch(Long[] roleIds) {
		sysRoleDao.deleteBatch(roleIds);
	}
	
	@Override
	public List<Long> queryRoleIdList(Long createUserId) {
		return sysRoleDao.queryRoleIdList(createUserId);
	}

	/**
	 * Check whether the authority is exceeded
	 */
	private void checkPrems(SysRoleEntity role){
		//If you are not a super administrator, check whether the rights of the role exceed your own rights
		if(role.getCreateUserId()  == Constant.SUPER_ADMIN){
			return ;
		}
		
		//Query the list of menus owned by the user
		List<Long> menuIdList = sysUserService.queryAllMenuId(role.getCreateUserId());
		
		//Determine if you overstepped your authority
		if(!menuIdList.containsAll(role.getMenuIdList())){
			throw new RRException("The permission of the new role has exceeded your permission scope");
		}
	}
}

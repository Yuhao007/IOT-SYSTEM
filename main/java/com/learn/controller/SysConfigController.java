package com.learn.controller;

import com.learn.entity.SysConfigEntity;
import com.learn.service.SysConfigService;
import com.learn.utils.PageUtils;
import com.learn.utils.Query;
import com.learn.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * System Configuration information
 * 
 *
 *
 * @date 114日 下午6:55:53
 */
@RestController
@RequestMapping("/sys/config")
public class SysConfigController extends AbstractController {
	@Autowired
	private SysConfigService sysConfigService;
	
	/**
	 * System Configuration information list
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//query data list
		Query query = new Query(params);
		List<SysConfigEntity> configList = sysConfigService.queryList(query);
		int total = sysConfigService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(configList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 *  Configuration information
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Long id){
		SysConfigEntity config = sysConfigService.queryObject(id);
		
		return R.ok().put("config", config);
	}
	
	/**
	 * save
	 */
	@RequestMapping("/save")
	public R save(@RequestBody SysConfigEntity config){

		sysConfigService.save(config);
		
		return R.ok();
	}
	
	/**
	 * config
	 */
	@RequestMapping("/update")
	public R update(@RequestBody SysConfigEntity config){

		sysConfigService.update(config);
		
		return R.ok();
	}
	
	/**
	 * delete
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Long[] ids){
		sysConfigService.deleteBatch(ids);
		
		return R.ok();
	}

}

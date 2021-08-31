package com.learn.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Shiro Permission tags (Velocity)
 * 
 *
 *
 * @date 113 11:32:47
 */

public class VelocityShiro {

	/**
	 *
	 * @param permission
	 * @return   true：  false：
	 */
	public boolean hasPermission(String permission) {
		Subject subject = SecurityUtils.getSubject();
		return subject != null && subject.isPermitted(permission);
	}

}

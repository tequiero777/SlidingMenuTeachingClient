/**
 * Copyright (c) 2013 Tianjian, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * Tianjian, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Tianjian.
 */
package com.tianjian.slidingmenuteachingclient.bean;

/**
 * TODO
 * <p>
 * Title: MenuDict.java
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: Tianjian
 * </p>
 * <p>
 * team: TianjianTeam
 * </p>
 * <p>
 * 
 * @author: Puzhijun
 *          </p>
 * @date Aug 8, 201410:39:58 AM
 * @version 1.0
 * 
 */
public class MenuDictDetail {
	private String parentId;// 父级ID
	private Long endLevelFlag;// 是不是末级菜单
	private String menuLevel;// 菜单等级
	private String menuIcon;// 菜单图标
	private String menuTarget;// 菜单地址
	private String menuDetail;// 菜单名称
	private String menuUrl;
	
	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Long getEndLevelFlag() {
		return endLevelFlag;
	}

	public void setEndLevelFlag(Long endLevelFlag) {
		this.endLevelFlag = endLevelFlag;
	}

	public String getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getMenuTarget() {
		return menuTarget;
	}

	public void setMenuTarget(String menuTarget) {
		this.menuTarget = menuTarget;
	}

	public String getMenuDetail() {
		return menuDetail;
	}

	public void setMenuDetail(String menuDetail) {
		this.menuDetail = menuDetail;
	}

}

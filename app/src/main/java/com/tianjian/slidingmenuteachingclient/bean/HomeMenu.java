/**
 * Copyright (c) 2013 Tianjian, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * Tianjian, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Tianjian.
 */
package com.tianjian.slidingmenuteachingclient.bean;
/**
 * 单个菜单 
 * <p>
 * Title: HomeMenu.java
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
 * @author: cheng
 *          </p>
 * @date 2014-8-12下午3:39:22
 * @version 1.0
 * 
 */
public class HomeMenu {
	private String menuIcon;// 菜单图标
	private String menuTarget;// 菜单地址
	private String menuDetail;// 菜单名称
	private String menuId;
	private boolean check;
	private String firstLetter;
	
	
	
	
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}


	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
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

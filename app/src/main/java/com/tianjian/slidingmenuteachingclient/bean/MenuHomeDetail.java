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
 * Title: MenuHomeDetail.java
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
 * @date 2014-8-8下午3:48:22
 * @version 1.0
 * 
 */
public class MenuHomeDetail {
	private String menuDetail;
	private String menuIcon;
	private String menuTarget;
	private String menuId;
	private String menuUrl;
	
	
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuDetail() {
		return menuDetail;
	}
	public void setMenuDetail(String menuDetail) {
		this.menuDetail = menuDetail;
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
	@Override
	public String toString() {
		return "MenuHomeDetail [menuDetail=" + menuDetail + ", menuIcon="
				+ menuIcon + ", menuTarget=" + menuTarget + "]";
	}
	
	
}

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
 * Title: LocationBean.java
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
 * @date 2014年11月5日下午5:08:51
 * @version 1.0
 * 
 */
public class LocationBean {
	
	private double latitude;
	private double lontitude;
	private String addrName;
	private String city;
	
	
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLontitude() {
		return lontitude;
	}
	public void setLontitude(double lontitude) {
		this.lontitude = lontitude;
	}
	public String getAddrName() {
		return addrName;
	}
	public void setAddrName(String addrName) {
		this.addrName = addrName;
	}
	
	
}	

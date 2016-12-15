/**
 * Copyright (c) 2013 Tianjian, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * Tianjian, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Tianjian.
 */
package com.tianjian.slidingmenuteachingclient.bean;
/**
 * 基础的返回jsonStr样式 
 * <p>
 * Title: BaseJsonBean.java
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
 * @date 2015年1月19日上午9:32:20
 * @version 1.0
 * 
 */
public class BaseJsonBean {
	
	private String flag;
	private String message;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}

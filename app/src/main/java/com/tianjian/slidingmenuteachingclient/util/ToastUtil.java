/**
 * Copyright (c) 2013 Tianjian, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * Tianjian, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Tianjian.
 */
package com.tianjian.slidingmenuteachingclient.util;

import android.content.Context;
import android.widget.Toast;

/**
 * 全局只有一个toast的实例对象工具类
 * <p>
 * Title: ToastUtil.java
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
 * @date 2014-9-24下午4:30:38
 * @version 1.0
 * 
 */
public class ToastUtil{
	private static Toast toast;
	/**
	*Toast弹窗信息
	* @Title: showToast
	* @param mContext
	* @param string 显示的文字
	* @return void
	* @throws
	* @author cheng
	 */
	public static void showToast(Context mContext,String string){
		if(toast==null){
			toast = Toast.makeText(mContext, string, Toast.LENGTH_LONG);
		}else{
			toast.setText(string);
			toast.setDuration(Toast.LENGTH_LONG);
		}
		toast.show();
	}
}

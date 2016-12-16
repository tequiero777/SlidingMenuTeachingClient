/**
 * Copyright (c) 2013 Tianjian, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * Tianjian, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Tianjian.
 */
package com.tianjian.slidingmenuteachingclient.view;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

/**
 * 自定义popwindow
 * <p>Title: CustomerPopwindow.java</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Tianjian</p>
 * <p>team: TianjianTeam</p>
 * @author: Leipeijie
 * @date 2015年7月7日上午10:14:56
 * @version 1.0
 * 
 */
public class CustomerPopwindow extends PopupWindow{
	private int popbackColor;
	private View view;
	public CustomerPopwindow(int popbackColor,View view) {
		super();
		this.popbackColor = popbackColor;
		this.view = view;
		init();
	}
	
	private void init(){
		ColorDrawable colorDrawable = new ColorDrawable(popbackColor);
		setBackgroundDrawable(colorDrawable);
		setContentView(view);
		setHeight(LayoutParams.WRAP_CONTENT);
		setWidth(LayoutParams.WRAP_CONTENT);
	}
}

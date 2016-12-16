/**
 * Copyright (c) 2013 Tianjian, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * Tianjian, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Tianjian.
 */
package com.tianjian.slidingmenuteachingclient.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 自定义 患者信息卡的 view 为了 获取 每次点击的下标
 * <p>Title: PatientCardTextView.java</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Tianjian</p>
 * <p>team: TianjianTeam</p>
 * @author: Leipeijie
 * @date 2015年8月18日下午2:42:50
 * @version 1.0
 * 
 */
public class PatientCardTextView extends TextView{
	private int positien = 0;

	public PatientCardTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public PatientCardTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PatientCardTextView(Context context) {
		super(context);
	}

	public int getPositien() {
		return positien;
	}

	public void setPositien(int positien) {
		this.positien = positien;
	}

	
}

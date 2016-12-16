/**
 * Copyright (c) 2013 Tianjian, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * Tianjian, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Tianjian.
 */
package com.tianjian.slidingmenuteachingclient.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;


import com.tianjian.slidingmenuteachingclient.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * TODO
 * <p>Title: CustomerProgress.java</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Tianjian</p>
 * <p>team: TianjianTeam</p>
 * @author: Leipeijie
 * @date 2015年7月6日下午2:05:25
 * @version 1.0
 * 
 */
public class CustomerProgress extends Dialog{

	private ProgressWheel progressWheel;
	private Timer timer;
	
	public CustomerProgress(Context context, int theme) {
		super(context, theme);
		init();
	}

	protected CustomerProgress(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		init();
	}

	public CustomerProgress(Context context) {
		super(context);
		init();
	}

	/***
	 * 
	* 初始化
	* @Title: init
	* @return void
	* @throws
	* @author Leipeijie
	 */
	private void  init(){
		View  view  = LayoutInflater.from(getContext()).inflate(R.layout.custom_progress, null);
		progressWheel = (ProgressWheel) view.findViewById(R.id.customer_progressBar);
		this.setCancelable(false);
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				progressWheel.incrementProgress();
			}
		}, 0,10);
		setContentView(view);
	}
	
	public void  dismissDialog(CustomerProgress customerProgress){
		try {
			if(customerProgress!=null){
				customerProgress.dismiss();
				timer.cancel();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

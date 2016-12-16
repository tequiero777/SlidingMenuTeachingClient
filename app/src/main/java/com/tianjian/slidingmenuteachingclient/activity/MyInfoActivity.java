/**
 * Copyright (c) 2013 Tianjian, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * Tianjian, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Tianjian.
 */
package com.tianjian.slidingmenuteachingclient.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tianjian.slidingmenuteachingclient.R;
import com.tianjian.slidingmenuteachingclient.application.SystemApplcation;
import com.tianjian.slidingmenuteachingclient.bean.InLoginSrv.InLoginSrvOutputItem;


/**
 * 我的基本信息页面
 * <p>Title: MyInfoActivity.java</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Tianjian</p>
 * <p>team: TianjianTeam</p>
 * @author: Yehao
 * @date 2016年8月22日上午8:52:40
 * @version 1.0
 * 
 */
public class MyInfoActivity extends BaseActivity {
	private SystemApplcation applcation;
	private ImageButton button_back;
	private TextView text_name,text_sex,text_phone,text_hospital;
	private InLoginSrvOutputItem userDict;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myinfo_layout);
		
		applcation = (SystemApplcation)getApplication();
		applcation.addActivity(this);
		userDict = applcation.getUserDict();
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		
		initView();
	}

	private void initView() {
		//回退按钮
		button_back = (ImageButton) findViewById(R.id.myinfo_button_back);
		button_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		text_name = (TextView) findViewById(R.id.my_name_text);
		text_sex = (TextView) findViewById(R.id.my_sex_text);
		text_phone = (TextView) findViewById(R.id.my_phone_text);
		text_hospital = (TextView) findViewById(R.id.my_hospital_text);
		text_name.setText(userDict.getNAME());
		text_sex.setText(userDict.getSEX());
		text_hospital.setText(userDict.getHOSPITAL());
		text_phone.setText(userDict.getUSERNAME());
	}
}

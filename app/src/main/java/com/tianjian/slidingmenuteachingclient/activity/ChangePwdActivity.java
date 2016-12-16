/**
 * Copyright (c) 2013 Tianjian, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * Tianjian, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Tianjian.
 */
package com.tianjian.slidingmenuteachingclient.activity;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


import com.tianjian.slidingmenuteachingclient.R;
import com.tianjian.slidingmenuteachingclient.application.SystemApplcation;
import com.tianjian.slidingmenuteachingclient.bean.InLoginSrv.InLoginSrvOutputItem;
import com.tianjian.slidingmenuteachingclient.bean.InLoginSrv.InLoginSrvResponse;
import com.tianjian.slidingmenuteachingclient.util.ToastUtil;
import com.tianjian.slidingmenuteachingclient.util.network.callback.INetWorkCallBack;
import com.tianjian.slidingmenuteachingclient.util.network.helper.NetWorkHepler;
import com.tianjian.slidingmenuteachingclient.view.CustomerProgress;

import org.ksoap2.serialization.SoapObject;

import java.util.HashMap;

/**
 * 修改密码
 * <p>Title: ChangePwdActivity.java</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Tianjian</p>
 * <p>team: TianjianTeam</p>
 * @author: Yehao
 * @date 2016年8月22日上午9:23:37
 * @version 1.0
 * 
 */
public class ChangePwdActivity extends BaseActivity {
	private SystemApplcation applcation;
	private ImageButton button_back;
	private EditText oldpwd,newpwd,newpwd2;
	private Button save_button;
	private InLoginSrvOutputItem userDict;
	private SharedPreferences preferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.changepwd_layout);
		
		applcation = (SystemApplcation)getApplication();
		applcation.addActivity(this);
		userDict = applcation.getUserDict();
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		
		initView();
	}

	private void initView() {
		preferences = getSharedPreferences("TEACH", MODE_PRIVATE);
		//回退按钮
		button_back = (ImageButton) findViewById(R.id.changepwd_button_back);
		button_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		oldpwd = (EditText) findViewById(R.id.changepwd_oldpwd_text);
		newpwd = (EditText) findViewById(R.id.changepwd_newpwd_text);
		newpwd2 = (EditText) findViewById(R.id.changepwd_newpwd2_text);
		
		save_button = (Button) findViewById(R.id.changepwd_save_button);
		save_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!newpwd.getText().toString().equals(newpwd2.getText().toString())){
					ToastUtil.showToast(ChangePwdActivity.this, "两次输入的新密码不一致！");
					return;
				}else if(oldpwd.getText().toString().equals("") || newpwd.getText().toString().equals("")){
					ToastUtil.showToast(ChangePwdActivity.this, "密码不能为空！");
					return;
				}else{
					HashMap<String, Object> hashMap = new HashMap<String, Object>();
					hashMap.put("USERNAME", userDict.getUSERNAME());
					hashMap.put("PASSWORD", oldpwd.getText().toString()+"-"+newpwd.getText().toString());
					hashMap.put("OPERATE_TYPE", "2");
					queryChangePwd(hashMap);
				}
			}
		});
	}
	
	private void queryChangePwd(HashMap<String, Object> hashMap) {
		final CustomerProgress customerProgress =  new CustomerProgress(this,com.tianjian.slidingmenuteachingclient.R.style.customer_dialog);
		NetWorkHepler.postWsData("loginWs", "process", hashMap, new INetWorkCallBack() {
			SoapObject objectResult;
			@Override
			public void onResult(Object result) {
				customerProgress.dismissDialog(customerProgress);
				if(result == null){
					ToastUtil.showToast(getApplication(), "修改密码失败！");
				}else if(result instanceof SoapObject) {
					objectResult = (SoapObject) result;
				}else{
					ToastUtil.showToast(getApplication(), "服务器连接失败！");
					return;
				}
				InLoginSrvResponse response = new InLoginSrvResponse();
				try {
					for(int i=0;i<objectResult.getPropertyCount();i++){
						response.setProperty(i, objectResult.getProperty(i));
					}
				} catch (Exception e) {
					ToastUtil.showToast(getApplication(), "数据出错了，请重试！");
				}
				if("Y".equals(response.getErrorFlag())){
					ToastUtil.showToast(ChangePwdActivity.this, "修改密码成功！");
					Editor editor = preferences.edit();
					editor.putString("userPwd", newpwd.getText().toString());
					editor.commit();
					finish();
				}else if("原密码输入错误".equals(response.getErrorMessage())){
					ToastUtil.showToast(getApplication(), "原密码验证失败！");
				}else{
					ToastUtil.showToast(getApplication(), "修改密码失败！");
				}
				
			}
			
			@Override
			public void onProgressUpdate(Integer[] values) {
				
			}
			
			@Override
			public void onPreExecute() {
				customerProgress.show();
			}
		});
	}
}

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


import com.tianjian.slidingmenuteachingclient.R;
import com.tianjian.slidingmenuteachingclient.application.SystemApplcation;
import com.tianjian.slidingmenuteachingclient.bean.ImPortInformationSrv.ImPortInformationSrvResponse;
import com.tianjian.slidingmenuteachingclient.bean.InLoginSrv.InLoginSrvOutputItem;
import com.tianjian.slidingmenuteachingclient.bean.InTeachManagerSrv.InTeachManagerSrvResponse;
import com.tianjian.slidingmenuteachingclient.util.ToastUtil;
import com.tianjian.slidingmenuteachingclient.util.network.callback.INetWorkCallBack;
import com.tianjian.slidingmenuteachingclient.util.network.helper.NetWorkHepler;
import com.tianjian.slidingmenuteachingclient.view.CustomerProgress;

import org.ksoap2.serialization.SoapObject;

import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * TODO
 * <p>Title: ContactAdminActivity.java</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Tianjian</p>
 * <p>team: TianjianTeam</p>
 * @author: Yehao
 * @date 2016年11月8日下午3:45:17
 * @version 1.0
 * 
 */
public class ContactAdminActivity extends BaseActivity{
	private SystemApplcation applcation;
	private InLoginSrvOutputItem userDict;
	private ImageButton button_back;
	private EditText content_text;
	private TextView email_text;
	private Button button_save;
	private CustomerProgress customerProgress;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contactadmin_layout);
		
		applcation = (SystemApplcation)getApplication();
		applcation.addActivity(this);
		userDict = applcation.getUserDict();
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		
		//查询邮箱
		queryMail();
		
		initView();
	}

	private void queryMail() {
		HashMap<String, Object> hashMap_mail = new HashMap<String, Object>();
		hashMap_mail.put("OPERATE_TYPE", 1);
		NetWorkHepler.postWsData("inTeachManagerSrv", "process", hashMap_mail, new INetWorkCallBack() {
			SoapObject objectResult;
			@Override
			public void onResult(Object result) {
				if(result == null){
					ToastUtil.showToast(ContactAdminActivity.this, "保存数据失败！");
				}else if(result instanceof SoapObject) {
					objectResult = (SoapObject) result;
				}else{
					ToastUtil.showToast(ContactAdminActivity.this, "服务器连接失败！");
					return;
				}
				InTeachManagerSrvResponse response = new InTeachManagerSrvResponse();
				try {
					for(int i=0;i<objectResult.getPropertyCount();i++){
						response.setProperty(i, objectResult.getProperty(i));
					}
				} catch (Exception e) {
					ToastUtil.showToast(ContactAdminActivity.this, "数据出错了，请重试！");
				}
				if("Y".equals(response.getErrorFlag())){
					email_text = (TextView) findViewById(R.id.contactadmin_email);
					email_text.setText(response.getInTeachManagerSrvOutputCollection().getInTeachManagerSrvOutputItem().get(0).getMAIL());
				}else{
					ToastUtil.showToast(ContactAdminActivity.this, "获取数据失败！");
				}
			}
			
			@Override
			public void onProgressUpdate(Integer[] values) {
				
			}
			
			@Override
			public void onPreExecute() {
				
			}
		});
	
		
	}

	private void initView() {
		//回退按钮
		button_back = (ImageButton) findViewById(R.id.contactadmin_button_back);
		button_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		content_text = (EditText) findViewById(R.id.contactadmin_edittext);
		
		button_save = (Button) findViewById(R.id.contactadmin_save);
		button_save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!content_text.getText().toString().equals("")){
					customerProgress =  new CustomerProgress(ContactAdminActivity.this,com.tianjian.slidingmenuteachingclient.R.style.customer_dialog);
					customerProgress.show();
					HashMap<String, Object> hashMap = new HashMap<String, Object>();
					hashMap.put("USERNAME", userDict.getUSERNAME());
					hashMap.put("NAME", userDict.getNAME());
					hashMap.put("SEX", userDict.getSEX());
					hashMap.put("TYPE", userDict.getTYPE());
					hashMap.put("INFORMATION_TEXT", content_text.getText().toString());
					hashMap.put("HOSPITAL", userDict.getHOSPITAL());
					saveData(hashMap);
				}else{
					ToastUtil.showToast(ContactAdminActivity.this, "内容不能为空！");
				}
			}
		});
	}

	protected void saveData(HashMap<String, Object> hashMap) {
		NetWorkHepler.postWsData("imPortInformationSrv", "process", hashMap, new INetWorkCallBack() {
			SoapObject objectResult;
			@Override
			public void onResult(Object result) {
				customerProgress.dismissDialog(customerProgress);
				if(result == null){
					ToastUtil.showToast(ContactAdminActivity.this, "保存数据失败！");
				}else if(result instanceof SoapObject) {
					objectResult = (SoapObject) result;
				}else{
					ToastUtil.showToast(ContactAdminActivity.this, "服务器连接失败！");
					return;
				}
				ImPortInformationSrvResponse response = new ImPortInformationSrvResponse();
				try {
					for(int i=0;i<objectResult.getPropertyCount();i++){
						response.setProperty(i, objectResult.getProperty(i));
					}
				} catch (Exception e) {
					ToastUtil.showToast(ContactAdminActivity.this, "数据出错了，请重试！");
				}
				if("Y".equals(response.getErrorFlag())){
					ToastUtil.showToast(ContactAdminActivity.this, "保存成功！");
					setResult(1,  getIntent());
					finish();
				}else{
					ToastUtil.showToast(ContactAdminActivity.this, "获取数据失败！");
				}
			}
			
			@Override
			public void onProgressUpdate(Integer[] values) {
				
			}
			
			@Override
			public void onPreExecute() {
				
			}
		});
	}
}

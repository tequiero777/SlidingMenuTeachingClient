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
import android.widget.Button;
import android.widget.ListView;

import com.tianjian.slidingmenuteachingclient.R;
import com.tianjian.slidingmenuteachingclient.adapter.ChooseStuAdapter;
import com.tianjian.slidingmenuteachingclient.application.SystemApplcation;
import com.tianjian.slidingmenuteachingclient.bean.InLoginSrv.InLoginSrvOutputItem;
import com.tianjian.slidingmenuteachingclient.bean.InLoginSrv.InLoginSrvResponse;
import com.tianjian.slidingmenuteachingclient.util.ToastUtil;
import com.tianjian.slidingmenuteachingclient.util.network.callback.INetWorkCallBack;
import com.tianjian.slidingmenuteachingclient.util.network.helper.NetWorkHepler;
import com.tianjian.slidingmenuteachingclient.view.CustomerProgress;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * TODO
 * <p>Title: ChooseStuActivity.java</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Tianjian</p>
 * <p>team: TianjianTeam</p>
 * @author: Yehao
 * @date 2016年8月29日下午4:19:23
 * @version 1.0
 * 
 */
public class ChooseStuActivity extends BaseActivity{
	private SystemApplcation applcation;
	private InLoginSrvOutputItem userDict;
	private ListView listview;
	private ChooseStuAdapter adapter;
	private Button sure;
	private List<InLoginSrvOutputItem> list;
	private List<InLoginSrvOutputItem>  selected_templist = new ArrayList<InLoginSrvOutputItem>();
	private List<InLoginSrvOutputItem>  selected_templist_new = new ArrayList<InLoginSrvOutputItem>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choosestu_layout);
		
		applcation = (SystemApplcation)getApplication();
		applcation.addActivity(this);
		userDict = applcation.getUserDict();
		selected_templist = applcation.getSelected_templist();
		
		initView();
	}

	private void initView() {
		sure = (Button) findViewById(R.id.choosestu_save);
		sure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				selected_templist_new = new ArrayList<InLoginSrvOutputItem>();
				for(int i=0;i<adapter.mChecked.size();i++){  
                    if(adapter.mChecked.get(i)){  
                    	selected_templist_new.add(list.get(i));  
                    }  
                }
				applcation.setSelected_templist(selected_templist_new);
//				Gson gson = new Gson();
//				setResult(2,  getIntent().putExtra("list", gson.toJson(selected_templist)));
				setResult(2,  getIntent());
				finish();
			}
		});
		listview = (ListView) findViewById(R.id.choosestu_list);
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("USERNAME", userDict.getUSERNAME());
		hashMap.put("OPERATE_TYPE", "5");
		
		queryData(hashMap);
	}
	
	private void queryData(HashMap<String, Object> hashMap) {
		final CustomerProgress customerProgress =  new CustomerProgress(ChooseStuActivity.this,com.tianjian.slidingmenuteachingclient.R.style.customer_dialog);
		NetWorkHepler.postWsData("loginWs", "process", hashMap, new INetWorkCallBack() {
			SoapObject objectResult;
			@Override
			public void onResult(Object result) {
				customerProgress.dismissDialog(customerProgress);
				if(result == null){
					ToastUtil.showToast(ChooseStuActivity.this, "查询数据失败！");
				}else if(result instanceof SoapObject) {
					objectResult = (SoapObject) result;
				}else{
					ToastUtil.showToast(ChooseStuActivity.this, "服务器连接失败！");
					return;
				}
				InLoginSrvResponse response = new InLoginSrvResponse();
				try {
					for(int i=0;i<objectResult.getPropertyCount();i++){
						response.setProperty(i, objectResult.getProperty(i));
					}
				} catch (Exception e) {
					ToastUtil.showToast(ChooseStuActivity.this, "数据出错了，请重试！");
				}
				if(null!=response.getInLoginSrvOutputCollection() && null!=response.getInLoginSrvOutputCollection().getInLoginSrvOutputItem()){
					list = response.getInLoginSrvOutputCollection().getInLoginSrvOutputItem();
					if("Y".equals(response.getErrorFlag())){
						if(list.size()>0){
							initListView(list);
						}
					}else{
						ToastUtil.showToast(ChooseStuActivity.this, "获取数据失败！");
					}
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

	protected void initListView(List<InLoginSrvOutputItem> list) {
		if(adapter == null){
			adapter = new ChooseStuAdapter(list,selected_templist, ChooseStuActivity.this);
			listview.setAdapter(adapter);
		}else{
			adapter.setUsers(list);
			adapter.notifyDataSetChanged();
		}
		
	}


}

package com.tianjian.slidingmenuteachingclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.tianjian.slidingmenuteachingclient.R;
import com.tianjian.slidingmenuteachingclient.adapter.MyStudentOrMentorAdapter;
import com.tianjian.slidingmenuteachingclient.application.SystemApplcation;
import com.tianjian.slidingmenuteachingclient.bean.InLoginSrv.InLoginSrvOutputItem;
import com.tianjian.slidingmenuteachingclient.bean.InLoginSrv.InLoginSrvResponse;
import com.tianjian.slidingmenuteachingclient.util.ToastUtil;
import com.tianjian.slidingmenuteachingclient.util.network.callback.INetWorkCallBack;
import com.tianjian.slidingmenuteachingclient.util.network.helper.NetWorkHepler;
import com.tianjian.slidingmenuteachingclient.view.CustomerProgress;

import org.ksoap2.serialization.SoapObject;

import java.util.HashMap;
import java.util.List;

/**
 * TODO
 * <p>Title: MyStuOrMentorActivity.java</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Tianjian</p>
 * <p>team: TianjianTeam</p>
 * @author: Yehao
 * @date 2016年8月23日上午9:13:39
 * @version 1.0
 * 
 */
public class MyStuOrMentorActivity extends BaseActivity{
	private SystemApplcation applcation;
	private InLoginSrvOutputItem userDict;
	private ImageButton button_back;
	private TextView title;
	private ListView listview;
	private int usertype;
	private MyStudentOrMentorAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mystuormentor_layout);
		
		applcation = (SystemApplcation)getApplication();
		applcation.addActivity(this);
		userDict = applcation.getUserDict();
		Intent intent = getIntent();
		usertype = intent.getIntExtra("usertype", 0);
		
		initView();
	}

	private void initView() {
		//回退按钮
		button_back = (ImageButton) findViewById(R.id.mystuormentor_back);
		button_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		title = (TextView) findViewById(R.id.mystuormentor_title);
		if(usertype == 1){
			title.setText("我的导师");
		}else if(usertype == 2){
			title.setText("我的学生");
		}
		
		listview = (ListView) findViewById(R.id.mystuormentor_list);
		AnimationSet set = new AnimationSet(false);  
		Animation animation = new TranslateAnimation(-50f, 0f, 0f, 0f);
		animation.setDuration(500);
		set.addAnimation(animation);
		LayoutAnimationController controller = new LayoutAnimationController(set, 1);  
		listview.setLayoutAnimation(controller); 
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("USERNAME", userDict.getUSERNAME());
		if(userDict.getTYPE().equals("1")){
			hashMap.put("OPERATE_TYPE", "4");
		}else{
			hashMap.put("OPERATE_TYPE", "5");
		}
		
		queryData(hashMap);
	}
	
	private void queryData(HashMap<String, Object> hashMap) {
		final CustomerProgress customerProgress =  new CustomerProgress(MyStuOrMentorActivity.this,com.tianjian.slidingmenuteachingclient.R.style.customer_dialog);
		NetWorkHepler.postWsData("loginWs", "process", hashMap, new INetWorkCallBack() {
			SoapObject objectResult;
			@Override
			public void onResult(Object result) {
				customerProgress.dismissDialog(customerProgress);
				if(result == null){
					ToastUtil.showToast(MyStuOrMentorActivity.this, "查询数据失败！");
				}else if(result instanceof SoapObject) {
					objectResult = (SoapObject) result;
				}else{
					ToastUtil.showToast(MyStuOrMentorActivity.this, "服务器连接失败！");
					return;
				}
				InLoginSrvResponse response = new InLoginSrvResponse();
				try {
					for(int i=0;i<objectResult.getPropertyCount();i++){
						response.setProperty(i, objectResult.getProperty(i));
					}
				} catch (Exception e) {
					ToastUtil.showToast(MyStuOrMentorActivity.this, "数据出错了，请重试！");
				}
				if(null!=response.getInLoginSrvOutputCollection() && null!=response.getInLoginSrvOutputCollection().getInLoginSrvOutputItem()){
					List<InLoginSrvOutputItem> list = response.getInLoginSrvOutputCollection().getInLoginSrvOutputItem();
					if("Y".equals(response.getErrorFlag())){
						if(list.size()>0){
							initListView(list);
						}
					}else{
						ToastUtil.showToast(MyStuOrMentorActivity.this, "获取数据失败！");
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
			adapter = new MyStudentOrMentorAdapter(list, MyStuOrMentorActivity.this);
			listview.setAdapter(adapter);
		}else{
			adapter.setUsers(list);
			adapter.notifyDataSetChanged();
		}
		
	}
}

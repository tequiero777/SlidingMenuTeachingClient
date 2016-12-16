/**
 * Copyright (c) 2013 Tianjian, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * Tianjian, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Tianjian.
 */
package com.tianjian.slidingmenuteachingclient.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.tianjian.slidingmenuteachingclient.R;
import com.tianjian.slidingmenuteachingclient.activity.QuestionOverViewActivity;
import com.tianjian.slidingmenuteachingclient.adapter.QuestionListAdapter;
import com.tianjian.slidingmenuteachingclient.application.SystemApplcation;
import com.tianjian.slidingmenuteachingclient.bean.InLoginSrv.InLoginSrvOutputItem;
import com.tianjian.slidingmenuteachingclient.bean.InQueryQuestionSrv.InQueryQuestionSrvOutputCollection;
import com.tianjian.slidingmenuteachingclient.bean.InQueryQuestionSrv.InQueryQuestionSrvOutputItem;
import com.tianjian.slidingmenuteachingclient.bean.InQueryQuestionSrv.InQueryQuestionSrvResponse;
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
 * <p>Title: MyQuestionFragment.java</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Tianjian</p>
 * <p>team: TianjianTeam</p>
 * @author: Yehao
 * @date 2016年9月2日下午5:10:45
 * @version 1.0
 * 
 */
public class MyQuestionFragment extends BaseFragment{
	private View rootView;
	private RadioGroup radioGroup;
	private int radioButtonId = R.id.myquestion_radiogroup_replay;
	private PullToRefreshGridView listview;
	private QuestionListAdapter adapter;
	private SystemApplcation systemApplcation;
	private InLoginSrvOutputItem userDict;
	private CustomerProgress customerProgress;
	HashMap<String, Object> hashMap = new HashMap<String, Object>();
	List<InQueryQuestionSrvOutputItem> list = new ArrayList<InQueryQuestionSrvOutputItem>();
	List<InQueryQuestionSrvOutputItem> list_replay = new ArrayList<InQueryQuestionSrvOutputItem>();
	List<InQueryQuestionSrvOutputItem> list_noreplay = new ArrayList<InQueryQuestionSrvOutputItem>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(rootView == null){
			rootView = inflater.inflate(R.layout.myquestion_layout, null);
			systemApplcation = (SystemApplcation) getActivity().getApplication();
			userDict = systemApplcation.getUserDict();
			initView();	
		}
		systemApplcation = (SystemApplcation) getActivity().getApplication();
		userDict = systemApplcation.getUserDict();
		return rootView;
	}
	
	private void initView() {
		radioGroup = (RadioGroup) rootView.findViewById(R.id.myquestion_radiogroup);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				radioButtonId = group.getCheckedRadioButtonId();
                 //根据ID获取RadioButton的实例
				if(radioButtonId == R.id.myquestion_radiogroup_replay){
					initListView(list);
				}else{
					initListView(list);
				}
			}
		});
		
		listview = (PullToRefreshGridView) rootView.findViewById(R.id.myquestion_listview);
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Gson gson = new Gson();
				Intent intent = new Intent(getActivity(), QuestionOverViewActivity.class);
				if(radioButtonId == R.id.myquestion_radiogroup_replay){
//					intent.putExtra("list", gson.toJson(list_replay.get(position)));
					intent.putExtra("id", list_replay.get(position).getQUESTIONID());
				}else if(radioButtonId == R.id.myquestion_radiogroup_noreplay){
//					intent.putExtra("list", gson.toJson(list_noreplay.get(position)));
					intent.putExtra("id", list_noreplay.get(position).getQUESTIONID());
				}
				startActivity(intent);
			}
		});
		
		listview.setMode(Mode.PULL_DOWN_TO_REFRESH);
		listview.setOnRefreshListener(new OnRefreshListener2<GridView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
				queryData(hashMap);
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
//				currentPage = Integer.parseInt(hashMap.get("CURRENT_PAGE").toString());
//				++currentPage;
//				hashMap.remove("CURRENT_PAGE");
//				hashMap.put("CURRENT_PAGE", currentPage);
//				queryMoreData(hashMap);
				
			}
		});
		listview.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

			@Override
			public void onLastItemVisible() {
				
			}
		});
		initListViewTipText();
		
		hashMap.put("OPERATE_TYPE", "1");
		hashMap.put("USERNAME", userDict.getUSERNAME());
		queryData(hashMap);
	}
	
	private void initListViewTipText() {  
        // 设置上拉刷新文本  
    	com.handmark.pulltorefresh.library.ILoadingLayout  startLabels = listview.getLoadingLayoutProxy(true,  
                false);  
        startLabels.setPullLabel("下拉刷新...");  
        startLabels.setReleaseLabel("放开立即刷新...");  
        startLabels.setRefreshingLabel("正在刷新...");  
  
        // 设置下拉刷新文本  
        com.handmark.pulltorefresh.library.ILoadingLayout endLabels = listview.getLoadingLayoutProxy(false, true);  
        endLabels.setPullLabel("上拉加载更多...");  
        endLabels.setReleaseLabel("放开加载更多...");  
        endLabels.setRefreshingLabel("正在加载..."); 
     } 
	
	private void queryData(HashMap<String, Object> hashMap) {
		customerProgress =  new CustomerProgress(getActivity(),com.tianjian.slidingmenuteachingclient.R.style.customer_dialog);
		NetWorkHepler.postWsData("queryquestionWs", "process", hashMap, new INetWorkCallBack() {
			SoapObject objectResult;
			@Override
			public void onResult(Object result) {
				listview.onRefreshComplete();
				if(result == null){
					ToastUtil.showToast(getActivity(), "问题查询失败！");
				}else if(result instanceof SoapObject) {
					objectResult = (SoapObject) result;
				}else{
					ToastUtil.showToast(getActivity(), "服务器连接失败！");
					return;
				}
				InQueryQuestionSrvResponse response = new InQueryQuestionSrvResponse();
				try {
					for(int i=0;i<objectResult.getPropertyCount();i++){
						response.setProperty(i, objectResult.getProperty(i));
					}
				} catch (Exception e) {
					ToastUtil.showToast(getActivity(), "数据出错了，请重试！");
				}
				if("Y".equals(response.getErrorFlag()) && "查询成功".equals(response.getErrorMessage())){
					Gson gson = new Gson();
					InQueryQuestionSrvOutputCollection collection = gson.fromJson(response.getJSON(), new TypeToken<InQueryQuestionSrvOutputCollection>() {}.getType());
					list = collection.getInQueryQuestionSrvOutputItem();
					initListView(list);
				}else{
					ToastUtil.showToast(getActivity(), "暂无数据！");
					customerProgress.dismissDialog(customerProgress);
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
	
	protected void initListView(List<InQueryQuestionSrvOutputItem> list) {
		list_replay = new ArrayList<InQueryQuestionSrvOutputItem>();
		list_noreplay = new ArrayList<InQueryQuestionSrvOutputItem>();
		for (int i = 0; i < list.size(); i++) {
			if(null!=list.get(i).getANSWERSDETAILLINE()){
				if(list.get(i).getANSWERSDETAILLINE().getInQueryQuestionAnswersDetailSrvOutputItem().size()>0){
					list_replay.add(list.get(i));
				}else{
					list_noreplay.add(list.get(i));
				}
			}else{
				list_noreplay.add(list.get(i));
			}
		}
		
		if(radioButtonId == R.id.myquestion_radiogroup_replay){
			if(adapter == null){
				adapter = new QuestionListAdapter(list_replay, getActivity());
				listview.setAdapter(adapter);
			}else{
				adapter.setList(list_replay);
				adapter.notifyDataSetChanged();
			}
			customerProgress.dismissDialog(customerProgress);
		}else if(radioButtonId == R.id.myquestion_radiogroup_noreplay){
			if(adapter == null){
				adapter = new QuestionListAdapter(list_noreplay, getActivity());
				listview.setAdapter(adapter);
			}else{
				adapter.setList(list_noreplay);
				adapter.notifyDataSetChanged();
			}
			customerProgress.dismissDialog(customerProgress);
		}
	}
}

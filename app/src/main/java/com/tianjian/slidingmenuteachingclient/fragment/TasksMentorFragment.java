/**
 * Copyright (c) 2013 Tianjian, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * Tianjian, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Tianjian.
 */
package com.tianjian.slidingmenuteachingclient.fragment;

/**
 * 导师端任务界面
 * <p>Title: TasksMentorFragment.java</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Tianjian</p>
 * <p>team: TianjianTeam</p>
 * @author: Yehao
 * @date 2016年8月17日上午9:12:00
 * @version 1.0
 * 
 */
public class TasksMentorFragment extends BaseFragment{
	/*private View rootView;
	private Button send_button;
	private PullToRefreshGridView listview;
	private TasksMentorAdapter adapter;
	private SystemApplcation systemApplcation;
	private InLoginSrvOutputItem userDict;
	private static int REQUESTCODE = 1;
	HashMap<String, Object> hashMap = new HashMap<String, Object>();
	List<InQueryTaskSrvOutputItem> list = new ArrayList<InQueryTaskSrvOutputItem>();;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(rootView == null){
			rootView = inflater.inflate(R.layout.tasks_mentor_layout, null);
			systemApplcation = (SystemApplcation) getActivity().getApplication();
			userDict = systemApplcation.getUserDict();
			initView();	
		}
		return rootView;
	}
	
	private void initView() {
		//快速发任务
		send_button = (Button) rootView.findViewById(R.id.tasks_mentor_send);
		send_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				Intent intent = new Intent(getActivity(), SendTaskActivity.class);
//				startActivity(intent);	
				Intent intent =  new Intent();
	            intent.setClass(getActivity(),  SendTaskActivity.class);
	            startActivityForResult(intent,REQUESTCODE);
			}
		});
		
		listview = (PullToRefreshGridView) rootView.findViewById(R.id.tasks_mentor_list);
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Gson gson = new Gson();
				Intent intent = new Intent(getActivity(), TaskOverViewActivity.class);
				intent.putExtra("list", gson.toJson(list.get(position)));
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
		
		
		hashMap.put("OPERATE_TYPE", "3");
		hashMap.put("MENTOR_USERNAME", userDict.getUSERNAME());
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
		final CustomerProgress customerProgress =  new CustomerProgress(getActivity(),com.tianjian.teachingclient.R.style.customer_dialog);
		NetWorkHepler.postWsData("taskWs", "process", hashMap, new INetWorkCallBack() {
			SoapObject objectResult;
			@Override
			public void onResult(Object result) {
				listview.onRefreshComplete();
				customerProgress.dismissDialog(customerProgress);
				if(result == null){
					ToastUtil.showToast(getActivity(), "任务查询失败！");
				}else if(result instanceof SoapObject) {
					objectResult = (SoapObject) result;
				}else{
					ToastUtil.showToast(getActivity(), "服务器连接失败！");
					return;
				}
				InQueryTaskSrvResponse response = new InQueryTaskSrvResponse();
				try {
					for(int i=0;i<objectResult.getPropertyCount();i++){
						response.setProperty(i, objectResult.getProperty(i));
					}
				} catch (Exception e) {
					ToastUtil.showToast(getActivity(), "数据出错了，请重试！");
				}
				if("Y".equals(response.getErrorFlag()) && "查询成功".equals(response.getErrorMessage())){
					list = response.getInQueryTaskSrvOutputCollection().getInQueryTaskSrvOutputItem();
					initListView(list);
				}else{
					ToastUtil.showToast(getActivity(), "未查询到任务！");
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
	
	protected void initListView(List<InQueryTaskSrvOutputItem> list) {
		if(adapter == null){
			adapter = new TasksMentorAdapter(list, getActivity());
			listview.setAdapter(adapter);
		}else{
			adapter.setList(list);
			adapter.notifyDataSetChanged();
		}
		
	}
	
	@Override
	public  void onActivityResult(int requestCode, int resultCode, Intent data)  {
        super.onActivityResult(requestCode, resultCode,  data);
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("OPERATE_TYPE", "3");
		hashMap.put("MENTOR_USERNAME", userDict.getUSERNAME());
		queryData(hashMap);
	}*/
}

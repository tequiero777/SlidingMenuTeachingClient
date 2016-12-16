/**
 * Copyright (c) 2013 Tianjian, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * Tianjian, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Tianjian.
 */
package com.tianjian.slidingmenuteachingclient.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.tianjian.slidingmenuteachingclient.R;
import com.tianjian.slidingmenuteachingclient.bean.InQueryTasksSrv.InQueryTaskSrvOutputItem;
import com.tianjian.slidingmenuteachingclient.bean.InQueryTasksSrv.InQueryTaskSrvResponse;
import com.tianjian.slidingmenuteachingclient.util.ToastUtil;
import com.tianjian.slidingmenuteachingclient.util.network.callback.INetWorkCallBack;
import com.tianjian.slidingmenuteachingclient.util.network.helper.NetWorkHepler;
import com.tianjian.slidingmenuteachingclient.view.CustomerProgress;

import org.ksoap2.serialization.SoapObject;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 * <p>Title: TasksStuAdapter.java</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Tianjian</p>
 * <p>team: TianjianTeam</p>
 * @author: Yehao
 * @date 2016年8月26日上午9:37:59
 * @version 1.0
 * 
 */
public class TasksStuAdapter extends BaseAdapter{
	private List<InQueryTaskSrvOutputItem>  list = new LinkedList<InQueryTaskSrvOutputItem>();
	/**context**/
	private Context myContext;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	private AlertDialog.Builder alert ;
	
	public TasksStuAdapter(List<InQueryTaskSrvOutputItem>  list,
			Context myContext) {
		super();
		this.list = list;
		this.myContext = myContext;
	}

	@Override
	public int getCount() {
		return list ==null ? 0:list.size();
	}

	@Override
	public Object getItem(int position) {
		return list ==null ? null : list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getAdapterView(position, convertView, parent);
	}
	
	private View getAdapterView(int position, View convertView, ViewGroup parent){
		View  view = LayoutInflater.from(myContext).inflate(R.layout.tasks_stu_listitem, null);
			initInfos(position, view);
		return view;
		
	}
	
	private void initInfos(final int position, View view) {
		TextView text = (TextView) view.findViewById(R.id.task_stu_item_text); 
		TextView time = (TextView) view.findViewById(R.id.task_stu_item_time);
		final TextView button = (TextView) view.findViewById(R.id.task_stu_item_button); 
		
		text.setText(list.get(position).getCONTENT());
		time.setText(list.get(position).getTIME().replace(".0", ""));
		if(list.get(position).getSTATUS().equals("1")){
			button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					alert = new AlertDialog.Builder(myContext);
					alert.setCancelable(false);
		            alert.setTitle("确认")
		                 .setMessage("是否确认已收到任务？")
		                 .setPositiveButton("确定", new DialogInterface.OnClickListener() {
		                     public void onClick(DialogInterface dialog, int which) {
		                    	 HashMap<String, Object> hashMap = new HashMap<String, Object>();
		                 		 hashMap.put("OPERATE_TYPE", "1");
		                 		 hashMap.put("TASK_ID", list.get(position).getTASKID());
		                 		 hashMap.put("RECEIVE_USERNAME", list.get(position).getRECEIVEUSERNAME());
		                 		 queryData(hashMap);
		                     }

							private void queryData(HashMap<String, Object> hashMap) {
							final CustomerProgress customerProgress =  new CustomerProgress(myContext,com.tianjian.slidingmenuteachingclient.R.style.customer_dialog);
							NetWorkHepler.postWsData("operatetaskandquestionWs", "process", hashMap, new INetWorkCallBack() {
								SoapObject objectResult;
								@Override
								public void onResult(Object result) {
									customerProgress.dismissDialog(customerProgress);
									if(result == null){
										ToastUtil.showToast(myContext, "任务查询失败！");
									}else if(result instanceof SoapObject) {
										objectResult = (SoapObject) result;
									}else{
										ToastUtil.showToast(myContext, "服务器连接失败！");
										return;
									}
									InQueryTaskSrvResponse response = new InQueryTaskSrvResponse();
									try {
										for(int i=0;i<objectResult.getPropertyCount();i++){
											response.setProperty(i, objectResult.getProperty(i));
										}
									} catch (Exception e) {
										ToastUtil.showToast(myContext, "数据出错了，请重试！");
									}
									
									if("Y".equals(response.getErrorFlag())){
										 button.setText("已接收");
					         			 button.setTextColor(Color.parseColor("#3dc341"));
					         			 button.setBackground(null);
					         			 button.setClickable(false);
					         			 list.get(position).setSTATUS("2");
									}else{
										ToastUtil.showToast(myContext, "保存失败！");
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
		                 })
		                 .setNegativeButton("取消",new DialogInterface.OnClickListener(){
		                     public void onClick(DialogInterface dialog, int which) {
		                         dialog.dismiss();
		                     }
		                 });
		            
		            if(alert!=null)
		            	alert.create().show();
					}
			});
		}else if(list.get(position).getSTATUS().equals("2")){
			button.setText("已接收");
			button.setTextColor(Color.parseColor("#3dc341"));
			button.setBackground(null);
		}
		
	}

	
	public List<InQueryTaskSrvOutputItem> getList() {
		return list;
	}

	public void setList(List<InQueryTaskSrvOutputItem> list) {
		this.list = list;
	}

}

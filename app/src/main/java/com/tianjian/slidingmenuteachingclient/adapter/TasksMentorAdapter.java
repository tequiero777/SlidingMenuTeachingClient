/**
 * Copyright (c) 2013 Tianjian, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * Tianjian, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Tianjian.
 */
package com.tianjian.slidingmenuteachingclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.tianjian.slidingmenuteachingclient.R;
import com.tianjian.slidingmenuteachingclient.bean.InQueryTasksSrv.InQueryTaskSrvOutputItem;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 * <p>Title: TasksMentorAdapter.java</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Tianjian</p>
 * <p>team: TianjianTeam</p>
 * @author: Yehao
 * @date 2016年8月26日上午11:17:49
 * @version 1.0
 * 
 */
public class TasksMentorAdapter extends BaseAdapter{
	private List<InQueryTaskSrvOutputItem>  list = new LinkedList<InQueryTaskSrvOutputItem>();
	/**context**/
	private Context myContext;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	public TasksMentorAdapter(List<InQueryTaskSrvOutputItem>  list,
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
		View  view = LayoutInflater.from(myContext).inflate(R.layout.tasks_mentor_listitem, null);
			initInfos(position, view);
		return view;
		
	}
	
	private void initInfos(int position, View view) {
		TextView name = (TextView) view.findViewById(R.id.task_mentor_item_name); 
		TextView time = (TextView) view.findViewById(R.id.task_mentor_item_time);
		TextView text = (TextView) view.findViewById(R.id.task_mentor_item_text);
		TextView flag = (TextView) view.findViewById(R.id.task_mentor_item_flag);
		
		name.setText(list.get(position).getRECEIVENAME());
		text.setText(list.get(position).getCONTENT());
		time.setText(list.get(position).getTIME().replace(".0", ""));
		if(list.get(position).getSTATUS().equals("2")){
			flag.setBackgroundResource(R.drawable.button_green_short_normal);
			flag.setText("已接收");
		}
	}

	public List<InQueryTaskSrvOutputItem> getList() {
		return list;
	}

	public void setList(List<InQueryTaskSrvOutputItem> list) {
		this.list = list;
	}

}

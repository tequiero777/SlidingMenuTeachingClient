/**
 * Copyright (c) 2013 Tianjian, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * Tianjian, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Tianjian.
 */
package com.tianjian.slidingmenuteachingclient.adapter;

import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.tianjian.slidingmenuteachingclient.R;
import com.tianjian.slidingmenuteachingclient.bean.InQueryTasksSrv.InQueryTaskResourcesDetailSrvOutputItem;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 * <p>Title: TaskOverViewAdapter.java</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Tianjian</p>
 * <p>team: TianjianTeam</p>
 * @author: Yehao
 * @date 2016年8月31日下午5:00:26
 * @version 1.0
 * 
 */
public class TaskOverViewAdapter extends BaseAdapter{
	private List<InQueryTaskResourcesDetailSrvOutputItem>  list = new LinkedList<InQueryTaskResourcesDetailSrvOutputItem>();
	/**context**/
	private Context myContext;
	private String currentFileName="";
	private String currentFileType="";
	private String currentFileUrl="";
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	public TaskOverViewAdapter(List<InQueryTaskResourcesDetailSrvOutputItem>  list,
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
		View  view = LayoutInflater.from(myContext).inflate(R.layout.taskoverview_listitem, null);
			initInfos(position, view);
		return view;
		
	}
	
	private void initInfos(final int position, View view) {
		ImageView image = (ImageView) view.findViewById(R.id.taskoverview_listitem_image); 
		TextView name = (TextView) view.findViewById(R.id.taskoverview_listitem_name); 
		TextView button = (TextView) view.findViewById(R.id.taskoverview_listitem_button); 
		
		if(list.get(position).getRESOURCESTYPE().equals("1")){
			image.setBackgroundResource(R.drawable.mp4_icon);
		}else if(list.get(position).getRESOURCESTYPE().equals("2")){
			image.setBackgroundResource(R.drawable.word_icon);
		}else if(list.get(position).getRESOURCESTYPE().equals("3")){
			image.setBackgroundResource(R.drawable.ppt_icon);
		}else if(list.get(position).getRESOURCESTYPE().equals("4")){
			image.setBackgroundResource(R.drawable.pdf_icon);
		}else if(list.get(position).getRESOURCESTYPE().equals("5")){
			image.setBackgroundResource(R.drawable.pic_icon);
		}else if(list.get(position).getRESOURCESTYPE().equals("6")){
			image.setBackgroundResource(R.drawable.record_icon);
		}
		name.setText(list.get(position).getRESOURCESNAME());
		
		currentFileUrl = list.get(position).getRESOURCESURL();
		currentFileName = list.get(position).getRESOURCESNAME();
		currentFileType = list.get(position).getRESOURCESURL().substring(list.get(position).getRESOURCESURL().lastIndexOf("."),list.get(position).getRESOURCESURL().length()).toLowerCase();
		
		File file = new File(Environment.getExternalStorageDirectory()+"/分级诊疗下载文件/"+currentFileName+currentFileType);
        if(!file.exists()){
        	button.setText("下载");
        }else{
        	button.setText("查看");
        }
	}

	public List<InQueryTaskResourcesDetailSrvOutputItem> getList() {
		return list;
	}

	public void setList(List<InQueryTaskResourcesDetailSrvOutputItem> list) {
		this.list = list;
	}

}

/**
 * Copyright (c) 2013 Tianjian, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * Tianjian, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Tianjian.
 */
package com.tianjian.slidingmenuteachingclient.adapter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;


import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tianjian.slidingmenuteachingclient.R;
import com.tianjian.slidingmenuteachingclient.bean.InQueryResourcesSrv.InQueryResourcesSrvOutputItem;

/**
 * TODO
 * <p>Title: ResourcesAdapter.java</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Tianjian</p>
 * <p>team: TianjianTeam</p>
 * @author: Yehao
 * @date 2016年8月25日下午2:57:27
 * @version 1.0
 * 
 */
public class ResourcesAdapter extends BaseAdapter{
	private List<InQueryResourcesSrvOutputItem>  list = new LinkedList<InQueryResourcesSrvOutputItem>();
	/**context**/
	private Context myContext;
	private String currentFileName="";
	private String currentFileType="";
	private String currentFileUrl="";
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	public ResourcesAdapter(List<InQueryResourcesSrvOutputItem>  list,
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
		View  view = LayoutInflater.from(myContext).inflate(R.layout.resources_listitem, null);
			initInfos(position, view);
		return view;
		
	}
	
	private void initInfos(int position, View view) {
		ImageView image = (ImageView) view.findViewById(R.id.resources_item_image); 
		TextView name = (TextView) view.findViewById(R.id.resources_item_name); 
		TextView time = (TextView) view.findViewById(R.id.resources_item_time);
		TextView button = (TextView) view.findViewById(R.id.resources_item_button); 
		
		if(list.get(position).getRESOURCESTYPE().equals("1")){
			image.setBackgroundResource(R.drawable.mp4_icon);
		}else if(list.get(position).getRESOURCESTYPE().equals("2")){
			image.setBackgroundResource(R.drawable.word_icon);
		}else if(list.get(position).getRESOURCESTYPE().equals("3")){
			image.setBackgroundResource(R.drawable.ppt_icon);
		}else if(list.get(position).getRESOURCESTYPE().equals("4")){
			image.setBackgroundResource(R.drawable.pdf_icon);
		}
		name.setText(list.get(position).getRESOURCESNAME());
		time.setText(list.get(position).getUPLOADTIME());
		
		currentFileUrl = list.get(position).getRESOURCESURL();
		currentFileName = list.get(position).getRESOURCESNAME();
		if(!currentFileUrl.equals("") && currentFileUrl.contains(".")){
			currentFileType = list.get(position).getRESOURCESURL().substring(list.get(position).getRESOURCESURL().lastIndexOf("."),list.get(position).getRESOURCESURL().length()).toLowerCase();

		}
		
		File file = new File(Environment.getExternalStorageDirectory()+"/分级诊疗下载文件/"+currentFileName+currentFileType);
        if(!file.exists()){
        	button.setText("下载");
        }else{
        	button.setText("查看");
        }
	}

	public List<InQueryResourcesSrvOutputItem> getList() {
		return list;
	}

	public void setList(List<InQueryResourcesSrvOutputItem> list) {
		this.list = list;
	}

}

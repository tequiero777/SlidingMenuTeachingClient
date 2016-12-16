/**
 * Copyright (c) 2013 Tianjian, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * Tianjian, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Tianjian.
 */
package com.tianjian.slidingmenuteachingclient.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.tianjian.slidingmenuteachingclient.R;
import com.tianjian.slidingmenuteachingclient.bean.InLoginSrv.InLoginSrvOutputItem;

import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 * <p>Title: MyStudentOrMentorAdapter.java</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Tianjian</p>
 * <p>team: TianjianTeam</p>
 * @author: Yehao
 * @date 2016年8月23日上午9:04:38
 * @version 1.0
 * 
 */
public class MyStudentOrMentorAdapter extends BaseAdapter{
	private List<InLoginSrvOutputItem>  users = new LinkedList<InLoginSrvOutputItem>();
	/**context**/
	private Context myContext;
	public MyStudentOrMentorAdapter(List<InLoginSrvOutputItem>  users,
									Context myContext) {
		super();
		this.users = users;
		this.myContext = myContext;
	}

	@Override
	public int getCount() {
		return users ==null ? 0:users.size();
	}

	@Override
	public Object getItem(int position) {
		return users ==null ? null : users.get(position);
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
		View  view = LayoutInflater.from(myContext).inflate(R.layout.mystuormentor_listitem, null);
			initInfos(position, view);
		return view;
		
	}
	
	private void initInfos(int position, View view) {
		ImageView image = (ImageView) view.findViewById(R.id.mystuormentor_item_image); 
		TextView name = (TextView) view.findViewById(R.id.mystuormentor_item_name); 
		TextView sex = (TextView) view.findViewById(R.id.mystuormentor_item_sex);
		TextView hospital = (TextView) view.findViewById(R.id.mystuormentor_item_hospital); 
		TextView phone = (TextView) view.findViewById(R.id.mystuormentor_item_phone); 
		
		if(null!=users.get(position).getIMAGE()){
			image.setImageBitmap(BitmapFactory.decodeByteArray(users.get(position).getIMAGE(), 0, users.get(position).getIMAGE().length));
		}
		name.setText(users.get(position).getNAME());
		sex.setText(users.get(position).getSEX());
		hospital.setText(users.get(position).getHOSPITAL());
		phone.setText(users.get(position).getUSERNAME());
		
		Drawable leftDrawable = view.getResources().getDrawable(R.drawable.phone_icon);
	    leftDrawable.setBounds(0, 0, 40, 40);
	    phone.setCompoundDrawables(leftDrawable, null, null, null);
		
	}

	public List<InLoginSrvOutputItem> getUsers() {
		return users;
	}

	public void setUsers(List<InLoginSrvOutputItem> users) {
		this.users = users;
	}

}

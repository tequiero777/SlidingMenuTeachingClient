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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.tianjian.slidingmenuteachingclient.R;
import com.tianjian.slidingmenuteachingclient.bean.InLoginSrv.InLoginSrvOutputItem;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 * <p>Title: ChooseStuAdapter.java</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Tianjian</p>
 * <p>team: TianjianTeam</p>
 * @author: Yehao
 * @date 2016年8月29日下午4:24:27
 * @version 1.0
 * 
 */
public class ChooseStuAdapter extends BaseAdapter{
	private List<InLoginSrvOutputItem>  users = new LinkedList<InLoginSrvOutputItem>();
	/**context**/
	private Context myContext;
	public List<Boolean> mChecked;
	List<InLoginSrvOutputItem> selected_templist;
	public ChooseStuAdapter(List<InLoginSrvOutputItem>  users, List<InLoginSrvOutputItem> selected_templist, Context myContext) {
		super();
		this.users = users;
		this.myContext = myContext;
		this.selected_templist = selected_templist;
		mChecked = new ArrayList<Boolean>();  
        for(int i=0;i<users.size();i++){  
            mChecked.add(false);  
        }
        if(selected_templist!=null){
        	for (int i = 0; i < users.size(); i++) {
    			for (int j = 0; j < selected_templist.size(); j++) {
    				if(users.get(i).getUSERNAME().equals(selected_templist.get(j).getUSERNAME())){
    					mChecked.set(i, true);
    				}
    			}
    		}
        }
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
		View  view = LayoutInflater.from(myContext).inflate(R.layout.choosestu_listitem, null);
			initInfos(position, view);
		return view;
		
	}
	
	private void initInfos(final int position, View view) {
		ImageView image = (ImageView) view.findViewById(R.id.choosestu_item_image); 
		TextView name = (TextView) view.findViewById(R.id.choosestu_item_name); 
		TextView sex = (TextView) view.findViewById(R.id.choosestu_item_sex);
		TextView hospital = (TextView) view.findViewById(R.id.choosestu_item_hospital); 
		TextView phone = (TextView) view.findViewById(R.id.choosestu_item_phone); 
		CheckBox check = (CheckBox) view.findViewById(R.id.choosestu_item_checkbox); 
		
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
		
	    if(selected_templist!=null){
	    	for (int i = 0; i < selected_templist.size(); i++) {
				if(selected_templist.get(i).getUSERNAME().equals(users.get(position).getUSERNAME())){
					check.setChecked(true);
				}
			}
	    }
	    
	    check.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				CheckBox cb = (CheckBox)v;  
                mChecked.set(position, cb.isChecked());
			}
		});
	}

	public List<InLoginSrvOutputItem> getUsers() {
		return users;
	}

	public void setUsers(List<InLoginSrvOutputItem> users) {
		this.users = users;
	}

}

package com.tianjian.slidingmenuteachingclient.activity;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tianjian.slidingmenuteachingclient.application.SystemApplcation;


public class BaseActivity extends AppCompatActivity {
	protected SystemApplcation systemApplcation;
	protected ImageButton backImg;
	protected TextView title;//标题
	protected Class<?> toActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		systemApplcation = (SystemApplcation) getApplication();
		systemApplcation.addActivity(this);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
	
	

	public void setActivity(Class<?> toActivity){
		this.toActivity =  toActivity;
	}
	
	public Class<?> getActivity(){
		return this.toActivity;
	}
	
}

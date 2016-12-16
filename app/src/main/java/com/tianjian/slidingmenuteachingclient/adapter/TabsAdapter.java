package com.tianjian.slidingmenuteachingclient.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class TabsAdapter extends FragmentPagerAdapter {
	private List<Fragment> fragmentList;
	
	public TabsAdapter(FragmentManager fm, List<Fragment> fragmentList) {
		super(fm);
		this.fragmentList = fragmentList;
	}	
	
	@Override            
	public int getCount() {                
		return fragmentList == null ? 0 : fragmentList.size();           
	} 

	@Override            
	public Fragment getItem(int index)//直接创建fragment对象并返回
	{                
		return (fragmentList == null || fragmentList.size() == 0) ? null : fragmentList.get(index);           
	}

	

}

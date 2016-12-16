/**
 * Copyright (c) 2013 Tianjian, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * Tianjian, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Tianjian.
 */
package com.tianjian.slidingmenuteachingclient.fragment;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.tianjian.slidingmenuteachingclient.R;
import com.tianjian.slidingmenuteachingclient.adapter.TabsAdapter;
import com.tianjian.slidingmenuteachingclient.bean.ClassBean;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 学生端提问
 * <p>Title: QuestionsFragmentStudent.java</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Tianjian</p>
 * <p>team: TianjianTeam</p>
 * @author: Yehao
 * @date 2016年8月17日上午9:04:17
 * @version 1.0
 * 
 */
public class QuestionsStudentFragment extends BaseFragment{
	private LinkedList<RadioButton> tabRadioButtons = new LinkedList<RadioButton>();
	private ViewPager vPager = null;
	/**     * 选项卡总数     */
	private static int tab_count = 2;    
	/**     * 当前显示的选项卡位置     */
	private int current_index = 0;        
	/**     * 选项卡标题     */
	private List<ClassBean> tabsList = new ArrayList<ClassBean>();
	private TabsAdapter hta;
	private LinearLayout titleListview;
	
	private View rootView;
	
	private List<Fragment> fragmentList = null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		if(rootView == null){
			rootView = inflater.inflate(R.layout.questions_student_layout, null);
			vPager = (ViewPager) rootView.findViewById(R.id.vPager);
			titleListview = (LinearLayout) rootView.findViewById(R.id.question_stu_tab_title);	      
			getScreenSize();
			fragmentList = new ArrayList<Fragment>();
			initView();
			
			vPager.setOnPageChangeListener(new OnPageChangeListener() {
				
				@Override            
				public void onPageSelected(int index) {   
					
					//之前选中的组件
					RadioButton beforeCheckedTextView = (RadioButton) titleListview.findViewById(current_index);
					beforeCheckedTextView.setTextColor(Color.GRAY);
					
					//当前选中的组件
					RadioButton nowCheckedTextView = (RadioButton) titleListview.findViewById(index);
					nowCheckedTextView.setTextColor(Color.parseColor("#019dd9"));
					
					current_index = index; 
					for(int j =0;j<tabRadioButtons.size();j++){
		        		if(index==j){
		        			tabRadioButtons.get(index).setChecked(true);
		        			continue;
		        		}else{
		        			tabRadioButtons.get(j).setChecked(false);
		        		}
		        	}
					           
				}                        
				
				@Override            
				public void onPageScrolled(int arg0, float arg1, int arg2) {
					
				}                        
				
				@Override            
				public void onPageScrollStateChanged(int index) {            
					
				}        
			});
			
		}
		return rootView;
	}
	
	private void initView() {
		ClassBean cb1 = new ClassBean();
		cb1.setClassName("我的提问");
		ClassBean cb2 = new ClassBean();
		cb2.setClassName("公开提问");
		tabsList.add(cb1);
		tabsList.add(cb2);
		
		MyQuestionFragment viewPagerFragment1 = new MyQuestionFragment();		
		AllOpenQuestionFragment viewPagerFragment2 = new AllOpenQuestionFragment();	
        fragmentList.add(viewPagerFragment1); 
        fragmentList.add(viewPagerFragment2); 
		
		for (int i = 0; i < tab_count; i++) {
			final LinearLayout layout = new LinearLayout(getActivity());
			layout.setOrientation(LinearLayout.HORIZONTAL);
			DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
		    int px = Math.round(45 * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)); 
			layout.setLayoutParams(new LinearLayout.LayoutParams(screenSize.getWidth()/tab_count, px));
			layout.setGravity(Gravity.CENTER);
			RadioButton tv = (RadioButton) LayoutInflater.from(getActivity()).inflate(R.layout.custom_radiobutton, null);
			tv.setText(tabsList.get(i).getClassName());
			tv.setTextSize(16);
			tv.setId(i);
			tv.setHeight(px);
			tv.setWidth(screenSize.getWidth()/4);
			tv.setGravity(Gravity.CENTER);
			tabRadioButtons.addLast(tv);
			if(i==0){
				Resources resource = (Resources) getActivity().getBaseContext().getResources();
				ColorStateList csl = (ColorStateList) resource.getColorStateList(R.color.class_title_color_sel);
				
				tv.setTextColor(csl);
				tv.setChecked(true);
			}
			tv.setOnClickListener(new MyOnClickListener(i));
			
			layout.addView(tv);
			titleListview.addView(layout);
		}
		
		hta = new TabsAdapter(getChildFragmentManager(), fragmentList);	
		vPager.setAdapter(hta);
	}    
	
	/**
     * 头标点击监听
     */
    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
        	RadioButton button = (RadioButton) v;
        	button.setChecked(true);
        	for(int j =0;j<tabRadioButtons.size();j++){
        		if(index==j){
        			continue;
        		}else{
        			tabRadioButtons.get(j).setChecked(false);
        		}
        	}
            vPager.setCurrentItem(index);
        }
    };
}

package com.tianjian.slidingmenuteachingclient.application;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.os.Vibrator;
import android.widget.TextView;


import com.tianjian.slidingmenuteachingclient.bean.InLoginSrv.InLoginSrvOutputItem;
import com.tianjian.slidingmenuteachingclient.bean.LocationBean;
import com.tianjian.slidingmenuteachingclient.fragment.HomePageMentorFragment;
import com.tianjian.slidingmenuteachingclient.fragment.HomePageStudentFragment;
import com.tianjian.slidingmenuteachingclient.fragment.QuestionsMentorFragment;
import com.tianjian.slidingmenuteachingclient.fragment.QuestionsStudentFragment;
import com.tianjian.slidingmenuteachingclient.fragment.ResourcesFragment;
import com.tianjian.slidingmenuteachingclient.fragment.TasksMentorFragment;
import com.tianjian.slidingmenuteachingclient.fragment.TasksStudentFragment;
import com.tianjian.slidingmenuteachingclient.fragment.UserInfoMentorFragment;
import com.tianjian.slidingmenuteachingclient.fragment.UserInfoStudentFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SystemApplcation extends Application {
	private List<Activity> activityList = new LinkedList<Activity>();

	private Boolean isDownload;
	
	/**是否登陆的标志*/
	private boolean islogin = false;
	//public GeofenceClient mGeofenceClient;
	
	public TextView mLocationResult,logMsg;
	public TextView trigger,exit;
	public Vibrator mVibrator;
	public LocationBean locationBean;
	private NewMsgCountCallBack callBack;
	/**公钥或者私钥*/
	private String clientKey = "";
	/**随机数*/
	private String radomNum = "";
	/**授权码*/
	private String code ="";
	/**已选择学生*/
	private List<InLoginSrvOutputItem>  selected_templist = new ArrayList<InLoginSrvOutputItem>();
	
	
	public void setCallBack(NewMsgCountCallBack callBack) {
		this.callBack = callBack;
	}
	
	/**登陆人员信息*/
	private InLoginSrvOutputItem userDict;
	
	private HomePageMentorFragment homePageMentorFragment;
	private HomePageStudentFragment homePageStudentFragment;
	private QuestionsMentorFragment questionMentorFragment;
	private QuestionsStudentFragment questionStudentFragment;
	private ResourcesFragment resourcesFragment;
	private TasksMentorFragment tasksMentorFragment;
	private TasksStudentFragment tasksStudentFragment;
	private UserInfoMentorFragment userInfoMentorFragment;
	private UserInfoStudentFragment userInfoStudentFragment;
	
	/**
	 * 
	*通知主线程信息数量变化
	* @Title: notifyMainActivity
	* @return void
	* @throws
	* @author cheng
	 */
	public void notifyMainActivity(){
		if(callBack!=null)
			callBack.callBack();
	}
	private Map<String, Object> map = new HashMap<String, Object>();

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	// 添加Activity到容器中
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	// 遍历所有Activity并finish
	public void exit() {
		for (Activity activity : activityList) {
			activity.finish();
		}
		
	}


	/**
	 * 判断当前版本是否兼容目标版本的方法
	 * 
	 * @param VersionCode
	 * @return
	 */
	public static boolean isMethodsCompat(int VersionCode) {
		int currentVersion = android.os.Build.VERSION.SDK_INT;
		return currentVersion >= VersionCode;
	}
	
	
	

	@Override
	public void onCreate() {
		super.onCreate();
		
		
		isDownload = true;
		// 注册全局异常处理器 
		CrashHandler crashHandler = CrashHandler.getInstance();  
        // 注册crashHandler  
        crashHandler.init(getApplicationContext());  
		//mGeofenceClient = new GeofenceClient(getApplicationContext());
		if(locationBean==null){
			locationBean = new LocationBean();
		}
		
		
		mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        // 发送以前没发送的报告(可选)  
//        crashHandler.sendPreviousReportsToServer();  
	}
	
	public boolean isDownload() {
		return isDownload;
	}

	public void setDownload(boolean isDownload) {
		this.isDownload = isDownload;
	}

	
	
	public interface NewMsgCountCallBack{
		void callBack();
	}



	public String getClientKey() {
		return clientKey;
	}
	public void setClientKey(String clientKey) {
		this.clientKey = clientKey;
	}
	public String getRadomNum() {
		return radomNum;
	}
	public void setRadomNum(String radomNum) {
		this.radomNum = radomNum;
	}
	public boolean isIslogin() {
		return islogin;
	}
	public void setIslogin(boolean islogin) {
		this.islogin = islogin;
	}
	
	/***
	 * 
	* 授权码
	* @Title: getCode
	* @return
	* @return String
	* @throws
	* @author Leipeijie
	 */
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public InLoginSrvOutputItem getUserDict() {
		return userDict;
	}

	public void setUserDict(InLoginSrvOutputItem userDict) {
		this.userDict = userDict;
	}

	public List<InLoginSrvOutputItem> getSelected_templist() {
		return selected_templist;
	}

	public void setSelected_templist(List<InLoginSrvOutputItem> selected_templist) {
		this.selected_templist = selected_templist;
	}

	public HomePageMentorFragment getHomePageMentorFragment() {
		return homePageMentorFragment;
	}

	public void setHomePageMentorFragment(
			HomePageMentorFragment homePageMentorFragment) {
		this.homePageMentorFragment = homePageMentorFragment;
	}

	public HomePageStudentFragment getHomePageStudentFragment() {
		return homePageStudentFragment;
	}

	public void setHomePageStudentFragment(
			HomePageStudentFragment homePageStudentFragment) {
		this.homePageStudentFragment = homePageStudentFragment;
	}

	public QuestionsMentorFragment getQuestionMentorFragment() {
		return questionMentorFragment;
	}

	public void setQuestionMentorFragment(
			QuestionsMentorFragment questionMentorFragment) {
		this.questionMentorFragment = questionMentorFragment;
	}

	public QuestionsStudentFragment getQuestionStudentFragment() {
		return questionStudentFragment;
	}

	public void setQuestionStudentFragment(
			QuestionsStudentFragment questionStudentFragment) {
		this.questionStudentFragment = questionStudentFragment;
	}

	public ResourcesFragment getResourcesFragment() {
		return resourcesFragment;
	}

	public void setResourcesFragment(ResourcesFragment resourcesFragment) {
		this.resourcesFragment = resourcesFragment;
	}

	public TasksMentorFragment getTasksMentorFragment() {
		return tasksMentorFragment;
	}

	public void setTasksMentorFragment(TasksMentorFragment tasksMentorFragment) {
		this.tasksMentorFragment = tasksMentorFragment;
	}

	public TasksStudentFragment getTasksStudentFragment() {
		return tasksStudentFragment;
	}

	public void setTasksStudentFragment(TasksStudentFragment tasksStudentFragment) {
		this.tasksStudentFragment = tasksStudentFragment;
	}

	public UserInfoMentorFragment getUserInfoMentorFragment() {
		return userInfoMentorFragment;
	}

	public void setUserInfoMentorFragment(
			UserInfoMentorFragment userInfoMentorFragment) {
		this.userInfoMentorFragment = userInfoMentorFragment;
	}

	public UserInfoStudentFragment getUserInfoStudentFragment() {
		return userInfoStudentFragment;
	}

	public void setUserInfoStudentFragment(
			UserInfoStudentFragment userInfoStudentFragment) {
		this.userInfoStudentFragment = userInfoStudentFragment;
	}



	
}

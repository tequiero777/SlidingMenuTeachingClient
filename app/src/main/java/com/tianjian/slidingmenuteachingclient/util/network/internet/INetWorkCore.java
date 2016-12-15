package com.tianjian.slidingmenuteachingclient.util.network.internet;

import android.app.Activity;


import com.tianjian.slidingmenuteachingclient.bean.FormFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface INetWorkCore  {
	
	public String postDataBySsl(String url, String jsonStr, String authorization, String mehodName);
	
	public String postDataFormBySsl(String url, Map<String, Object> params, String authorization);
	
	public String postDataOneSideSsl(String url, String methodName, String jsonAttr, Activity baseActivity);
	
	
	public String postFile(String path, Map<String, String> params, List<FormFile> fileByte) throws Exception;
	
	

	public Object postWsData(String adrressName, String methodName, HashMap<String, Object> request);
	
	
}

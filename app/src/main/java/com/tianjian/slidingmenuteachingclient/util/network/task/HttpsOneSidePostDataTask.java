package com.tianjian.slidingmenuteachingclient.util.network.task;

import android.app.Activity;
import android.os.AsyncTask;

import com.tianjian.slidingmenuteachingclient.util.network.callback.INetWorkCallBack;
import com.tianjian.slidingmenuteachingclient.util.network.internet.INetWorkCore;
import com.tianjian.slidingmenuteachingclient.util.network.internet.impl.NetWorkCoreImpl;


public class HttpsOneSidePostDataTask extends AsyncTask<Void, Integer, String> {
	
	private INetWorkCallBack callBack;
	
	private String url;
	
	private String jsonStr;
	
	private String methodName;
	
	private INetWorkCore netWorkCore;

	private Activity mainActivity;
	
	/**
	 * 
	* <p>Title: </p>
	* @param callBack
	* @param url 请求地址
	* @param jsonStr 请求内容
	* @param methodName 方法名
	* @param mainActivity  
	 */
	public HttpsOneSidePostDataTask(INetWorkCallBack callBack, String url,
			String jsonStr,String methodName,Activity mainActivity) {
		super();
		this.callBack = callBack;
		this.url = url;
		this.jsonStr = jsonStr;
		this.methodName = methodName;
		this.mainActivity = mainActivity;
	}

	private INetWorkCore getNetWorkByForm(){
		if(netWorkCore==null){
			this.netWorkCore = new NetWorkCoreImpl();
		}
		return netWorkCore;
	}


	@Override
	protected String doInBackground(Void... params) {
		INetWorkCore nw = getNetWorkByForm();
		String rs = nw.postDataOneSideSsl(url, methodName, jsonStr, mainActivity);
		return rs;
	}

	@Override
	protected void onPreExecute() {
		callBack.onPreExecute();
	}

	
	@Override
	protected void onPostExecute(String result) {
		callBack.onResult(result);
		publishProgress();
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		callBack.onProgressUpdate(values);
	}
}

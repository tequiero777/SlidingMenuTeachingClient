package com.tianjian.slidingmenuteachingclient.util.network.task;

import android.os.AsyncTask;


import com.tianjian.slidingmenuteachingclient.util.network.callback.INetWorkCallBack;
import com.tianjian.slidingmenuteachingclient.util.network.internet.INetWorkCore;
import com.tianjian.slidingmenuteachingclient.util.network.internet.impl.NetWorkCoreImpl;

import java.util.HashMap;

public class HttpWsTask extends AsyncTask<Void, Void, Object> {
	
	private String methodName;
	private HashMap<String, Object> request;
	private String adrressName;
	private INetWorkCallBack callback;
	private INetWorkCore netWorkCore;

	public HttpWsTask(String adrressName,String methodName, HashMap<String, Object> request,
			 INetWorkCallBack callback) {
		super();
		this.request = request;
		this.adrressName = adrressName;
		this.callback = callback;
		this.methodName = methodName;
	}


	private INetWorkCore getNetWorkByForm(){
		if(netWorkCore==null){
			this.netWorkCore = new NetWorkCoreImpl();
		}
		return netWorkCore;
	}


	@Override
	protected Object doInBackground(Void... params) {
		INetWorkCore nw = getNetWorkByForm();
		Object rs = nw.postWsData(adrressName,methodName, request);
		return rs;
	}

	@Override
	protected void onPreExecute() {
		callback.onPreExecute();
	}

	@Override
	protected void onPostExecute(Object result) {
		callback.onResult(result);
	}

}

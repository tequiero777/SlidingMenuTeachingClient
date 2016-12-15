package com.tianjian.slidingmenuteachingclient.util.network.callback;

public abstract class INetWorkCallBack {
	/**
	 * 加载之前处理的方法
	 */
	public abstract void onPreExecute();
	
	public abstract void onResult(Object result);

	/***
	 * 进度
	 * @param values
	 */
	public abstract void onProgressUpdate(Integer[] values);
	
	
	
}

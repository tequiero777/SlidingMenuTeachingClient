package com.tianjian.slidingmenuteachingclient.upload;

public interface MsgCallBack {
	/**
	 * 加载之前处理的方法
	 */
	public abstract void onPreExecute();
	
	public abstract void onResult(String result);

	/***
	 * 进度
	 * @param values
	 */
	public abstract void onProgressUpdate(Integer[] values);
	
	
}

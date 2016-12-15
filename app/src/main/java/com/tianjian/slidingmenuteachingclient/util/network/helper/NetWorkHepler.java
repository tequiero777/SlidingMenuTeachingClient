package com.tianjian.slidingmenuteachingclient.util.network.helper;

import android.app.Activity;
import android.widget.ImageView;

import com.tianjian.slidingmenuteachingclient.bean.FormFile;
import com.tianjian.slidingmenuteachingclient.util.network.callback.INetWorkCallBack;
import com.tianjian.slidingmenuteachingclient.util.network.task.HttpPostDataByFormTask;
import com.tianjian.slidingmenuteachingclient.util.network.task.HttpPostDataTask;
import com.tianjian.slidingmenuteachingclient.util.network.task.HttpWsTask;
import com.tianjian.slidingmenuteachingclient.util.network.task.HttpsOneSidePostDataTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract  class NetWorkHepler {
	
	
	
	/**
	 * 提交form表单
	 * @param url 地址
	 * @param params 参数
	 * @param callBack 回调
	 */
	public static void postDataByForm(String url,Map<String, Object> params,INetWorkCallBack callBack){
		new HttpPostDataByFormTask(callBack, url, params).execute();
	}
	/**
	 * post方式提交json数据
	 * @param url   地址
	 * @param jsonparams 参数
	 * @param callBack 回调
	 */
	public static void postData(String url,String jsonparams,INetWorkCallBack callBack){
		new HttpPostDataTask(callBack, url, jsonparams).execute();
	}
	
	public static void postDataSslOneSide(String url,String jsonAttr,String methodName,INetWorkCallBack callBack,Activity mainActivity){
		new HttpsOneSidePostDataTask(callBack, url, jsonAttr, methodName, mainActivity).execute();
	}
	
	/**
	 * 上传文件
	 * @param url 地址
	 * @param params 参数
	 * @param formFileList 文件集合
	 * @param callBack 回调
	 */
    public static void uploadForm(String url, Map<String, Object> params, List<FormFile> formFileList, INetWorkCallBack callBack){
		
	}
    
    public static void postWsData(String adrressName, String methodName,
    		HashMap<String, Object> request,INetWorkCallBack callback){
    	new HttpWsTask(adrressName,methodName, request, callback).execute();
    }
	
    /**
     * 显示图片
     */
    
    public static void showImg(String url ,ImageView imageView){
    	//需要改进用线程池管理
    	//TODO
//    	HttpImageTask task = new HttpImageTask(url, imageView);
//    	task.start();
    	//new HttpImageTask(url, imageView).executeOnExecutor(Executors.newCachedThreadPool());
    }
	
}

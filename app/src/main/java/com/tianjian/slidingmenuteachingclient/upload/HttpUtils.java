/**
 * Copyright (c) 2013 SanRenXing, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * SanRenXing, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with SanRenXing.
 */
package com.tianjian.slidingmenuteachingclient.upload;

import com.tianjian.slidingmenuteachingclient.common.Constant;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;


/**
 * TODO
 * <p>
 * Title: HttpUtils.java
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SanRenXing
 * </p>
 * <p>
 * team: SanRenXingTeam
 * </p>
 * <p>
 * 
 * @author: cheng
 *          </p>
 * @date 2015年10月24日上午11:51:11
 * @version 1.0
 * 
 */
public class HttpUtils {
	// /< 请求服务URL


	public static String sendMessage(Map<String, String> params, String methodName) {
		String result ="";
		HttpClient httpClient = new DefaultHttpClient();
		// 请求超时
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
        // 读取超时
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);
		HttpPost httpRequest = new HttpPost("http://192.168.0.37:8080/DWMSWS/servlet/");
		 LinkedList<NameValuePair> listparams = new LinkedList <NameValuePair>();
		 for(Entry<String, String> entry : params.entrySet()){
			 NameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), entry.getValue());
			 listparams.add(nameValuePair);
		 }
		 try {
			httpRequest.setEntity(new UrlEncodedFormEntity(listparams, HTTP.UTF_8));
			HttpResponse response = httpClient.execute(httpRequest);
			if(response.getStatusLine().getStatusCode() == 200){
				result = EntityUtils.toString(response.getEntity());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}

	 public static String uploadFile(File file,String fileName){  
		 //Constant.SERVER_URL+"/servlet/UploadServlet?params=upload&id="+id+"&name="+fileName
		 String result ="";
		 try {
		        HttpClient httpClient = new DefaultHttpClient();
		        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
		        // 读取超时
				httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);
		        HttpPost httpPost = new HttpPost(Constant.SERVER_URL+"/servlet/UploadServlet?params=upload&name="+fileName);
		       
		    	String temStr = new String(file.getPath());
		    	String replace = temStr.substring(0,temStr.lastIndexOf(File.separator));
		    	File listFolder = new File(replace);
		    	File[] listFiles = listFolder.listFiles();
		    	if(listFolder.exists() && listFiles!=null && listFiles.length>1 ){
		    		String[] partsPaths = new String[listFiles.length];
		    		for(int i=0;i<listFiles.length;i++){
		    			partsPaths[i] = replace+File.separator+listFiles[i].getName();
		    		}
		    		int lastStr = Integer.valueOf(fileName.substring(fileName.length()-5,fileName.length()-4));
		    		String finalName = fileName.substring(0, fileName.length()-5);
		    		finalName = finalName+(lastStr+1%10);
//		    		file = uniteAMRFile(partsPaths, replace+File.separator+finalName+".amr");
		    	}
		        InputStreamEntity reqEntity = new InputStreamEntity(new FileInputStream(file), -1);
				reqEntity.setContentType("binary/octet-stream");
			    reqEntity.setChunked(true); // Send in multiple parts if needed
			    httpPost.setEntity(reqEntity);
			    HttpResponse response = httpClient.execute(httpPost);
			    if(response.getStatusLine().getStatusCode() == 200){
					result = EntityUtils.toString(response.getEntity());
					return result; 
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	        return result;  
	    }  
	 
	 
	 public static String getFile(String id){  
	        try {  
	            String boundary = "*****";  
	            URL url = new URL(Constant.SERVER_URL+"/servlet/UploadServlet?params=get&id="+id);  
	            HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
	            /* 允许使用输入流，输出流，不允许使用缓存*/  
	            conn.setDoInput(true);  
	            conn.setDoOutput(true);  
	            conn.setUseCaches(false);  
	            /* 请求方式*/  
	            conn.setRequestMethod("GET");  
	            conn.setRequestProperty("Charset", "UTF-8");  
	            conn.setRequestProperty("Connection", "Keep-Alive");  
	            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);  
                if(conn.getResponseCode() == 200){  
                	InputStream response = conn.getInputStream();
                	byte[] b = new byte[1024];   
                    int len = 0;   
                    int temp=0;          //所有读取的内容都使用temp接收   
                    while((temp=response.read())!=-1){    //当没有读取完时，继续读取   
                        b[len]=(byte)temp;   
                        len++;   
                    }   
                    response.close();   
                    return new String(new String(b,0,len));  
                }  
	              
	        } catch (MalformedURLException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	        return "获取失败";  
	    }  
	 
	 
	 public static String deletFile(String id,String fileName){  
		 //Constant.SERVER_URL+"/servlet/UploadServlet?params=upload&id="+id+"&name="+fileName
        try {  
            URL url = new URL(Constant.SERVER_URL+"/servlet/UploadServlet?params=delete&id="+id+"&name="+fileName);  
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
            /* 允许使用输入流，输出流，不允许使用缓存*/  
            conn.setDoInput(true);  
            conn.setDoOutput(true);  
            conn.setUseCaches(false);  
            /* 请求方式*/  
            conn.setRequestMethod("GET");  
            conn.setRequestProperty("Charset", "UTF-8");  
            conn.setRequestProperty("Connection", "Keep-Alive");  
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary="+"");  
            if(conn.getResponseCode() == 200){  
            	InputStream response = conn.getInputStream();
            	byte[] b = new byte[1024];   
                int len = 0;   
                int temp=0;          //所有读取的内容都使用temp接收   
                while((temp=response.read())!=-1){    //当没有读取完时，继续读取   
                    b[len]=(byte)temp;   
                    len++;   
                }   
                response.close();   
                return new String(new String(b,0,len));  
            }  
              
        } catch (MalformedURLException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
		 return "获取失败";  
 }  
	 
	 /***
		 * 
		* 文件合并
		* @Title: uniteAMRFile
		* @param partsPaths
		* @param unitedFilePath
		* @return void
		* @throws
		* @author Leipeijie
		 */
	public static File uniteAMRFile(String[] partsPaths, String unitedFilePath) {
		try {
			File unitedFile = new File(unitedFilePath);
			FileOutputStream fos = new FileOutputStream(unitedFile);
			RandomAccessFile ra = null;
			for (int i = 0; i < partsPaths.length; i++) {
				ra = new RandomAccessFile(partsPaths[i], "r");
				if (i != 0) {
					ra.seek(6);
				}
				byte[] buffer = new byte[512 ];
				int len = 0;
				while ((len = ra.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
				}
			}
			ra.close();
			fos.close();
			return unitedFile;
		} catch (Exception e) {
		}
		return null;
	}
	 
}

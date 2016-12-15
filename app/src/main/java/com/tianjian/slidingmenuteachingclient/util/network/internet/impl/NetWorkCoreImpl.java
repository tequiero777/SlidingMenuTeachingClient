package com.tianjian.slidingmenuteachingclient.util.network.internet.impl;

import android.app.Activity;
import android.text.SpannableStringBuilder;

import com.tianjian.slidingmenuteachingclient.application.SystemApplcation;
import com.tianjian.slidingmenuteachingclient.bean.FormFile;
import com.tianjian.slidingmenuteachingclient.common.Constant;
import com.tianjian.slidingmenuteachingclient.util.StringUtil;
import com.tianjian.slidingmenuteachingclient.util.https.CustomSSLSocketFactory;
import com.tianjian.slidingmenuteachingclient.util.network.helper.HttpClientSslHelper;
import com.tianjian.slidingmenuteachingclient.util.network.internet.INetWorkCore;
import com.tianjian.slidingmenuteachingclient.util.network.internet.SSLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class NetWorkCoreImpl  implements INetWorkCore {

	@Override
	public String postDataFormBySsl(String url, Map<String, Object> params,String authorization) {
		
		HttpClientSslHelper httpClientSslHelper = HttpClientSslHelper.getInstance();
		
		HttpClient httpClient = httpClientSslHelper.getSslHttpClient();
		if(httpClient != null){
	        try{  
	            // 创建HttpPost对象。  
	            HttpPost post = new HttpPost(url);
	            if(!StringUtil.isEmpty(authorization)){
	            	 post.addHeader("Authorization", authorization);
	            }
	            // 如果传递参数个数比较多的话可以对传递的参数进行封装  
	            List<NameValuePair> listParams = new ArrayList<NameValuePair>();
	            for(String key : params.keySet()){  
	                //封装请求参数  
	            	listParams.add(new BasicNameValuePair(key , (String) params.get(key)));
	            }  
	            // 设置请求参数  
	            post.setEntity(new UrlEncodedFormEntity(listParams, HTTP.UTF_8));
	            // 发送POST请求  
	            HttpResponse httpResponse = httpClient.execute(post);
	            // 如果服务器成功地返回响应  
	            if (httpResponse.getStatusLine() .getStatusCode() == 200){  
	                // 获取服务器响应字符串  
	                String result = EntityUtils.toString(httpResponse.getEntity());
	               return result;
	            		    
	            }  
	        }catch(Exception e){  
	        	return e.getMessage();
	        }finally{  
	            httpClient.getConnectionManager().shutdown();  
	        }  
		}else{
			return "创建httpClien失败";
		}
		return "";
	}

	@Override
	public String postFile(String path, Map<String, String> params,List<FormFile> files) throws Exception {
		
		
		return "";
	}

	
	
	
	@Override
	public Object postWsData(String addressName,String methodName, HashMap<String, Object> request)  {
		// 实例化SoapObject对象
		SoapObject soapObject = null;
		if (StringUtil.empty(addressName)) {
			String ns = Constant.getServerNs("basic");
			soapObject = new SoapObject(ns, methodName);
		} else {
			String ns = Constant.getServerNs(addressName);
			soapObject = new SoapObject(ns, methodName);
		}
		if (request != null) {
			for(Entry<String, Object>  entry : request.entrySet()){
				PropertyInfo info = new PropertyInfo();
				info.setName(entry.getKey());
				if(entry.getValue() == null){
					
				}else{
					info.setType(entry.getValue().getClass());
					if(entry.getValue() instanceof SpannableStringBuilder){
						info.setValue(entry.getValue()+"");
					}else{
						info.setValue(entry.getValue());
					}
				}
				
				soapObject.addProperty(info);
			}
			
			
		}
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		
//		envelope.addMapping(Constant.getServerNs(addressName),"MsgHeader",MsgHeader.class);
		envelope.bodyOut = soapObject;
		// 是否调用DotNet开发的WebService  
		envelope.dotNet = true;
		envelope.setOutputSoapObject(soapObject);
		HttpTransportSE transport = null;
		if (StringUtil.empty(addressName)) {
			String ws = Constant.getServerWsUrl("basic");
			transport = new HttpTransportSE(ws);
		} else {
			String ws = Constant.getServerWsUrl(addressName);
			transport = new HttpTransportSE(ws,25000);
		}

		try {
			SSLConnection.allowAllSSL();
			transport.call("process", envelope);
			if (envelope.getResponse() != null) {
				SoapObject result = (SoapObject) envelope.bodyIn;
				return result;
			} 
		} catch (IOException e) {
			return e.getMessage();
		} catch (XmlPullParserException e) {
			return e.getMessage();
		}
		return "";
	}


	@Override
	public String postDataBySsl(String url, String jsonStr,String authorization,String methodName) {
		try {
			HttpClientSslHelper httpClientSslHelper = HttpClientSslHelper.getInstance();
			
			HttpClient httpClient = httpClientSslHelper.getSslHttpClient();
		    HttpPost httppost = new HttpPost(url);
		    if(!StringUtil.isEmpty(authorization)){
		    	httppost.addHeader("Authorization", authorization);
		    }
		    httppost.addHeader("Content-Type", "application/json");  
		    httppost.addHeader("User-Agent", "imgfornote");  List<NameValuePair> paramsList = new LinkedList<NameValuePair>();
			paramsList.add(new BasicNameValuePair("methodName", methodName));
			paramsList.add(new BasicNameValuePair("tenantId", Constant.TENANT_ID));
			paramsList.add(new BasicNameValuePair("attr", jsonStr));
			httppost.setEntity(new UrlEncodedFormEntity(paramsList));
			HttpResponse response;
		    response = httpClient.execute(httppost);  
		    //检验状态码，如果成功接收数据   
		    int code = response.getStatusLine().getStatusCode();  
		    if (code == 200) {   
		        String rev = EntityUtils.toString(response.getEntity());
		        return rev;
		       
		    }  
		    return "";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}     
	    
	}

	@Override
	public String postDataOneSideSsl(String url,String methodName,String jsonAttr,Activity baseActivity) {
		HttpClient client = null;
		try {
			KeyStore trustkeyStore = KeyStore.getInstance("bks");
			InputStream tsIn = NetWorkCoreImpl.class.getClassLoader().getResourceAsStream(Constant.KEY_STORE_TRUST_PATH);
			trustkeyStore.load(tsIn, "123456".toCharArray());
			
			HttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, 10000);
			HttpConnectionParams.setSoTimeout(httpParams, 10000);
			HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(httpParams, HTTP.UTF_8);
	        client = new DefaultHttpClient(httpParams);
	        SSLSocketFactory ssf = new CustomSSLSocketFactory(trustkeyStore);
			
			ClientConnectionManager ccm = client.getConnectionManager();
	        SchemeRegistry sr = ccm.getSchemeRegistry();
	        sr.register(new Scheme("https", ssf, 443));
	        DefaultHttpClient sslClient = new DefaultHttpClient(ccm,client.getParams());
			
			
		    HttpPost httppost = new HttpPost(url);
		    SystemApplcation applcation = (SystemApplcation) baseActivity.getApplication();
		    String authorization = "";
		    httppost.addHeader("Authorization", authorization);
		    List<NameValuePair> paramsList = new LinkedList<NameValuePair>();
			paramsList.add(new BasicNameValuePair("methodName", methodName));
			paramsList.add(new BasicNameValuePair("tenantId", Constant.TENANT_ID));
			paramsList.add(new BasicNameValuePair("attr", jsonAttr));
			httppost.setEntity(new UrlEncodedFormEntity(paramsList, HTTP.UTF_8));
			HttpResponse response;
		    response = sslClient.execute(httppost);  
		    //检验状态码，如果成功接收数据   
		    int code = response.getStatusLine().getStatusCode();  
		    if (code == 200) {   
		        String rev = EntityUtils.toString(response.getEntity());
		        return rev;
		       
		    }  
		    return "";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}     
	}

}

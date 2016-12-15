
package com.tianjian.slidingmenuteachingclient.util.https;


import com.tianjian.slidingmenuteachingclient.common.Constant;

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

import java.io.InputStream;
import java.security.KeyStore;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("deprecation")
public class DataLoader {

	public static HttpResponse secureLoadData(String methodName, String jsonStr, String methodParams, String selectActionUrl){
    	HttpClient client = null;
    	try{
//    		SSLContext ctx = SSLContext.getInstance("TLS");
	    	KeyStore keyStore = KeyStore.getInstance("PKCS12");
	    	KeyStore trustkeyStore = KeyStore.getInstance("bks");
	    	InputStream ksIn = DataLoader.class.getClassLoader().getResourceAsStream(Constant.KEY_STORE_CLIENT_PATH);
			InputStream tsIn = DataLoader.class.getClassLoader().getResourceAsStream(Constant.KEY_STORE_TRUST_PATH);
	        keyStore.load(ksIn, Constant.PASSWORD.toCharArray());
	        trustkeyStore.load(tsIn, Constant.PASSWORD.toCharArray());
	        tsIn.close();
	        ksIn.close();
	//      ctx.init(null, new TrustManager[] { new CustomX509TrustManager(keyStore) },new SecureRandom());
	        HttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, 10000);
			HttpConnectionParams.setSoTimeout(httpParams, 10000);
			HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(httpParams, HTTP.UTF_8);
	        client = new DefaultHttpClient(httpParams);
	        SSLSocketFactory ssf = new CustomSSLSocketFactory(trustkeyStore);
//	        SSLSocketFactory ssf = new SSLSocketFactory(keyStore, Constant.PASSWORD, trustkeyStore);
//	        ssf.setHostnameVerifier(SSLSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
	        ClientConnectionManager ccm = client.getConnectionManager();
	        SchemeRegistry sr = ccm.getSchemeRegistry();
	        sr.register(new Scheme("https", ssf, 443));
	        DefaultHttpClient sslClient = new DefaultHttpClient(ccm,client.getParams());
	        HttpPost httpPost = new HttpPost(Constant.getServerWsUrl(selectActionUrl));
	
	        List<NameValuePair> paramsList = new LinkedList<NameValuePair>();
			paramsList.add(new BasicNameValuePair("methodName", methodName));
			paramsList.add(new BasicNameValuePair("attr", jsonStr));
			httpPost.setEntity(new UrlEncodedFormEntity(paramsList));
	        HttpResponse response = sslClient.execute(httpPost);
	        return response;
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	
        }
		return null;
    }

}
package com.tianjian.slidingmenuteachingclient.util.network.helper;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpsConnectionHelper {
	
	/**
	 * 获得HttpsURLConnection
	 * @param httpsUrl
	 * @return
	 * @throws Exception
	 */
	public static HttpsURLConnection getHttpsURLConnection(String httpsUrl) throws Exception{
		URL url = new URL(httpsUrl); 
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setSSLSocketFactory(TwoWaysAuthenticationSSLSocketFactory.getSSLSocketFactory());
		conn.connect();
		return conn;
	}
	
}

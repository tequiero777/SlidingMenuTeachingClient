package com.tianjian.slidingmenuteachingclient.util.network.helper;


import com.tianjian.slidingmenuteachingclient.common.Constant;

import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

@SuppressWarnings("deprecation")
public class HttpClientSslHelper {

	private static final String KEY_STORE_TYPE_BKS = "bks";
	private static final String KEY_STORE_TYPE_P12 = "PKCS12";
	private static final String SCHEME_HTTPS = "https";
	private static KeyStore keyStore;
	private static KeyStore trustStore;
	private static HttpClientSslHelper httpClientSslHelper;
	
	private HttpClientSslHelper(){}
	
	
	
	public synchronized static HttpClientSslHelper getInstance(){
		if(httpClientSslHelper == null){
			httpClientSslHelper = new HttpClientSslHelper();
		}
		return httpClientSslHelper;
	}

	public HttpClient getSslHttpClient() {
			HttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, 10000);
			HttpConnectionParams.setSoTimeout(httpParams, 10000);
			HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(httpParams, HTTP.UTF_8);
			HttpClient httpsClient = new DefaultHttpClient(httpParams);
			try {
				SSLSocketFactory socketFactory = getSocketFactory();
				Scheme sch = new Scheme(SCHEME_HTTPS, socketFactory, Constant.HTTPS_PORT);
				httpsClient.getConnectionManager().getSchemeRegistry().register(sch);
	
			} catch (KeyManagementException e) {
				e.printStackTrace();
			} catch (UnrecoverableKeyException e) {
				e.printStackTrace();
			} catch (KeyStoreException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		return httpsClient;
	}

	public SSLSocketFactory getSocketFactory()
			throws KeyStoreException, IOException, NoSuchAlgorithmException,
			KeyManagementException, UnrecoverableKeyException {
		keyStore = KeyStore.getInstance(KEY_STORE_TYPE_P12);
		
		trustStore = KeyStore.getInstance(KEY_STORE_TYPE_BKS);
		InputStream ksIn = HttpClientSslHelper.class.getClassLoader().getResourceAsStream(Constant.KEY_STORE_CLIENT_PATH);
		InputStream tsIn = HttpClientSslHelper.class.getClassLoader().getResourceAsStream(Constant.KEY_STORE_TRUST_PATH);
		try {
			keyStore.load(ksIn, Constant.KEY_STORE_PASSWORD.toCharArray());
			trustStore.load(tsIn, Constant.KEY_STORE_TRUST_PASSWORD.toCharArray());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeStream(ksIn);
			closeStream(tsIn);
		}

		SSLSocketFactory socketFactory = new SSLSocketFactory(keyStore, Constant.KEY_STORE_PASSWORD, trustStore);
		return socketFactory;
	}

	private void closeStream(InputStream ksIn) {
		try {
			ksIn.close();
		} catch (Exception ignore) {
		}
	}
}

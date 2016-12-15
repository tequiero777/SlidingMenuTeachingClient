/**
 * Copyright (c) 2013 Tianjian, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * Tianjian, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Tianjian.
 */
package com.tianjian.slidingmenuteachingclient.bean;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * TODO
 * <p>
 * Title: MyWebViewClient.java
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: Tianjian
 * </p>
 * <p>
 * team: TianjianTeam
 * </p>
 * <p>
 * 
 * @author: cheng
 *          </p>
 * @date 2014年11月6日下午4:48:56
 * @version 1.0
 * 
 */
public class MyWebViewClient extends WebViewClient {
	
	public MyWebViewClient() {
		super();
	}
	
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		view.getCertificate();
		view.loadUrl(url);
		view.getCertificate();
		return false;
	}
	
	
	@Override
	public void onReceivedSslError(WebView view, SslErrorHandler handler,
			SslError error) {
		//handler.cancel(); 默认的处理方式，WebView变成空白页
		handler.proceed();
	    //handleMessage(Message msg); 其他处理
	}
	
	/**
     * If you want to use client certificate by Android 4.x WebView, you should uncomment this.
     * However this method uses private class `ClientCertRequestHandler`.
     * You need to create original android.jar(contains all classes)
     * Please read this issue.
     * https://github.com/yonekawa/webview-with-client-certificate/issues/1
     */
    
//	@Override
//    public void onReceivedClientCertRequest( WebView view, ClientCertRequestHandler handler, String host_and_port ) {
//        try {
//            KeyStore trustkeyStore = KeyStore.getInstance("bks");
// 	    	InputStream ksIn = DataLoader.class.getClassLoader().getResourceAsStream(Constant.KEY_STORE_CLIENT_PATH);
// 			InputStream tsIn = DataLoader.class.getClassLoader().getResourceAsStream(Constant.KEY_STORE_TRUST_PATH);
// 	        trustkeyStore.load(tsIn, Constant.PASSWORD.toCharArray());
// 	        SSLContext ctx = SSLContext.getInstance("TLS");
// 	        ctx.init(null, new TrustManager[] { new CustomX509TrustManager(trustkeyStore) },new SecureRandom());
//            KeyStore store = KeyStore.getInstance("PKCS12");
//        	store.load(ksIn, Constant.PASSWORD.toCharArray());
//        	tsIn.close();
//  	        ksIn.close();
//        	PrivateKey privateKey = null;
//            X509Certificate[] certificates = null;
//            Enumeration<String> e = store.aliases();
//            for (; e.hasMoreElements(); ) {
//                String alias = e.nextElement();
//                if (store.isKeyEntry(alias)) {
//                    KeyStore.PrivateKeyEntry entry = (KeyStore.PrivateKeyEntry)store.getEntry( alias, null );
//                    privateKey = entry.getPrivateKey();
//                    certificates = (X509Certificate[])entry.getCertificateChain();
//                }
//            }
//           
//            handler.proceed( privateKey, certificates );
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
	
	
	
	
}

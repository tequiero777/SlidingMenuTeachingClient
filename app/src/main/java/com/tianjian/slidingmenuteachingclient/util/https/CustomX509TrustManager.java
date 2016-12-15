/**
 * Copyright (c) 2015 Tianjian, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * Tianjian, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Tianjian.
 */
package com.tianjian.slidingmenuteachingclient.util.https;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * TODO
 * <p>Title: CustomX509TrustManager.java</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: Tianjian</p>
 * <p>team: TianjianTeam</p>
 *
 * @author: Leipeijie
 * @date 2015年4月13日下午4:53:36
 * @version 1.0
 */

public class CustomX509TrustManager implements X509TrustManager {

	 private X509TrustManager standardTrustManager = null;
	 private List<X509TrustManager> trustManagers = new ArrayList<X509TrustManager>();
	 
	    /**
	     * Constructor for EasyX509TrustManager.
	     */
	    public CustomX509TrustManager(KeyStore keystore) throws NoSuchAlgorithmException, KeyStoreException {
	        super();
	            TrustManagerFactory factory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
	            factory.init(keystore);

	            for (TrustManager tm : factory.getTrustManagers()) {
	                if (tm instanceof X509TrustManager)
	                    trustManagers.add((X509TrustManager)tm);
	            }
	        
	        if (trustManagers.size() == 0) {
	            throw new NoSuchAlgorithmException("no trust manager found");
	        }
	        this.standardTrustManager = (X509TrustManager) trustManagers.get(0);
	    }
	 
	    /**
	     * @see X509TrustManager#checkClientTrusted(X509Certificate[],String authType)
	     */
	    public void checkClientTrusted(X509Certificate[] certificates, String authType) throws CertificateException {
//	        standardTrustManager.checkClientTrusted(certificates, authType);
	    }
	 
	    /**
	     * @see X509TrustManager#checkServerTrusted(X509Certificate[],String authType)
	     */
	    public void checkServerTrusted(X509Certificate[] certificates, String authType) throws CertificateException {
//	        if ((certificates != null) && (certificates.length == 1)) {
//	        	 standardTrustManager.checkServerTrusted(certificates, authType);
//	        }
	    }
	 
	    /**
	     * @see X509TrustManager#getAcceptedIssuers()
	     */
	    public X509Certificate[] getAcceptedIssuers() {
	        return this.standardTrustManager.getAcceptedIssuers();
	    }
}

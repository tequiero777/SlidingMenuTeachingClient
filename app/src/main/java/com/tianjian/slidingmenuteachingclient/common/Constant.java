package com.tianjian.slidingmenuteachingclient.common;

import java.util.HashMap;
import java.util.Map;


public class Constant {
	//15922911693 123456

	/*********************************** http配置信息*****************************************/
	/** 地址端口*/
//	public static String IP_ADDRESS = PropertiesUtil.getProperty("IP_ADDRESS");
//	public static String IP_ADDRESS = "http://60.22.147.147:9080";
	public static String IP_ADDRESS = "http://192.168.191.1:8080";
	/*** ws地址 ***/
	public static final String SERVER_URL = IP_ADDRESS+"/TeachingServer";
	
	public static final String KEY_STORE_PASSWORD = "123456";
	public static final String KEY_STORE_TRUST_PASSWORD = "123456";
	public static final int HTTPS_PORT = 8443;
	public static final String KEY_STORE_CLIENT_PATH = "com/tianjian/teachingclient/basic/core/certificate/client.key.p12";
	public static final String KEY_STORE_TRUST_PATH = "com/tianjian/teachingclient/basic/core/certificate/client.truststore";

	/** 服务器webservice地址前缀 */
	public static final String SERVER_WEBSERVICE_URL_PREFIX = SERVER_URL + "/ws";

	/**
	 * 服务器根目录
	 */
	public static final String SERVER_ROOT= "http://192.168.191.1:8080/TeachingServer";
	/**
	 * openfire服务器地址
	 */
	
	/** 每页加载的数量 */
	public static final int PAGE_SIZE = 10;
	/**
	 * 下载地址
	 */
	public static String downloadDir = "";
	
	/**
	 * 租户id
	 */
	public static String TENANT_ID = "20160725";
	public static String PASSWORD = "123456";
	//ws请求用户名密码
	public static final String WS_USERNAME="tianjian-ws";
	public static final String WS_PASSWORD="tianjianquyu-panjin-ws";
	
	/*** MQTT地址 ***/
//	public static final String MQTT_ADDRESS = PropertiesUtil.getProperty("MQTT_ADDRESS");
	public static final String MQTT_ADDRESS = "tcp://192.168.191.1:1883";
	public static final boolean MQTT_CLEAN_SESSION = true; // 这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
	public static final int RUNNING_TASKS_SIZE = 100;
	
	public static String getServerWsUrl(String key) {
		return putServerWsUrl().get(key);
	}

	public static String getServerNs(String key) {
		return putServerNameSpace().get(key);
	}

	/**
	 * 服务器WS地址
	 */
	private static Map<String, String> putServerWsUrl() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("loginWs", SERVER_WEBSERVICE_URL_PREFIX + "/InLoginSrv");
		map.put("resourcesWs", SERVER_WEBSERVICE_URL_PREFIX + "/InQueryResourcesSrv");
		map.put("countWs", SERVER_WEBSERVICE_URL_PREFIX + "/InQueryCountSrv");
		map.put("taskWs", SERVER_WEBSERVICE_URL_PREFIX + "/InQueryTaskSrv");
		map.put("operatetaskandquestionWs", SERVER_WEBSERVICE_URL_PREFIX + "/InOperateTaskAndInOperateQuestionSrv");
		map.put("appUpdateWs", SERVER_WEBSERVICE_URL_PREFIX + "/InTeachApplicationUpdateSrv");
		map.put("importtaskWs", SERVER_WEBSERVICE_URL_PREFIX + "/ImPortTaskSrv");
		map.put("importquestionWs", SERVER_WEBSERVICE_URL_PREFIX + "/ImPortQuestionSrv");
		map.put("queryquestionWs", SERVER_WEBSERVICE_URL_PREFIX + "/InQueryQuestionSrv");
		map.put("imPortInformationSrv", SERVER_WEBSERVICE_URL_PREFIX + "/ImPortInformationSrv");
		map.put("inTeachManagerSrv", SERVER_WEBSERVICE_URL_PREFIX + "/InTeachManagerSrv");
		return map;
	}

	/**
	 * 服务器命名空间
	 */
	private static Map<String, String> putServerNameSpace() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("loginWs", "http://tj.teach.com/InLoginSrv");
		map.put("resourcesWs", "http://tj.teach.com/InQueryResourcesSrv");
		map.put("countWs", "http://tj.teach.com/InQueryCountSrv");
		map.put("taskWs", "http://tj.teach.com/InQueryTaskSrv");
		map.put("operatetaskandquestionWs", "http://tj.teach.com/InOperateTaskAndInOperateQuestionSrv");
		map.put("appUpdateWs", "http://tj.teach.com/InTeachApplicationUpdateSrv");
		map.put("importtaskWs", "http://tj.teach.com/ImPortTaskSrv");
		map.put("importquestionWs", "http://tj.teach.com/ImPortQuestionSrv");
		map.put("queryquestionWs", "http://tj.teach.com/InQueryQuestionSrv");
		map.put("imPortInformationSrv", "http://tj.teach.com/ImPortInformationSrv");
		map.put("inTeachManagerSrv", "http://tj.teach.com/InTeachManagerSrv");
		return map;
	}
}

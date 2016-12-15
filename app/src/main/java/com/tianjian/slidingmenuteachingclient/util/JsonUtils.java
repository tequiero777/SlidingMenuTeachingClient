/**
 * JsonUtils.java
 * 
 * Create Version: 1.0
 * Author: lixh
 * Create Date: 2012-5-7
 * 
 * Copyright (c) 2012 CQCIS. All Right Reserved.
 */
package com.tianjian.slidingmenuteachingclient.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.bind.SqlDateTypeAdapter;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * JsonUtils （类说明）
 * 
 * @author Luoqb
 */
public class JsonUtils {
	public static final String EMPTY = "";
	/** 空的 {@code JSON} 数据 - <code>"{}"</code>。 */
	public static final String EMPTY_JSON = "{}";
	/** 空的 {@code JSON} 数组(集合)数据 - {@code "[]"}。 */
	public static final String EMPTY_JSON_ARRAY = "[]";
	/** 默认的 {@code JSON} 日期/时间字段的格式化模式。 */
	public static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd"; // 去掉final以便于其他格式转换使用,dul-20140730修改
	/** {@code Google Gson} 的 {@literal @Since} 注解常用的版本号常量 - {@code }。 */
	public static final Double SINCE_VERSION_10 = 1.0d;
	/** {@code Google Gson} 的 {@literal @Since} 注解常用的版本号常量 - {@code }。 */
	public static final Double SINCE_VERSION_11 = 1.1d;
	/** {@code Google Gson} 的 {@literal @Since} 注解常用的版本号常量 - {@code }。 */
	public static final Double SINCE_VERSION_12 = 1.2d;
	/**Gjson 对象*/
	private static Gson gson = new Gson();

	/**
	 * 将给定的目标对象根据指定的条件参数转换成 {@code JSON} 格式的字符串。
	 * <p />
	 * <strong>该方法转换发生错误时，不会抛出任何异常。若发生错误时，曾通对象返回 <code>"{}"</code>； 集合或数组对象返回
	 * <code>"[]"</code></strong>
	 * 
	 * @param target
	 *            目标对象。
	 * @param targetType
	 *            目标对象的类型。
	 * @param isSerializeNulls
	 *            是否序列化 {@code null} 值字段。
	 * @param version
	 *            字段的版本号注解。
	 * @param datePattern
	 *            日期字段的格式化模式。
	 * @param excludesFieldsWithoutExpose
	 *            是否排除未标注 {@literal @Expose} 注解的字段。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 */
	public static String toJson(Object target, Type targetType, boolean isSerializeNulls, Double version, String datePattern, boolean excludesFieldsWithoutExpose) {
		if (target == null)
			return EMPTY_JSON;
		GsonBuilder builder = new GsonBuilder();
		if (isSerializeNulls)
			builder.serializeNulls();
		if (version != null)
			builder.setVersion(version.doubleValue());
		if (isEmpty(datePattern))
			datePattern = DEFAULT_DATE_PATTERN;
		builder.setDateFormat(datePattern);
		if (excludesFieldsWithoutExpose)
			builder.excludeFieldsWithoutExposeAnnotation();
		String result = EMPTY;
		Gson gson = builder.create();
		try {
			if (targetType != null) {
				result = gson.toJson(target, targetType);
			} else {
				result = gson.toJson(target);
			}
		} catch (Exception ex) {
			Log.v("JSON转换出错:", "目标对象 " + target.getClass().getName() + " 转换 JSON 字符串时，发生异常！", ex);
			if (target instanceof Collection || target instanceof Iterator || target instanceof Enumeration || target.getClass().isArray()) {
				result = EMPTY_JSON_ARRAY;
			} else
				result = EMPTY_JSON;
		}
		return result;
	}

	/**
	 * 将给定的目标对象转换成 {@code JSON} 格式的字符串。<strong>此方法只用来转换普通的 {@code JavaBean}
	 * 对象。</strong>
	 * <ul>
	 * <li>该方法只会转换标有 {@literal @Expose} 注解的字段；</li>
	 * <li>该方法不会转换 {@code null} 值字段；</li>
	 * <li>该方法会转换所有未标注或已标注 {@literal @Since} 的字段；</li>
	 * <li>该方法转换时使用默认的 日期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss SSS}；</li>
	 * </ul>
	 * 
	 * @param target
	 *            要转换成 {@code JSON} 的目标对象。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 */
	public static String toJson(Object target) {
		return toJson(target, null, false, null, null, true);
	}

	/**
	 * 将给定的目标对象转换成 {@code JSON} 格式的字符串。<strong>此方法只用来转换普通的 {@code JavaBean}
	 * 对象。</strong>
	 * <ul>
	 * <li>该方法只会转换标有 {@literal @Expose} 注解的字段；</li>
	 * <li>该方法不会转换 {@code null} 值字段；</li>
	 * <li>该方法会转换所有未标注或已标注 {@literal @Since} 的字段；</li>
	 * </ul>
	 * 
	 * @param target
	 *            要转换成 {@code JSON} 的目标对象。
	 * @param datePattern
	 *            日期字段的格式化模式。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 */
	public static String toJson(Object target, String datePattern) {
		return toJson(target, null, false, null, datePattern, true);
	}

	/**
	 * 将给定的目标对象转换成 {@code JSON} 格式的字符串。<strong>此方法只用来转换普通的 {@code JavaBean}
	 * 对象。</strong>
	 * <ul>
	 * <li>该方法只会转换标有 {@literal @Expose} 注解的字段；</li>
	 * <li>该方法不会转换 {@code null} 值字段；</li>
	 * <li>该方法转换时使用默认的 日期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss SSS}；</li>
	 * </ul>
	 * 
	 * @param target
	 *            要转换成 {@code JSON} 的目标对象。
	 * @param version
	 *            字段的版本号注解({@literal @Since})。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 */
	public static String toJson(Object target, Double version) {
		return toJson(target, null, false, version, null, true);
	}

	/**
	 * 将给定的目标对象转换成 {@code JSON} 格式的字符串。<strong>此方法只用来转换普通的 {@code JavaBean}
	 * 对象。</strong>
	 * <ul>
	 * <li>该方法不会转换 {@code null} 值字段；</li>
	 * <li>该方法会转换所有未标注或已标注 {@literal @Since} 的字段；</li>
	 * <li>该方法转换时使用默认的 日期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss SSS}；</li>
	 * </ul>
	 * 
	 * @param target
	 *            要转换成 {@code JSON} 的目标对象。
	 * @param excludesFieldsWithoutExpose
	 *            是否排除未标注 {@literal @Expose} 注解的字段。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 */
	public static String toJson(Object target, boolean excludesFieldsWithoutExpose) {
		return toJson(target, null, false, null, null, excludesFieldsWithoutExpose);
	}

	/**
	 * 将给定的目标对象转换成 {@code JSON} 格式的字符串。<strong>此方法只用来转换普通的 {@code JavaBean}
	 * 对象。</strong>
	 * <ul>
	 * <li>该方法不会转换 {@code null} 值字段；</li>
	 * <li>该方法转换时使用默认的 日期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss SSS}；</li>
	 * </ul>
	 * 
	 * @param target
	 *            要转换成 {@code JSON} 的目标对象。
	 * @param version
	 *            字段的版本号注解({@literal @Since})。
	 * @param excludesFieldsWithoutExpose
	 *            是否排除未标注 {@literal @Expose} 注解的字段。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 */
	public static String toJson(Object target, Double version, boolean excludesFieldsWithoutExpose) {
		return toJson(target, null, false, version, null, excludesFieldsWithoutExpose);
	}

	/**
	 * 将给定的目标对象转换成 {@code JSON} 格式的字符串。<strong>此方法通常用来转换使用泛型的对象。</strong>
	 * <ul>
	 * <li>该方法只会转换标有 {@literal @Expose} 注解的字段；</li>
	 * <li>该方法不会转换 {@code null} 值字段；</li>
	 * <li>该方法会转换所有未标注或已标注 {@literal @Since} 的字段；</li>
	 * <li>该方法转换时使用默认的 日期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss SSSS}；</li>
	 * </ul>
	 * 
	 * @param target
	 *            要转换成 {@code JSON} 的目标对象。
	 * @param targetType
	 *            目标对象的类型。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 */
	public static String toJson(Object target, Type targetType) {
		return toJson(target, targetType, false, null, null, true);
	}

	/**
	 * 将给定的目标对象转换成 {@code JSON} 格式的字符串。<strong>此方法通常用来转换使用泛型的对象。</strong>
	 * <ul>
	 * <li>该方法只会转换标有 {@literal @Expose} 注解的字段；</li>
	 * <li>该方法不会转换 {@code null} 值字段；</li>
	 * <li>该方法转换时使用默认的 日期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss SSSS}；</li>
	 * </ul>
	 * 
	 * @param target
	 *            要转换成 {@code JSON} 的目标对象。
	 * @param targetType
	 *            目标对象的类型。
	 * @param version
	 *            字段的版本号注解({@literal @Since})。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 */
	public static String toJson(Object target, Type targetType, Double version) {
		return toJson(target, targetType, false, version, null, true);
	}

	/**
	 * 将给定的目标对象转换成 {@code JSON} 格式的字符串。<strong>此方法通常用来转换使用泛型的对象。</strong>
	 * <ul>
	 * <li>该方法不会转换 {@code null} 值字段；</li>
	 * <li>该方法会转换所有未标注或已标注 {@literal @Since} 的字段；</li>
	 * <li>该方法转换时使用默认的 日期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss SSS}；</li>
	 * </ul>
	 * 
	 * @param target
	 *            要转换成 {@code JSON} 的目标对象。
	 * @param targetType
	 *            目标对象的类型。
	 * @param excludesFieldsWithoutExpose
	 *            是否排除未标注 {@literal @Expose} 注解的字段。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 */
	public static String toJson(Object target, Type targetType, boolean excludesFieldsWithoutExpose) {
		return toJson(target, targetType, false, null, null, excludesFieldsWithoutExpose);
	}

	/**
	 * 将给定的目标对象转换成 {@code JSON} 格式的字符串。<strong>此方法通常用来转换使用泛型的对象。</strong>
	 * <ul>
	 * <li>该方法不会转换 {@code null} 值字段；</li>
	 * <li>该方法转换时使用默认的 日期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss SSS}；</li>
	 * </ul>
	 * 
	 * @param target
	 *            要转换成 {@code JSON} 的目标对象。
	 * @param targetType
	 *            目标对象的类型。
	 * @param version
	 *            字段的版本号注解({@literal @Since})。
	 * @param excludesFieldsWithoutExpose
	 *            是否排除未标注 {@literal @Expose} 注解的字段。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 */
	public static String toJson(Object target, Type targetType, Double version, boolean excludesFieldsWithoutExpose) {
		return toJson(target, targetType, false, version, null, excludesFieldsWithoutExpose);
	}

	/**
	 * 将给定的 {@code JSON} 字符串转换成指定的类型对象。
	 * 
	 * @param <T>
	 *            要转换的目标类型。
	 * @param json
	 *            给定的 {@code JSON} 字符串。
	 * @param token
	 *            {@code com.google.gson.reflect.TypeToken} 的类型指示类对象。
	 * @param datePattern
	 *            日期格式模式。
	 * @param isSerializeNulls
	 *            是否轉換為null字段
	 * @return 给定的 {@code JSON} 字符串表示的指定的类型对象。
	 */
	public static <T> T fromJson(String json, TypeToken<T> token, String datePattern, boolean isSerializeNulls) {
		if (isEmpty(json)) {
			return null;
		}
		json = json.replaceAll("\"\"", "null");
		GsonBuilder builder = new GsonBuilder();
		if (isSerializeNulls) {
			builder.serializeNulls();
		}
		if (isEmpty(datePattern)) {
			datePattern = DEFAULT_DATE_PATTERN;
		}
		builder.setDateFormat(datePattern);
		UtilDateTypeAdapter ds = new UtilDateTypeAdapter();
		SqlDateTypeAdapter sds = new SqlDateTypeAdapter();

		builder.registerTypeAdapter(java.util.Date.class, ds);
		builder.registerTypeAdapter(java.sql.Date.class, sds);
		Gson gson = builder.create();
		try {
			return gson.fromJson(json, token.getType());
		} catch (JsonSyntaxException ex) {
			ex.printStackTrace();
			Log.e("json转换出错:", json + " 无法转换为 " + token.getRawType().getName() + " 对象!", ex);
			return null;
		}
	}

	/**
	 * 将给定的 {@code JSON} 字符串转换成指定的类型对象。
	 * 
	 * @param <T>
	 *            要转换的目标类型。
	 * @param json
	 *            给定的 {@code JSON} 字符串。
	 * @param token
	 *            {@code com.google.gson.reflect.TypeToken} 的类型指示类对象。
	 * @return 给定的 {@code JSON} 字符串表示的指定的类型对象。
	 */
	public static <T> T fromJson(String json, TypeToken<T> token, boolean isSerializeNulls) {
		return fromJson(json, token, null, isSerializeNulls);
	}

	/**
	 * 转换所有的字段
	 * 
	 * @param json
	 * @param token
	 * @return
	 */
	public static <T> T fromJson(String json, TypeToken<T> token) {
		return fromJson(json, token, null, true);
	}

	/**
	 * 将给定的 {@code JSON} 字符串转换成指定的类型对象。<strong>此方法通常用来转换普通的 {@code JavaBean}
	 * 对象。</strong>
	 * 
	 * @param <T>
	 *            要转换的目标类型。
	 * @param json
	 *            给定的 {@code JSON} 字符串。
	 * @param clazz
	 *            要转换的目标类。
	 * @param datePattern
	 *            日期格式模式。
	 * @param isSerializeNulls
	 *            是否转换为null字段
	 * @return 给定的 {@code JSON} 字符串表示的指定的类型对象。
	 */
	public static <T> T fromJson(String json, Class<T> clazz, String datePattern, boolean isSerializeNulls) {
		if (isEmpty(json)) {
			return null;
		}
		json = json.replaceAll("\"\"", "null");
		GsonBuilder builder = new GsonBuilder();
		if (isSerializeNulls) {
			builder.serializeNulls();
		}
		if (isEmpty(datePattern)) {
			datePattern = DEFAULT_DATE_PATTERN;
		}
		builder.setDateFormat(datePattern);
		Gson gson = builder.create();
		try {
			return gson.fromJson(json, clazz);
		} catch (JsonSyntaxException ex) {
			Log.v("json 转换出错:", json + " 无法转换为 " + clazz.getName() + " 对象!", ex);
			return null;
		}
	}

	/**
	 * 将给定的 {@code JSON} 字符串转换成指定的类型对象。<strong>此方法通常用来转换普通的 {@code JavaBean}
	 * 对象。</strong>
	 * 
	 * @param <T>
	 *            要转换的目标类型。
	 * @param json
	 *            给定的 {@code JSON} 字符串。
	 * @param clazz
	 *            要转换的目标类。
	 * @param isSerializeNulls
	 *            是否转换为null字段
	 * @return 给定的 {@code JSON} 字符串表示的指定的类型对象。
	 */
	public static <T> T fromJson(String json, Class<T> clazz, boolean isSerializeNulls) {
		return fromJson(json, clazz, null, isSerializeNulls);
	}

	/**
	 * 转换所有的字段类型
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> clazz) {
		return fromJson(json, clazz, null, true);
	}

	public static boolean isEmpty(String inStr) {
		boolean reTag = false;
		if (inStr == null || "".equals(inStr)) {
			reTag = true;
		}
		return reTag;
	}

	/**
	 * 
	 * 获取json字符串中的指定属性的字符串
	 * 
	 * @Title: getValue
	 * @param json
	 *            jsoan字符串
	 * @param key
	 *            指定的属性
	 * @return String 返回指定属性的字符串，如果没有指定的属性则返回null
	 * @throws
	 * @author Puzhijun
	 */
	public static String getValue(String json, String key) {
		String retValue = null;
		try {
			retValue = new JSONObject(json).optString(key, null);
		} catch (JSONException e) {
			Log.e("JsonUtils.getValue", e.getMessage(), e);
		}
		return retValue;
	}
	
	/***
	 * 
	* 将bean 转换成 json 对日期 无操作，若对日期有要求 请勿使用这个方法
	* @Title: toJsonStr
	* @param object
	* @return
	* @return String
	* @throws
	* @author Leipeijie
	 */
	public static String toJsonStr(Object object){
		return gson.toJson(object);
	}
	
	/***
	 * 
	*将json 转换成 对象 对日期 无操作，若对日期有要求 请勿使用这个方法
	* @Title: formJsonStr
	* @param jsonStr
	* @param classOfT
	* @return
	* @return T
	* @throws
	* @author Leipeijie
	 */
	public static <T> T formJsonStr(String jsonStr,Class<T> classOfT){
		return gson.fromJson(jsonStr, classOfT);
	}

}

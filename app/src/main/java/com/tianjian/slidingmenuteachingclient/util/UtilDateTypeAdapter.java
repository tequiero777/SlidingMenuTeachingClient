/**
 * UtilDateTypeAdapter.java
 * 
 * Create Version: 1.0
 * Author: lixh
 * Create Date: 2012-5-8
 * 
 * Copyright (c) 2012 CQCIS. All Right Reserved.
 */
package com.tianjian.slidingmenuteachingclient.util;

import android.annotation.SuppressLint;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * UtilDateTypeAdapter （类说明） TimestampTypeAdapter
 * 
 * @author Luoqb
 */
@SuppressLint("SimpleDateFormat")
public class UtilDateTypeAdapter implements JsonDeserializer<Date> {
	private static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement,
	 * java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		Date date=null;
		try {
			date=DATE_TIME_FORMAT.parse(json.getAsString());
		} catch (ParseException e) {
			try {
				date = DATE_FORMAT.parse(json.getAsString());
			} catch (ParseException e1) {
				date=new Date(0L);
			}
		}
		return date;
	}
}

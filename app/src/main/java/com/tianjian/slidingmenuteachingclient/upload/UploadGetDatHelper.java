/**
 * Copyright (c) 2013 SanRenXing, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * SanRenXing, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with SanRenXing.
 */
package com.tianjian.slidingmenuteachingclient.upload;

import android.os.AsyncTask;

/**
 * TODO
 * <p>
 * Title: AsyncNetUtil.java
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
 * @date 2015年10月24日下午12:15:12
 * @version 1.0
 * 
 */
public abstract class UploadGetDatHelper extends AsyncTask<Void, Void, String>{

	private MsgCallBack callBack;
	private String id;
	
	public UploadGetDatHelper(String id,MsgCallBack callBack) {
		super();
		this.callBack = callBack;
		this.id =id;
	}
	
	@Override
	protected void onCancelled() {
		super.onCancelled();
		
	}
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		callBack.onPreExecute();
	}
	
	@Override
	protected String doInBackground(Void... p) {
		return HttpUtils.getFile(id)+"";
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		callBack.onResult(result);
	}
	
}

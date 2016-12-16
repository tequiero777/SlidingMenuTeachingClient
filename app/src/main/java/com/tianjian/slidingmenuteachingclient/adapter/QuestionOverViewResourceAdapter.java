/**
 * Copyright (c) 2013 Tianjian, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * Tianjian, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Tianjian.
 */
package com.tianjian.slidingmenuteachingclient.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.tianjian.slidingmenuteachingclient.R;
import com.tianjian.slidingmenuteachingclient.bean.InQueryQuestionSrv.InQueryQuestionResourcesDetailSrvOutputItem;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 * <p>Title: QuestionOverViewResourceAdapter.java</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Tianjian</p>
 * <p>team: TianjianTeam</p>
 * @author: Yehao
 * @date 2016年9月7日下午2:20:44
 * @version 1.0
 * 
 */
public class QuestionOverViewResourceAdapter extends BaseAdapter{
	private List<InQueryQuestionResourcesDetailSrvOutputItem>  list = new LinkedList<InQueryQuestionResourcesDetailSrvOutputItem>();
	/**context**/
	private Context myContext;
	private String currentFileName="";
	private String currentFileType="";
	private String currentFileUrl="";
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	public QuestionOverViewResourceAdapter(List<InQueryQuestionResourcesDetailSrvOutputItem>  list,
			Context myContext) {
		super();
		this.list = list;
		this.myContext = myContext;
	}

	@Override
	public int getCount() {
		return list ==null ? 0:list.size();
	}

	@Override
	public Object getItem(int position) {
		return list ==null ? null : list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getAdapterView(position, convertView, parent);
	}
	
	private View getAdapterView(int position, View convertView, ViewGroup parent){
		View  view = LayoutInflater.from(myContext).inflate(R.layout.questionoverviewresource_listitem, null);
			initInfos(position, view);
		return view;
		
	}
	
	private void initInfos(final int position, View view) {
		ImageView image = (ImageView) view.findViewById(R.id.questionoverview_listitem_image); 
		
		currentFileUrl = list.get(position).getRESOURCESURL();
		currentFileName = list.get(position).getRESOURCESNAME();
		currentFileType = list.get(position).getRESOURCESURL().substring(list.get(position).getRESOURCESURL().lastIndexOf("."),list.get(position).getRESOURCESURL().length()).toLowerCase();
		
		File file = new File(Environment.getExternalStorageDirectory()+"/分级诊疗下载文件/"+currentFileName+currentFileType);
		String path=Environment.getExternalStorageDirectory()+"/分级诊疗下载文件/"+currentFileName+currentFileType;
		image.setImageBitmap(loadBitmap(path,200,200));
	}

	public static Bitmap loadBitmap(String url, int width, int height)
	{
	    Bitmap bitmap = null;
	    if (url == null || !new File(url).exists()) return bitmap;
	    try
	    {
	        BitmapFactory.Options opts = new BitmapFactory.Options();
	        opts.inJustDecodeBounds = true;
	        BitmapFactory.decodeFile(url, opts);
	        opts.inSampleSize = calculateSampleSize(opts, width, height);
	        opts.inJustDecodeBounds = false;
	        opts.inPreferredConfig = Bitmap.Config.RGB_565;
	        opts.inPurgeable = true;
	        opts.inInputShareable = true;
	        bitmap = BitmapFactory.decodeStream(new FileInputStream(url), null, opts);
	        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height);
	        return bitmap;
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	    return bitmap;
	}

	private static int calculateSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight){
	    final int height = options.outHeight;
	    final int width = options.outWidth;
	    int inSampleSize = 1;
	    if (height > reqHeight || width > reqWidth)
	    {
	        final int halfHeight = height / 2;
	        final int halfWidth = width / 2;
		while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth)
		{
			inSampleSize *= 2;
		}
	    }
	    return inSampleSize;
	}
	
	public List<InQueryQuestionResourcesDetailSrvOutputItem> getList() {
		return list;
	}

	public void setList(List<InQueryQuestionResourcesDetailSrvOutputItem> list) {
		this.list = list;
	}

}

/**
 * Copyright (c) 2013 Tianjian, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * Tianjian, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Tianjian.
 */
package com.tianjian.slidingmenuteachingclient.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tianjian.slidingmenuteachingclient.R;
import com.tianjian.slidingmenuteachingclient.application.SystemApplcation;
import com.tianjian.slidingmenuteachingclient.bean.ImportTaskSrv.ImPortTaskSrvResponse;
import com.tianjian.slidingmenuteachingclient.bean.InLoginSrv.InLoginSrvOutputItem;
import com.tianjian.slidingmenuteachingclient.bean.InQueryResourcesSrv.InQueryResourcesSrvOutputItem;
import com.tianjian.slidingmenuteachingclient.common.Constant;
import com.tianjian.slidingmenuteachingclient.upload.MsgCallBack;
import com.tianjian.slidingmenuteachingclient.upload.UploadPostDatHelper;
import com.tianjian.slidingmenuteachingclient.util.ToastUtil;
import com.tianjian.slidingmenuteachingclient.util.network.callback.INetWorkCallBack;
import com.tianjian.slidingmenuteachingclient.util.network.helper.NetWorkHepler;
import com.tianjian.slidingmenuteachingclient.view.CustomerProgress;

import org.ksoap2.serialization.SoapObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 回复提问
 * <p>Title: ReplayQuestionActivity.java</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Tianjian</p>
 * <p>team: TianjianTeam</p>
 * @author: Yehao
 * @date 2016年9月13日上午10:53:33
 * @version 1.0
 * 
 */
public class ReplayQuestionActivity extends BaseActivity{
	private final static int REQUEST_STU_CODE = 2;
	private final static int REQUEST_PICTURE_CODE = 3;
	private final static int REQUEST_DOC_CODE = 4;
	private final static int REQUEST_MP4_CODE = 5;
	private SystemApplcation applcation;
	private InLoginSrvOutputItem userDict;
	private ImageButton button_back;
	private EditText content_text;
	private Button upload_mp4,upload_word,upload_pic,button_save,upload_mic;
	private List<InQueryResourcesSrvOutputItem>  resourcesList;
	String selectedStu="";
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMddHHmmss");
	private LinearLayout contentLayout;
	private ScrollView scrollView;
	private CustomerProgress customerProgress;
	private String currentTime = "";
	private MediaRecorder recorder;
	private long startTimeSec,endTimeSec,currentVedioLength;
	private boolean isrecording = false;//是否正在录音
	private String currentFilePath;
	private String currentFileName;
	String recordfileName = "";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.replayquestion_layout);
		
		applcation = (SystemApplcation)getApplication();
		applcation.addActivity(this);
		userDict = applcation.getUserDict();
		applcation.setSelected_templist(null);
		resourcesList = new ArrayList<InQueryResourcesSrvOutputItem>();
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		
		initView();
	}

	private void initView() {
		//回退按钮
		button_back = (ImageButton) findViewById(R.id.replayquestion_button_back);
		button_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		content_text = (EditText) findViewById(R.id.replayquestion_edittext);
		
		upload_mp4 = (Button) findViewById(R.id.replayquestion_upload_mp4);
		upload_mp4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				intent.setType("*/*");
				intent.addCategory(Intent.CATEGORY_OPENABLE);
				try {
					startActivityForResult(intent, REQUEST_MP4_CODE);
				} catch (android.content.ActivityNotFoundException ex) {
					Toast.makeText(ReplayQuestionActivity.this, "请安装文件管理器", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		upload_word = (Button) findViewById(R.id.replayquestion_upload_word);
		upload_word.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				intent.setType("*/*");
				intent.addCategory(Intent.CATEGORY_OPENABLE);
				try {
					startActivityForResult(intent, REQUEST_DOC_CODE);
				} catch (android.content.ActivityNotFoundException ex) {
					Toast.makeText(ReplayQuestionActivity.this, "请安装文件管理器", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		
		upload_pic = (Button) findViewById(R.id.replayquestion_upload_pic);
		upload_pic.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_PICK);
				startActivityForResult(intent, REQUEST_PICTURE_CODE);
			}
		});
		
		button_save = (Button) findViewById(R.id.replayquestion_save);
		button_save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!content_text.getText().toString().equals("")){
					customerProgress =  new CustomerProgress(ReplayQuestionActivity.this,com.tianjian.slidingmenuteachingclient.R.style.customer_dialog);
					customerProgress.show();
					Gson gson = new Gson();
					HashMap<String, Object> hashMap = new HashMap<String, Object>();
					hashMap.put("OPRATETYPE", "2");
					hashMap.put("USERNAME", userDict.getUSERNAME());
					hashMap.put("NAME", userDict.getNAME());
					hashMap.put("TYPE", userDict.getTYPE());
					hashMap.put("QUESTION_ID", getIntent().getStringExtra("questionid"));
					hashMap.put("CONTENT", content_text.getText().toString());
					hashMap.put("LIST_RESOURCES", gson.toJson(resourcesList));
					saveData(hashMap);
				}else{
					ToastUtil.showToast(ReplayQuestionActivity.this, "回复内容不能为空！");
				}
			}
		});
		
		upload_mic = (Button) findViewById(R.id.replayquestion_upload_mic);
//		upload_mic.setOnLongClickListener(new OnLongClickListener() {
//			@Override
//			public boolean onLongClick(View v) {
//				isrecording = true;
//				try {
//					recorder.prepare();
//					recorder.start();
//					startTimeSec = System.currentTimeMillis();
//				} catch (Exception e) {
//					e.printStackTrace();
//				} 
//				return false;
//			}
//		});
		
		upload_mic.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					try {
						recorderInit();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}else if(event.getAction() == MotionEvent.ACTION_UP && isrecording){
					endTimeSec = System.currentTimeMillis();
					currentVedioLength = endTimeSec-startTimeSec;
					recorder.stop();
					recorder.reset();
					isrecording = false;
					if((currentVedioLength/1000)<3){
						ToastUtil.showToast(ReplayQuestionActivity.this, "录音时间不能少于三秒！");
					}else{
						RelativeLayout layout;
	      				ImageView iv;
	      				final TextView tv;
	      				Button bt;
	      				LayoutInflater mInflater = (LayoutInflater) ReplayQuestionActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	      				layout = (RelativeLayout) mInflater.inflate(R.layout.resources_item_layout, null);
	      				iv = (ImageView) layout.findViewById(R.id.resources_listitem_image);
	      				tv = (TextView) layout.findViewById(R.id.resources_listitem_name);
	      				bt = (Button) layout.findViewById(R.id.resources_listitem_button);
	      				iv.setBackgroundResource(R.drawable.record_icon);
	      				tv.setText("AMR_"+recordfileName.replace(" ", "").replace(".amr", ""));
	      				bt.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								int index = deleteResources(tv.getText().toString());
								if(index!=-1){
									contentLayout.removeViewAt(index);
								}
							}
						});
	      	 			contentLayout.addView(layout);
	      	 			if(contentLayout.getChildCount()!=0){
	      	 				scrollView.setVisibility(View.VISIBLE);
	      	 			}
						//上传文件
		      			uploadFile(recordfileName,Environment
									.getExternalStorageDirectory()
									+ "/分级诊疗下载文件/"+recordfileName);
					}
				}
				return false;
			}

			/**
			*TODO
			* @Title: recorderInit
			* @return void
			* @throws
			* @author Leipeijie
			*/
			private void recorderInit() throws IOException {
				 recorder = new MediaRecorder();
				 recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
				 recorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
				 recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
				 isrecording = true;
				 
				 recordfileName = formatter2.format(new Date())+".amr";
				 
				 File folder = new File(Environment
							.getExternalStorageDirectory()
							+ "/分级诊疗下载文件/"+recordfileName);
				 
				 File folder2 = new File(Environment
							.getExternalStorageDirectory()
							+ "/分级诊疗下载文件/");
				 if(!folder2.exists()){
					 folder2.mkdir();
				 }
				 
				 if(folder.exists()){
					folder.delete();
					recorder.setOutputFile(Environment
							.getExternalStorageDirectory()
							+ "/分级诊疗下载文件/"+recordfileName);
				 }else{
//					folder.mkdir();
					recorder.setOutputFile(Environment
							.getExternalStorageDirectory()
							+ "/分级诊疗下载文件/"+recordfileName);
				 }
				 
				try {
		            // 准备好开始录音
					recorder.prepare();
					recorder.start();
		            startTimeSec = System.currentTimeMillis();
		        } catch (IllegalStateException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
			}

		});
		
		scrollView = (ScrollView) findViewById(R.id.replayquestion_resources_scrollview);
		
		
		contentLayout = (LinearLayout) findViewById(R.id.replayquestion_resources_content);
		contentLayout.setOrientation(LinearLayout.VERTICAL);
	}
	
	protected void saveData(HashMap<String, Object> hashMap) {
//		final CustomerProgress customerProgress =  new CustomerProgress(ReplayQuestionActivity.this,com.tianjian.R.style.customer_dialog);
		NetWorkHepler.postWsData("importquestionWs", "process", hashMap, new INetWorkCallBack() {
			SoapObject objectResult;
			@Override
			public void onResult(Object result) {
				customerProgress.dismissDialog(customerProgress);
				if(result == null){
					ToastUtil.showToast(ReplayQuestionActivity.this, "保存数据失败！");
				}else if(result instanceof SoapObject) {
					objectResult = (SoapObject) result;
				}else{
					ToastUtil.showToast(ReplayQuestionActivity.this, "服务器连接失败！");
					return;
				}
				ImPortTaskSrvResponse response = new ImPortTaskSrvResponse();
				try {
					for(int i=0;i<objectResult.getPropertyCount();i++){
						response.setProperty(i, objectResult.getProperty(i));
					}
				} catch (Exception e) {
					ToastUtil.showToast(ReplayQuestionActivity.this, "数据出错了，请重试！");
				}
				if("Y".equals(response.getErrorFlag())){
					ToastUtil.showToast(ReplayQuestionActivity.this, "保存成功！");
					setResult(1,  getIntent());
					finish();
				}else{
					ToastUtil.showToast(ReplayQuestionActivity.this, "获取数据失败！");
				}
			}
			
			@Override
			public void onProgressUpdate(Integer[] values) {
				
			}
			
			@Override
			public void onPreExecute() {
//				customerProgress.show();
			}
		});
	}

	@Override
     protected  void onActivityResult(int requestCode, int resultCode, Intent data)  {
         super.onActivityResult(requestCode, resultCode,  data);
         applcation = (SystemApplcation)getApplication();
         if(requestCode == REQUEST_PICTURE_CODE){
        	 String fileSrc = null;
        	 String fileName = "";
        	 if(null!=data){
        		 if ("file".equals(data.getData().getScheme())) {
      				// 有些低版本机型返回的Uri模式为file
      				fileSrc = data.getData().getPath();
     			 } else {
      				// Uri模型为content
      				String[] proj = {MediaStore.Images.Media.DATA};
      				Cursor cursor = this.getContentResolver().query(data.getData(), proj,
      						null, null, null);
      				cursor.moveToFirst();
      				int idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
      				fileSrc = cursor.getString(idx);
      				cursor.close();
      			 }
             	// 获取返回数据
      			Bitmap bmp = BitmapFactory.decodeFile(fileSrc);
      			
      			final RelativeLayout layout;
  				ImageView iv;
  				final TextView tv;
  				Button bt;
  				LayoutInflater mInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  				layout = (RelativeLayout) mInflater.inflate(R.layout.resources_item_layout, null);
  				iv = (ImageView) layout.findViewById(R.id.resources_listitem_image);
  				tv = (TextView) layout.findViewById(R.id.resources_listitem_name);
  				bt = (Button) layout.findViewById(R.id.resources_listitem_button);
  				iv.setImageBitmap(bmp);
  				currentTime = formatter2.format(new Date());
  				tv.setText("JPG_"+currentTime);
  				bt.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						int index = deleteResources(tv.getText().toString());
						if(index!=-1){
							contentLayout.removeViewAt(index);
						}
					}
				});
      			contentLayout.addView(layout);
      			if(contentLayout.getChildCount()!=0){
      				scrollView.setVisibility(View.VISIBLE);
      			}
      			
      			//上传文件
      			fileName = File.separator+formatter2.format(new Date())+".jpg";
      			uploadFile(fileName,fileSrc);
        	 }
         }else if(requestCode == REQUEST_DOC_CODE){
        	 String fileSrc = null;
        	 if(null!=data){
        		 if ("file".equals(data.getData().getScheme())) {
      				// 有些低版本机型返回的Uri模式为file
      				fileSrc = data.getData().getPath();
     			 } else {
      				// Uri模型为content
      				String[] proj = {MediaStore.Images.Media.DATA};
      				Cursor cursor = this.getContentResolver().query(data.getData(), proj,
      						null, null, null);
      				cursor.moveToFirst();
      				int idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
      				fileSrc = cursor.getString(idx);
      				cursor.close();
      			 }
      			
      			String fileName = "";
      			if(fileSrc.endsWith(".doc") || fileSrc.endsWith(".DOC")){
      				RelativeLayout layout;
      				ImageView iv;
      				final TextView tv;
      				Button bt;
      				LayoutInflater mInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      				layout = (RelativeLayout) mInflater.inflate(R.layout.resources_item_layout, null);
      				iv = (ImageView) layout.findViewById(R.id.resources_listitem_image);
      				tv = (TextView) layout.findViewById(R.id.resources_listitem_name);
      				bt = (Button) layout.findViewById(R.id.resources_listitem_button);
      				iv.setBackgroundResource(R.drawable.word_icon);
      				tv.setText(fileSrc.substring(fileSrc.lastIndexOf(File.separator)).replace(File.separator, "").replace(" ", "").replace(".doc", "").replace(".DOC", ""));
      				bt.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							int index = deleteResources(tv.getText().toString());
							if(index!=-1){
								contentLayout.removeViewAt(index);
							}
						}
					});
      	 			contentLayout.addView(layout);
      	 			if(contentLayout.getChildCount()!=0){
      	 				scrollView.setVisibility(View.VISIBLE);
      	 			}
      				fileName = File.separator+formatter2.format(new Date())+".doc";
      				//上传文件
      	 			uploadFile(fileName,fileSrc);
      			}else if(fileSrc.endsWith(".docx") || fileSrc.endsWith(".DOCX")){
      				RelativeLayout layout;
      				ImageView iv;
      				final TextView tv;
      				Button bt;
      				LayoutInflater mInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      				layout = (RelativeLayout) mInflater.inflate(R.layout.resources_item_layout, null);
      				iv = (ImageView) layout.findViewById(R.id.resources_listitem_image);
      				tv = (TextView) layout.findViewById(R.id.resources_listitem_name);
      				bt = (Button) layout.findViewById(R.id.resources_listitem_button);
      				iv.setBackgroundResource(R.drawable.word_icon);
      				tv.setText(fileSrc.substring(fileSrc.lastIndexOf(File.separator)).replace(File.separator, "").replace(" ", "").replace(".docx", "").replace(".DOCX", ""));
      				bt.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							int index = deleteResources(tv.getText().toString());
							if(index!=-1){
								contentLayout.removeViewAt(index);
							}
						}
					});
      	 			contentLayout.addView(layout);
      	 			if(contentLayout.getChildCount()!=0){
      	 				scrollView.setVisibility(View.VISIBLE);
      	 			}
      				fileName = File.separator+formatter2.format(new Date())+".doc";
      				//上传文件
      	 			uploadFile(fileName,fileSrc);
      			}else if(fileSrc.endsWith(".pdf") || fileSrc.endsWith(".PDF")){
      				RelativeLayout layout;
      				ImageView iv;
      				final TextView tv;
      				Button bt;
      				LayoutInflater mInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      				layout = (RelativeLayout) mInflater.inflate(R.layout.resources_item_layout, null);
      				iv = (ImageView) layout.findViewById(R.id.resources_listitem_image);
      				tv = (TextView) layout.findViewById(R.id.resources_listitem_name);
      				bt = (Button) layout.findViewById(R.id.resources_listitem_button);
      				iv.setBackgroundResource(R.drawable.pdf_icon);
      				tv.setText(fileSrc.substring(fileSrc.lastIndexOf(File.separator)).replace(File.separator, "").replace(" ", "").replace(".pdf", "").replace(".PDF", ""));
      				bt.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							int index = deleteResources(tv.getText().toString());
							if(index!=-1){
								contentLayout.removeViewAt(index);
							}
						}
					});
      	 			contentLayout.addView(layout);
      	 			if(contentLayout.getChildCount()!=0){
      	 				scrollView.setVisibility(View.VISIBLE);
      	 			}
      				fileName = File.separator+formatter2.format(new Date())+".pdf";
      				//上传文件
      	 			uploadFile(fileName,fileSrc);
      			}else if(fileSrc.endsWith(".ppt") || fileSrc.endsWith(".PPT")){
      				RelativeLayout layout;
      				ImageView iv;
      				final TextView tv;
      				Button bt;
      				LayoutInflater mInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      				layout = (RelativeLayout) mInflater.inflate(R.layout.resources_item_layout, null);
      				iv = (ImageView) layout.findViewById(R.id.resources_listitem_image);
      				tv = (TextView) layout.findViewById(R.id.resources_listitem_name);
      				bt = (Button) layout.findViewById(R.id.resources_listitem_button);
      				iv.setBackgroundResource(R.drawable.ppt_icon);
      				tv.setText(fileSrc.substring(fileSrc.lastIndexOf(File.separator)).replace(File.separator, "").replace(" ", "").replace(".ppt", "").replace(".PPT", ""));
      				bt.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							int index = deleteResources(tv.getText().toString());
							if(index!=-1){
								contentLayout.removeViewAt(index);
							}
						}
					});
      	 			contentLayout.addView(layout);
      	 			if(contentLayout.getChildCount()!=0){
      	 				scrollView.setVisibility(View.VISIBLE);
      	 			}
      				fileName = File.separator+formatter2.format(new Date())+".ppt";
      				//上传文件
      	 			uploadFile(fileName,fileSrc);
      			}else if(fileSrc.endsWith(".pptx") || fileSrc.endsWith(".PPTX")){
      				RelativeLayout layout;
      				ImageView iv;
      				final TextView tv;
      				Button bt;
      				LayoutInflater mInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      				layout = (RelativeLayout) mInflater.inflate(R.layout.resources_item_layout, null);
      				iv = (ImageView) layout.findViewById(R.id.resources_listitem_image);
      				tv = (TextView) layout.findViewById(R.id.resources_listitem_name);
      				bt = (Button) layout.findViewById(R.id.resources_listitem_button);
      				iv.setBackgroundResource(R.drawable.ppt_icon);
      				tv.setText(fileSrc.substring(fileSrc.lastIndexOf(File.separator)).replace(File.separator, "").replace(" ", "").replace(".pptx", "").replace(".PPTX", ""));
      				bt.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							int index = deleteResources(tv.getText().toString());
							if(index!=-1){
								contentLayout.removeViewAt(index);
							}
						}
					});
      	 			contentLayout.addView(layout);
      	 			if(contentLayout.getChildCount()!=0){
      	 				scrollView.setVisibility(View.VISIBLE);
      	 			}
      				fileName = File.separator+formatter2.format(new Date())+".ppt";
      				//上传文件
      	 			uploadFile(fileName,fileSrc);
      			}else{
      				ToastUtil.showToast(ReplayQuestionActivity.this, "请选择WORD、PPT或PDF文档上传!");
      			}
        	 }
         }else if(requestCode == REQUEST_MP4_CODE){
        	 String fileSrc = null;
        	 if(null!=data){
        		 if ("file".equals(data.getData().getScheme())) {
      				// 有些低版本机型返回的Uri模式为file
      				fileSrc = data.getData().getPath();
     			 } else {
      				// Uri模型为content
      				String[] proj = {MediaStore.Images.Media.DATA};
      				Cursor cursor = this.getContentResolver().query(data.getData(), proj,
      						null, null, null);
      				cursor.moveToFirst();
      				int idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
      				fileSrc = cursor.getString(idx);
      				cursor.close();
      			 }
      			
      			String fileName = "";
      			if(fileSrc.endsWith(".mp4") || fileSrc.endsWith(".MP4")){
      				RelativeLayout layout;
      				ImageView iv;
      				final TextView tv;
      				Button bt;
      				LayoutInflater mInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      				layout = (RelativeLayout) mInflater.inflate(R.layout.resources_item_layout, null);
      				iv = (ImageView) layout.findViewById(R.id.resources_listitem_image);
      				tv = (TextView) layout.findViewById(R.id.resources_listitem_name);
      				bt = (Button) layout.findViewById(R.id.resources_listitem_button);
      				iv.setBackgroundResource(R.drawable.mp4_icon);
      				tv.setText(fileSrc.substring(fileSrc.lastIndexOf(File.separator)).replace(File.separator, "").replace(" ", "").replace(".mp4", "").replace(".MP4", ""));
      				bt.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							int index = deleteResources(tv.getText().toString());
							if(index!=-1){
								contentLayout.removeViewAt(index);
							}
						}
					});
      	 			contentLayout.addView(layout);
      	 			if(contentLayout.getChildCount()!=0){
      	 				scrollView.setVisibility(View.VISIBLE);
      	 			}
      				fileName = File.separator+formatter2.format(new Date())+".mp4";
      				//上传文件
      	 			uploadFile(fileName,fileSrc);
      			}else{
      				ToastUtil.showToast(ReplayQuestionActivity.this, "请选择MP4格式视频上传!");
      			}
        	 }
         }
	 }
	
	protected int deleteResources(String fileName) {
		for (int i = 0; i < resourcesList.size(); i++) {
			if(resourcesList.get(i).getRESOURCESNAME().equals(fileName)){
				resourcesList.remove(i);
				return i;
			}
		}
		return -1;
	}

	private void uploadFile(final String fileName,final String filePath) {
		final CustomerProgress customerProgress = new CustomerProgress(this,com.tianjian.slidingmenuteachingclient.R.style.customer_dialog);
		UploadPostDatHelper datHelper = new UploadPostDatHelper(new File(filePath),fileName, new MsgCallBack() {
			
			@Override
			public void onResult(String result) {
			customerProgress.dismiss();
//				ToastUtil.showToast(ReplayQuestionActivity.this, result);
				if("上传成功".equals(result)){
		 			InQueryResourcesSrvOutputItem item = new InQueryResourcesSrvOutputItem();
		 			if(fileName.endsWith(".jpg")){
		 				item.setRESOURCESNAME("JPG_"+currentTime);
		 				item.setRESOURCESURL(Constant.IP_ADDRESS+File.separator+"uploadFiles"+File.separator+"pic"+fileName);
		 				item.setRESOURCESTYPE("5");
		 			}else if(fileName.endsWith(".doc")){
		 				item.setRESOURCESNAME(filePath.substring(filePath.lastIndexOf(File.separator)).replace(File.separator, "").replace(" ", "").replace(".doc", ""));
		 				item.setRESOURCESURL(Constant.IP_ADDRESS+File.separator+"uploadFiles"+File.separator+"doc"+fileName);
		 				item.setRESOURCESTYPE("2");
		 			}else if(fileName.endsWith(".pdf")){
		 				item.setRESOURCESNAME(filePath.substring(filePath.lastIndexOf(File.separator)).replace(File.separator, "").replace(" ", "").replace(".pdf", ""));
		 				item.setRESOURCESURL(Constant.IP_ADDRESS+File.separator+"uploadFiles"+File.separator+"pdf"+fileName);
		 				item.setRESOURCESTYPE("4");
		 			}else if(fileName.endsWith(".mp4")){
		 				item.setRESOURCESNAME(filePath.substring(filePath.lastIndexOf(File.separator)).replace(File.separator, "").replace(" ", "").replace(".mp4", ""));
		 				item.setRESOURCESURL(Constant.IP_ADDRESS+File.separator+"uploadFiles"+File.separator+"mp4"+fileName);
		 				item.setRESOURCESTYPE("1");
		 			}else if(fileName.endsWith(".ppt")){
		 				item.setRESOURCESNAME(filePath.substring(filePath.lastIndexOf(File.separator)).replace(File.separator, "").replace(" ", "").replace(".ppt", ""));
		 				item.setRESOURCESURL(Constant.IP_ADDRESS+File.separator+"uploadFiles"+File.separator+"ppt"+fileName);
		 				item.setRESOURCESTYPE("3");
		 			}else if(fileName.endsWith(".amr")){
		 				item.setRESOURCESNAME("AMR_"+recordfileName.replace(".amr", ""));
		 				item.setRESOURCESURL(Constant.IP_ADDRESS+File.separator+"uploadFiles"+File.separator+"amr"+File.separator+fileName);
		 				item.setRESOURCESTYPE("6");
		 			}
		 			
		 			item.setUPLOADUSERNAME(userDict.getUSERNAME());
		 			item.setUPLOADNAME(userDict.getNAME());
		 			item.setUPLOADTIME(formatter.format(new Date()));
		 			item.setIMAGE(null);
		 			resourcesList.add(item);
				}else{
					contentLayout.removeViewAt(resourcesList.size());
					ToastUtil.showToast(ReplayQuestionActivity.this, "添加附件失败！");
				}
				
			}
			
			@Override
			public void onProgressUpdate(Integer[] values) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPreExecute() {
				customerProgress.show();
				
			}
		}) {
		};
		datHelper.execute();
	}


}

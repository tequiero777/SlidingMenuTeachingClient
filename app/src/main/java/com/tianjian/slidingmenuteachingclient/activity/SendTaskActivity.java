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
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tianjian.slidingmenuteachingclient.R;
import com.tianjian.slidingmenuteachingclient.adapter.MyStudentOrMentorAdapter;
import com.tianjian.slidingmenuteachingclient.application.SystemApplcation;
import com.tianjian.slidingmenuteachingclient.bean.ImportTaskSrv.ImPortTaskSrvResponse;
import com.tianjian.slidingmenuteachingclient.bean.InLoginSrv.InLoginSrvOutputItem;
import com.tianjian.slidingmenuteachingclient.bean.InLoginSrv.InLoginSrvResponse;
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
 * TODO
 * <p>Title: SendTaskActivity.java</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Tianjian</p>
 * <p>team: TianjianTeam</p>
 * @author: Yehao
 * @date 2016年8月26日上午11:24:39
 * @version 1.0
 * 
 */
public class SendTaskActivity extends BaseActivity{
	private final static int REQUEST_STU_CODE = 2;
	private final static int REQUEST_PICTURE_CODE = 3;
	private final static int REQUEST_DOC_CODE = 4;
	private final static int REQUEST_MP4_CODE = 5;
	private SystemApplcation applcation;
	private InLoginSrvOutputItem userDict;
	private ImageButton button_back;
	private TextView selected_stu,choose_stu;
	private EditText content_text;
	private Button upload_mp4,upload_word,upload_pic,button_save,upload_mic;
	private List<InLoginSrvOutputItem>  selected_templist = new ArrayList<InLoginSrvOutputItem>();
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
		setContentView(R.layout.sendtasks_layout);
		
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
		button_back = (ImageButton) findViewById(R.id.sendtask_button_back);
		button_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		selected_stu = (TextView) findViewById(R.id.sendtasks_selected_stu);
		
		choose_stu = (TextView) findViewById(R.id.sendtasks_choose);
		choose_stu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent =  new Intent();
	            intent.setClass(SendTaskActivity.this,  ChooseStuActivity.class);
	            startActivityForResult(intent,REQUEST_STU_CODE);
			}
		});
		
		content_text = (EditText) findViewById(R.id.sendtasks_edittext);
		
		upload_mp4 = (Button) findViewById(R.id.sendtasks_upload_mp4);
		upload_mp4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				intent.setType("*/*");
				intent.addCategory(Intent.CATEGORY_OPENABLE);
				try {
					startActivityForResult(intent, REQUEST_MP4_CODE);
				} catch (android.content.ActivityNotFoundException ex) {
					Toast.makeText(SendTaskActivity.this, "请安装文件管理器", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		upload_word = (Button) findViewById(R.id.sendtasks_upload_word);
		upload_word.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				intent.setType("*/*");
				intent.addCategory(Intent.CATEGORY_OPENABLE);
				try {
					startActivityForResult(intent, REQUEST_DOC_CODE);
				} catch (android.content.ActivityNotFoundException ex) {
					Toast.makeText(SendTaskActivity.this, "请安装文件管理器", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		
		upload_pic = (Button) findViewById(R.id.sendtasks_upload_pic);
		upload_pic.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_PICK);
				startActivityForResult(intent, REQUEST_PICTURE_CODE);
			}
		});
		
		button_save = (Button) findViewById(R.id.sendtasks_save);
		button_save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(selected_templist.size()>0){
					if(!content_text.getText().toString().equals("")){
						customerProgress =  new CustomerProgress(SendTaskActivity.this,com.tianjian.slidingmenuteachingclient.R.style.customer_dialog);
						customerProgress.show();
						Gson gson = new Gson();
						HashMap<String, Object> hashMap = new HashMap<String, Object>();
						hashMap.put("MENTOR_USERNAME", userDict.getUSERNAME());
						hashMap.put("MENTOR_NAME", userDict.getNAME());
						hashMap.put("CONTENT", content_text.getText().toString());
						hashMap.put("NAME", "");
						hashMap.put("LIST_STU", gson.toJson(selected_templist));
						hashMap.put("LIST_RESOURCES", gson.toJson(resourcesList));
						saveData(hashMap);
					}else{
						ToastUtil.showToast(SendTaskActivity.this, "任务内容不能为空！");
					}
				}else{
					ToastUtil.showToast(SendTaskActivity.this, "请先选择学生！");
				}
			}
		});
		
		upload_mic = (Button) findViewById(R.id.sendtasks_upload_mic);
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
						ToastUtil.showToast(SendTaskActivity.this, "录音时间不能少于三秒！");
					}else{
						RelativeLayout layout;
	      				ImageView iv;
	      				final TextView tv;
	      				Button bt;
	      				LayoutInflater mInflater = (LayoutInflater) SendTaskActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

		scrollView = (ScrollView) findViewById(R.id.sendtasks_resources_scrollview);
		
		
		contentLayout = (LinearLayout) findViewById(R.id.sendtasks_resources_content);
		contentLayout.setOrientation(LinearLayout.VERTICAL);
	}
	
	protected void saveData(HashMap<String, Object> hashMap) {
//		final CustomerProgress customerProgress =  new CustomerProgress(SendTaskActivity.this,com.tianjian.R.style.customer_dialog);
		NetWorkHepler.postWsData("importtaskWs", "process", hashMap, new INetWorkCallBack() {
			SoapObject objectResult;
			@Override
			public void onResult(Object result) {
				customerProgress.dismissDialog(customerProgress);
				if(result == null){
					ToastUtil.showToast(SendTaskActivity.this, "保存数据失败！");
				}else if(result instanceof SoapObject) {
					objectResult = (SoapObject) result;
				}else{
					ToastUtil.showToast(SendTaskActivity.this, "服务器连接失败！");
					return;
				}
				ImPortTaskSrvResponse response = new ImPortTaskSrvResponse();
				try {
					for(int i=0;i<objectResult.getPropertyCount();i++){
						response.setProperty(i, objectResult.getProperty(i));
					}
				} catch (Exception e) {
					ToastUtil.showToast(SendTaskActivity.this, "数据出错了，请重试！");
				}
				if("Y".equals(response.getErrorFlag())){
					ToastUtil.showToast(SendTaskActivity.this, "保存成功！");
					setResult(1,  getIntent());
					finish();
				}else{
					ToastUtil.showToast(SendTaskActivity.this, "获取数据失败！");
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
         if(requestCode == REQUEST_STU_CODE){
        	 if(applcation.getSelected_templist()!=null && applcation.getSelected_templist().size()>0){
            	 selected_templist = applcation.getSelected_templist();
            	 selectedStu = "";
            	 for (int i = 0; i < selected_templist.size(); i++) {
            		 selectedStu += selected_templist.get(i).getNAME()+" ";
      		   	 }
            	 selected_stu.setText(selectedStu);
             }else{
            	 selected_stu.setText("");
             }
         }else if(requestCode == REQUEST_PICTURE_CODE){
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
      				ToastUtil.showToast(SendTaskActivity.this, "请选择WORD、PPT或PDF文档上传!");
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
      				ToastUtil.showToast(SendTaskActivity.this, "请选择MP4格式视频上传!");
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
//				ToastUtil.showToast(SendTaskActivity.this, result);
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
					ToastUtil.showToast(SendTaskActivity.this, "添加附件失败！");
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

	/**
     * TODO
     * <p>Title: MyStuOrMentorActivity.java</p>
     * <p>Copyright: Copyright (c) 2013</p>
     * <p>Company: Tianjian</p>
     * <p>team: TianjianTeam</p>
     * @author: Yehao
     * @date 2016年8月23日上午9:13:39
     * @version 1.0
     *
     */
    public static class MyStuOrMentorActivity extends BaseActivity {
        private SystemApplcation applcation;
        private InLoginSrvOutputItem userDict;
        private ImageButton button_back;
        private TextView title;
        private ListView listview;
        private int usertype;
        private MyStudentOrMentorAdapter adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.mystuormentor_layout);

            applcation = (SystemApplcation)getApplication();
            applcation.addActivity(this);
            userDict = applcation.getUserDict();
            Intent intent = getIntent();
            usertype = intent.getIntExtra("usertype", 0);

            initView();
        }

        private void initView() {
            //回退按钮
            button_back = (ImageButton) findViewById(R.id.mystuormentor_back);
            button_back.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            title = (TextView) findViewById(R.id.mystuormentor_title);
            if(usertype == 1){
                title.setText("我的导师");
            }else if(usertype == 2){
                title.setText("我的学生");
            }

            listview = (ListView) findViewById(R.id.mystuormentor_list);
            AnimationSet set = new AnimationSet(false);
            Animation animation = new TranslateAnimation(-50f, 0f, 0f, 0f);
            animation.setDuration(500);
            set.addAnimation(animation);
            LayoutAnimationController controller = new LayoutAnimationController(set, 1);
            listview.setLayoutAnimation(controller);

            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("USERNAME", userDict.getUSERNAME());
            if(userDict.getTYPE().equals("1")){
                hashMap.put("OPERATE_TYPE", "4");
            }else{
                hashMap.put("OPERATE_TYPE", "5");
            }

            queryData(hashMap);
        }

        private void queryData(HashMap<String, Object> hashMap) {
            final CustomerProgress customerProgress =  new CustomerProgress(MyStuOrMentorActivity.this,com.tianjian.slidingmenuteachingclient.R.style.customer_dialog);
            NetWorkHepler.postWsData("loginWs", "process", hashMap, new INetWorkCallBack() {
                SoapObject objectResult;
                @Override
                public void onResult(Object result) {
                    customerProgress.dismissDialog(customerProgress);
                    if(result == null){
                        ToastUtil.showToast(MyStuOrMentorActivity.this, "查询数据失败！");
                    }else if(result instanceof SoapObject) {
                        objectResult = (SoapObject) result;
                    }else{
                        ToastUtil.showToast(MyStuOrMentorActivity.this, "服务器连接失败！");
                        return;
                    }
                    InLoginSrvResponse response = new InLoginSrvResponse();
                    try {
                        for(int i=0;i<objectResult.getPropertyCount();i++){
                            response.setProperty(i, objectResult.getProperty(i));
                        }
                    } catch (Exception e) {
                        ToastUtil.showToast(MyStuOrMentorActivity.this, "数据出错了，请重试！");
                    }
                    if(null!=response.getInLoginSrvOutputCollection() && null!=response.getInLoginSrvOutputCollection().getInLoginSrvOutputItem()){
                        List<InLoginSrvOutputItem> list = response.getInLoginSrvOutputCollection().getInLoginSrvOutputItem();
                        if("Y".equals(response.getErrorFlag())){
                            if(list.size()>0){
                                initListView(list);
                            }
                        }else{
                            ToastUtil.showToast(MyStuOrMentorActivity.this, "获取数据失败！");
                        }
                    }
                }

                @Override
                public void onProgressUpdate(Integer[] values) {

                }

                @Override
                public void onPreExecute() {
                    customerProgress.show();
                }
            });
        }

        protected void initListView(List<InLoginSrvOutputItem> list) {
            if(adapter == null){
                adapter = new MyStudentOrMentorAdapter(list, MyStuOrMentorActivity.this);
                listview.setAdapter(adapter);
            }else{
                adapter.setUsers(list);
                adapter.notifyDataSetChanged();
            }

        }
    }
}

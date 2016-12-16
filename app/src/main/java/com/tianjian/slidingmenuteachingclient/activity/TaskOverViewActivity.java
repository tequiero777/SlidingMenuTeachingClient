/**
 * Copyright (c) 2013 Tianjian, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * Tianjian, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Tianjian.
 */
package com.tianjian.slidingmenuteachingclient.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tianjian.slidingmenuteachingclient.R;
import com.tianjian.slidingmenuteachingclient.adapter.TaskOverViewAdapter;
import com.tianjian.slidingmenuteachingclient.application.SystemApplcation;
import com.tianjian.slidingmenuteachingclient.bean.InQueryTasksSrv.InQueryTaskResourcesDetailSrvOutputItem;
import com.tianjian.slidingmenuteachingclient.bean.InQueryTasksSrv.InQueryTaskSrvOutputItem;
import com.tianjian.slidingmenuteachingclient.util.ToastUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * 任务查看详细页面
 * <p>Title: TaskOverViewActivity.java</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Tianjian</p>
 * <p>team: TianjianTeam</p>
 * @author: Yehao
 * @date 2016年8月31日下午4:37:43
 * @version 1.0
 * 
 */
public class TaskOverViewActivity extends BaseActivity{
	private SystemApplcation applcation;
	private ImageButton button_back;
	private TextView tasktext,tasktime;
	private InQueryTaskSrvOutputItem item;
	private TaskOverViewAdapter adapter;
	private ListView listview;
	private ProgressBar proBar;
	private int progress;  
	private String currentFileName="";
	private String currentFileType="";
	private String currentFileUrl="";
	protected String saveDir;
	private Boolean canceled = false;
	private static final int UPDATE_CHECKCOMPLETED = 1;//已经完成检测新版本
	private static final int UPDATE_DOWNLOADING = 2; //下载中
	private static final int UPDATE_DOWNLOAD_ERROR = 3; //下载出错
	private static final int UPDATE_DOWNLOAD_COMPLETED = 4; //下载完成
	private static final int UPDATE_DOWNLOAD_CANCELED = 5;//取消下载
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.taskoverview_layout);
		
		applcation = (SystemApplcation)getApplication();
		applcation.addActivity(this);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		
		Gson gson = new Gson();
		item = gson.fromJson(getIntent().getStringExtra("list"),new TypeToken<InQueryTaskSrvOutputItem>(){}.getType());
		
		initView();
	}

	private void initView() {
		//回退按钮
		button_back = (ImageButton) findViewById(R.id.taskoverview_button_back);
		button_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		tasktext = (TextView) findViewById(R.id.taskoverview_tasktext);
		tasktime = (TextView) findViewById(R.id.taskoverview_tasktime);
		tasktext.setText(item.getCONTENT());
		tasktime.setText(item.getTIME().replace(".0", ""));
		
		proBar = (ProgressBar) findViewById(R.id.taskoverview_probar);
		proBar.setVisibility(View.GONE);
		
		listview = (ListView) findViewById(R.id.taskoverview_list);
		AnimationSet set = new AnimationSet(false);  
		Animation animation = new TranslateAnimation(-50f, 0f, 0f, 0f);
		animation.setDuration(500);
		set.addAnimation(animation);
		LayoutAnimationController controller = new LayoutAnimationController(set, 1);  
		listview.setLayoutAnimation(controller); 
		
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				TextView text = (TextView) view.findViewById(R.id.taskoverview_listitem_button);
				
				if(proBar.getVisibility() == View.VISIBLE && !text.getText().equals("查看")){
					ToastUtil.showToast(TaskOverViewActivity.this, "请稍后...");
					return;
				}
				
				currentFileUrl = item.getRESOURCESDETAILLINE().getInQueryTaskResourcesDetailSrvOutputItem().get(position).getRESOURCESURL();
				currentFileName = item.getRESOURCESDETAILLINE().getInQueryTaskResourcesDetailSrvOutputItem().get(position).getRESOURCESNAME();
				currentFileType = item.getRESOURCESDETAILLINE().getInQueryTaskResourcesDetailSrvOutputItem().get(position).getRESOURCESURL().substring(item.getRESOURCESDETAILLINE().getInQueryTaskResourcesDetailSrvOutputItem().get(position).getRESOURCESURL().lastIndexOf("."),item.getRESOURCESDETAILLINE().getInQueryTaskResourcesDetailSrvOutputItem().get(position).getRESOURCESURL().length()).toLowerCase();
				
				File file = new File(Environment.getExternalStorageDirectory()+"/分级诊疗下载文件/"+currentFileName+currentFileType);
                if(!file.exists()){
                	text.setText("正在下载");
                	download(currentFileUrl,currentFileName,currentFileType);
                	proBar.setVisibility(View.VISIBLE);
                    proBar.setMax(100);
                    proBar.setProgress(0);
                }else{
                	if(currentFileType.equals(".amr")){
						MediaPlayer mp=new MediaPlayer();
						try {
							mp.setDataSource(Environment
									.getExternalStorageDirectory()
									+ "/分级诊疗下载文件/"
									+ currentFileName + currentFileType);
							mp.prepare();
							mp.start();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (SecurityException e) {
							e.printStackTrace();
						} catch (IllegalStateException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}else{
						try {
							Intent intent = openFile(Environment
									.getExternalStorageDirectory()
									+ "/分级诊疗下载文件/"
									+ currentFileName + currentFileType);
							startActivity(intent);
						} catch (Exception e) {
							ToastUtil.showToast(TaskOverViewActivity.this, "打开文件失败，请安装WPS文档浏览工具！");
							
//							Builder builder = new Builder(TaskOverViewActivity.this);
//							builder.setTitle("下载提示");
//							builder.setMessage("是否下载WPS文档浏览工具？");
//							builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//								@Override
//								public void onClick(DialogInterface dialog, int which) {
//									currentFileUrl = "http://ftp-apk.pconline.com.cn/2ddc67528783efdb6407e53c8fdc0e7e/pub/download/201010/pconline1472209894159.apk";								
//									currentFileName = "WPS Office";
//									currentFileType = "apk";
//											
//									download(currentFileUrl,currentFileName,currentFileType);
//				                	proBar.setVisibility(View.VISIBLE);
//				                    proBar.setMax(100);
//				                    proBar.setProgress(0);
//								}
//							});
//							builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//								@Override
//								public void onClick(DialogInterface dialog, int which) {
//									dialog.dismiss();
//								}
//							});
//							builder.show();
						}
					}
                	
                }
			}
		});
		
		if(null!=item.getRESOURCESDETAILLINE()){
			if(null!=item.getRESOURCESDETAILLINE().getInQueryTaskResourcesDetailSrvOutputItem() && item.getRESOURCESDETAILLINE().getInQueryTaskResourcesDetailSrvOutputItem().size()>0){
				initListView(item.getRESOURCESDETAILLINE().getInQueryTaskResourcesDetailSrvOutputItem());
			}
		}
	}

	private void initListView(
			List<InQueryTaskResourcesDetailSrvOutputItem> inQueryTaskResourcesDetailSrvOutputItem) {
		if(adapter == null){
			adapter = new TaskOverViewAdapter(inQueryTaskResourcesDetailSrvOutputItem, TaskOverViewActivity.this);
			listview.setAdapter(adapter);
		}else{
			adapter.setList(inQueryTaskResourcesDetailSrvOutputItem);
			adapter.notifyDataSetChanged();
		}
	}

	// 下载
	public void download(final String filePath, final String fileName, final String fileType){
		new Thread() {			
			@Override  
		        public void run() {  
		            try {  
		                URL url = new URL(filePath);  
		                //新建连接并获取资源
		                HttpURLConnection conn = (HttpURLConnection)url.openConnection(); 
		                conn.setConnectTimeout(5000);
		                conn.setRequestMethod("POST");
		                conn.setRequestProperty("Accept-Encoding", "identity"); 
		                conn.connect();  
		                int length = conn.getContentLength();
		                
		                InputStream is = conn.getInputStream();  
		                
		                File file = new File(Environment.getExternalStorageDirectory()+"/分级诊疗下载文件/");
		                if(!file.exists()){
		                	file.mkdirs();
		                }
		                saveDir = Environment.getExternalStorageDirectory() + "/分级诊疗下载文件/";
		                
		                //如果文件存在，就先删除
		                File sdfile = new File(saveDir,fileName+fileType);  		                
		                if(sdfile.exists())
		                {
		                	sdfile.delete();
		                }
		                
		                FileOutputStream fos = new FileOutputStream(sdfile);  
		                 
		                int count = 0;  
		                byte buf[] = new byte[512];  
		                  
		                do{  
		                    int numread = is.read(buf);  
		                    count += numread;  
		                    //计算下载的进度
		                    progress =(int)(((float)count / length) * 100);  
		                   //把进度传给updateHandler更新ProgressDialog,从消息池中拿来一个msg 不需要另开辟空间
		                    updateHandler.sendMessage(updateHandler.obtainMessage(UPDATE_DOWNLOADING)); 
		                    if(numread <= 0){          
		                    	updateHandler.sendEmptyMessage(UPDATE_DOWNLOAD_COMPLETED);
		                        break;  
		                    }  
		                    fos.write(buf,0,numread);  
		                }while(!canceled);  
			                fos.close();  
			                is.close();  
		            } catch (MalformedURLException e) {  
		                e.printStackTrace(); 
		                updateHandler.sendMessage(updateHandler.obtainMessage(UPDATE_DOWNLOAD_ERROR,e.getMessage()));
		            } catch(IOException e){  
		                e.printStackTrace();  
		                updateHandler.sendMessage(updateHandler.obtainMessage(UPDATE_DOWNLOAD_ERROR,e.getMessage()));
		            }  
		              
		        } 
		}.start();
	}
	
	Handler updateHandler = new Handler() 
	{
		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case UPDATE_DOWNLOADING:
				//更新ProgressDialog的进度
				downloadProgressChanged(progress);
				break;
			case UPDATE_DOWNLOAD_ERROR:
				//下载失败
				downloadCompleted(false, msg.obj!=null?msg.obj.toString():"");
				break;
			case UPDATE_DOWNLOAD_COMPLETED:
				//下载完成
				downloadCompleted(true, "");
				break;
			default:
				break;
			}
		}
	};
	
	//更新ProgressDialog的进度
	public void downloadProgressChanged(int progress) {
		if (proBar != null) {
			proBar.setProgress(progress);
		}

	}
	
	//下载完成
	public void downloadCompleted(Boolean sucess, String errorMsg) {
		if (proBar != null) {
			proBar.setVisibility(View.GONE);
			proBar.setProgress(0);
		}
		if (sucess) {
			if(currentFileType.equals("apk")){
				installApk();
			}else{
				adapter.setList(item.getRESOURCESDETAILLINE().getInQueryTaskResourcesDetailSrvOutputItem());
				adapter.notifyDataSetChanged();
				ToastUtil.showToast(TaskOverViewActivity.this, "下载完成！");
				/*try {
					Intent intent = openFile(saveDir + "/" + currentFileName + currentFileType);
					startActivity(intent);
				} catch (Exception e) {
					ToastUtil.showToast(TaskOverViewActivity.this, "打开文件失败，请安装WPS文档浏览工具！");
				}*/
			}
		} else {
			proBar.setVisibility(View.GONE);
			proBar.setProgress(0);
			adapter.notifyDataSetChanged();
			ToastUtil.showToast(TaskOverViewActivity.this, "下载失败！");
		}
	}
		
	//提示安装
	public void installApk() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		
		intent.setDataAndType(
				Uri.fromFile(new File(saveDir, "WPS Office.apk")),
				"application/vnd.android.package-archive");
		TaskOverViewActivity.this.startActivityForResult(intent, 6);
	}
		
	public static Intent openFile(String filePath){ 
        /* 取得扩展名 */  
        String end=filePath.substring(filePath.lastIndexOf(".") + 1,filePath.length()).toLowerCase();   
        if(end.equals("mp4")){  
            return getVideoFileIntent(filePath);  
        }else if(end.equals("jpg")){  
            return getImageFileIntent(filePath);  
        }else if(end.equals("ppt")|| end.equals("pptx")){  
            return getPptFileIntent(filePath);  
        }else if(end.equals("doc")|| end.equals("docx")){  
            return getWordFileIntent(filePath);  
        }else if(end.equals("pdf")){  
            return getPdfFileIntent(filePath);  
        }else{
        	return getAllIntent(filePath);
        }
	}
	
	public static Intent getAllIntent( String param ) {  
		  
        Intent intent = new Intent();    
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);    
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(new File(param ));  
        intent.setDataAndType(uri,"*/*");   
        return intent;  
    }  
	
	public static Intent getVideoFileIntent( String param ) {  
		  
        Intent intent = new Intent("android.intent.action.VIEW");  
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
        intent.putExtra("oneshot", 0);  
        intent.putExtra("configchange", 0);  
        Uri uri = Uri.fromFile(new File(param ));  
        intent.setDataAndType(uri, "video/*");  
        return intent;  
    }  
	
	public static Intent getImageFileIntent( String param ) {  
		  
        Intent intent = new Intent("android.intent.action.VIEW");  
        intent.addCategory("android.intent.category.DEFAULT");  
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
        Uri uri = Uri.fromFile(new File(param ));  
        intent.setDataAndType(uri, "image/*");  
        return intent;  
    }  
	
	public static Intent getPptFileIntent( String param ){    
		  
        Intent intent = new Intent("android.intent.action.VIEW");     
        intent.addCategory("android.intent.category.DEFAULT");     
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);     
        Uri uri = Uri.fromFile(new File(param ));     
        intent.setDataAndType(uri, "application/vnd.ms-powerpoint");     
        return intent;     
    }     
	
	public static Intent getWordFileIntent( String param ){    
		  
        Intent intent = new Intent("android.intent.action.VIEW");     
        intent.addCategory("android.intent.category.DEFAULT");     
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);     
        Uri uri = Uri.fromFile(new File(param ));     
        intent.setDataAndType(uri, "application/msword");     
        return intent;     
    }     
	
	public static Intent getPdfFileIntent( String param ){     
		  
        Intent intent = new Intent("android.intent.action.VIEW");     
        intent.addCategory("android.intent.category.DEFAULT");     
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);     
        Uri uri = Uri.fromFile(new File(param ));     
        intent.setDataAndType(uri, "application/pdf");     
        return intent;     
    }  
}

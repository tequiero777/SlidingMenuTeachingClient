package com.tianjian.slidingmenuteachingclient.service;

/**
 * Copyright (c) 2013 Tianjian, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * Tianjian, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Tianjian.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.tianjian.slidingmenuteachingclient.R;
import com.tianjian.slidingmenuteachingclient.util.DownloadFileUtils;
import com.tianjian.slidingmenuteachingclient.util.DownloadFileUtils.DownloadFileCallback;
import com.tianjian.slidingmenuteachingclient.util.ToastUtil;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 下载服务
 * <p>
 * Title: DownLoatFileService.java
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
 * @date 2014-4-30上午10:36:11
 * @version 1.0
 * 
 */
public class DownloadFileService extends Service implements Callback{
	// TODO apk存放网络地址
	private String url = ""; // apk下载地址
	private String apkName = "TeachingClient.apk";
	private DownloadFileUtils downloadFileUtils;// 文件下载工具类
	private String filePath;// 保存在本地的路径
	private NotificationManager notificationManager;// 状态栏通知管理类
	private Notification notification;// 状态栏通知
	private RemoteViews remoteViews;// 状态栏通知显示的view
	private final int notificationID = 1;// 通知的id
	private final int updateProgress = 1;// 更新状态栏的下载进度
	private final int downloadSuccess = 2;// 下载成功
	private final int downloadError = 3;// 下载失败
	private final String TAG = "DownloadFileService";
	private Timer timer;// 定时器，用于更新下载进度
	private TimerTask task;// 定时器执行的任务
	private Handler handler;
	@Override
	public IBinder onBind(Intent intent) {

		return null;
	}

	@Override
	public void onCreate() {
		handler = new Handler(this);
		//init();
	}

	private void init() {
		filePath = Environment.getExternalStorageDirectory() + "/apk";
		Log.e(filePath, filePath);
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notification = new Notification();
		notification.icon = R.drawable.ic_launcher;// 设置通知消息的图标
		notification.tickerText = "正在下载...";// 设置通知消息的标题
		remoteViews = new RemoteViews(getPackageName(),
				R.layout.basic_down_notification);
		remoteViews.setImageViewResource(R.id.IconIV, R.drawable.ic_launcher);

		timer = new Timer();
		task = new TimerTask() {

			@Override
			public void run() {
				handler.sendEmptyMessage(updateProgress);
			}
		};

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if(intent==null){
			return super.onStartCommand(intent, flags, startId);
		}
		url = intent.getExtras().getString("url");
		Log.e("url", url);
		apkName = intent.getExtras().getString("appName")+".apk";
		Log.e("dfds", apkName);
		new Thread(new Runnable() {

			@Override
			public void run() {
				if(downloadFileUtils==null){
					downloadFileUtils = new DownloadFileUtils(url, filePath,
							apkName, 1, callback);
					downloadFileUtils.downloadFile();
					Log.e(TAG, "下载线程开始");
				}else{
					ToastUtil.showToast(getApplicationContext(), "下载任务已启动");
				}
			}
		}).start();
		init();
		timer.schedule(task, 0, 1000);
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		Log.i(TAG, TAG + " is onDestory...");
		super.onDestroy();
	}

	
	/**
	 * 下载回调
	 */
	DownloadFileCallback callback = new DownloadFileCallback() {

		@Override
		public void downloadSuccess(Object obj) {
			execCommand();
			handler.sendEmptyMessage(downloadSuccess);

		}

		@Override
		public void downloadError(Exception e, String msg) {
			handler.sendEmptyMessage(downloadError);
		}
	};

	/** 下载完成之后 的自动安装方法 */
	public void execCommand() {
		String path = filePath + "/" + apkName;
		// third
		Intent i = new Intent(Intent.ACTION_VIEW);
		Log.e("file", path);
		i.setDataAndType(Uri.fromFile(new File(path)),
				"application/vnd.android.package-archive");
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		this.startActivity(i);
	}

	public boolean handleMessage(Message msg) {

		Log.e("MSG.what", msg.what + "");
		if (msg.what == updateProgress) {// 更新下载进度
			long fileSize = downloadFileUtils.getFileSize();
			long totalReadSize = downloadFileUtils.getTotalReadSize();
			Log.e("totalReadSize", totalReadSize + "");
			if (totalReadSize >= 0) {
				float size = (float) totalReadSize * 100 / (float) fileSize;
				DecimalFormat format = new DecimalFormat("0.00");
				String progress = format.format(size);
				remoteViews.setTextViewText(R.id.progressTv, "已下载"
						+ progress + "%");
				remoteViews.setProgressBar(R.id.progressBar, 100,
						(int) size, false);
				notification.contentView = remoteViews;
				notification.flags |= Notification.FLAG_AUTO_CANCEL;
				notification.when = System.currentTimeMillis();
				// 点击操作
				// Intent notificationIntent =new
				// Intent(DownloadFileService.this, DownLoadActivity.class);
				// PendingIntent contentItent =
				// PendingIntent.getActivity(DownloadFileService.this, 0,
				// notificationIntent, 0);
				// notification.setLatestEventInfo(DownloadFileService.this,
				// "", "", contentItent);
				notificationManager.notify(notificationID, notification);
			}

		} else if (msg.what == downloadSuccess) {// 下载完成
			remoteViews.setTextViewText(R.id.progressTv, "下载完成");
			remoteViews.setProgressBar(R.id.progressBar, 100, 100, false);
			Toast.makeText(DownloadFileService.this, "下载完成",
					Toast.LENGTH_LONG).show();
			notification.contentView = remoteViews;
			notificationManager.notify(notificationID, notification);
			if (timer != null && task != null) {
				timer.cancel();
				task.cancel();
			}
			stopService(new Intent(getApplicationContext(),
					DownloadFileService.class));// stop service
		} else if (msg.what == downloadError) {// 下载失败
			if (timer != null && task != null) {
				timer.cancel();
				task.cancel();
			}
			Toast.makeText(DownloadFileService.this, "下载失败",
					Toast.LENGTH_LONG).show();
			notificationManager.cancel(notificationID);
			stopService(new Intent(getApplicationContext(),
					DownloadFileService.class));// stop service
		}
	
		return false;
	}
	

}

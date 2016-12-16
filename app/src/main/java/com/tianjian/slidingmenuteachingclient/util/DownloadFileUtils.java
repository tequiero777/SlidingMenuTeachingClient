/**
 * Copyright (c) 2013 Tianjian, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * Tianjian, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Tianjian.
 */
package com.tianjian.slidingmenuteachingclient.util;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * TODO
 * <p>
 * Title: sdf.java
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
 * @date 2014-4-30上午10:39:18
 * @version 1.0
 * 
 */
public class DownloadFileUtils {
    private final String TAG = "DownloadFileUtils";
    private String url;//下载地址
    private long fileSize;//下载的文件大小
    private long totalReadSize;//已读取的文件大小
    private long block;//每条线程下载的长度
    private int threadCount;//下载的线程数
    private final int threadPoolNum = 5;//线程池的大小
    private final int bufferSize = 1024 * 100;//缓冲区大小
    private String fileName;//存储在本地的文件名称
    private String filePath;//存储的路径
    private HttpURLConnection urlConnection;
    private RandomAccessFile randomAccessFile;//根据指定位置写入数据
    private URL uri;
    private DownloadFileCallback callback;//下载的回调接口
    private ExecutorService executorService;//固定大小的线程池
    private volatile boolean error = false;//全局变量，使用volatile同步，下载产生异常时改变

    public DownloadFileUtils(String url, String filePath, String fileName, int threadCount, DownloadFileCallback callback){
            this.url = url;
            this.filePath = filePath;
            this.fileName = fileName;
            this.threadCount = threadCount;
            this.callback = callback;
    }
   
    public long getFileSize() {
            return fileSize;
    }
    public long getTotalReadSize() {
            return totalReadSize;
    }
    /**
     * 文件下载
     * @return true 下载成功 false 下载失败
     */
    public boolean downloadFile(){
            try {
                    uri = new URL(url);
                    urlConnection = (HttpURLConnection) uri.openConnection();
                    urlConnection.setRequestMethod("GET");
                    if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                            fileSize = urlConnection.getContentLength();//获取文件的长度
                            block = fileSize / threadCount;//为了避免文件长度缺失每条线程下载长度增加1
                            File file1 = new File(filePath);
                            if(!file1.isDirectory()){
                            	file1.mkdirs();
                            }
                            File file = new File(file1, fileName);
                            executorService = Executors.newFixedThreadPool(threadPoolNum);
                            CountDownLatch countDownLatch = new CountDownLatch(threadCount);//线程计数器
                            for(int i = 0; i < threadCount; i++){
                                    long startPosition = i * block;//每条线程的开始读取位置
                                    long endPosition = (i+1) * block;//每条线程的读取结束位置
                                    randomAccessFile = new RandomAccessFile(file, "rwd");
                                    randomAccessFile.seek(startPosition);
                                    executorService.execute(new DownloadThread(i+1, startPosition, endPosition, randomAccessFile,countDownLatch));
                            }
                            countDownLatch.await();//阻塞线程,直到countDownLatch线程数为零
                            executorService.shutdown();
                            callback.downloadSuccess(null);//下载成功时的回调
                            Log.i(TAG, "下载成功。。。");
                            return true;
                    }
            } catch (Exception e) {
                    callback.downloadError(e, "");//下载失败的回调
                    e.printStackTrace();
                    return false;
            }
            return false;
    }
   
    class DownloadThread implements Runnable{
            private int threadId;
            private long startPosition;
            private long endPosition;
            private RandomAccessFile randomAccessFile;
            private CountDownLatch countDownLatch;
            public DownloadThread(int threadId,long startPosition,long endPosition,RandomAccessFile randomAccessFile,CountDownLatch countDownLatch){
                    this.threadId = threadId;
                    this.startPosition = startPosition;
                    this.endPosition = endPosition;
                    this.randomAccessFile = randomAccessFile;
                    this.countDownLatch = countDownLatch;
            }
            @Override
            public void run() {
                    try {
                            HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
                            connection.setRequestMethod("GET");//以GET方式连接
                            connection.setRequestProperty("Connection", "Keep-Alive");//维持长连接
                            connection.setConnectTimeout(5 * 60 * 1000);//设置连接超时
                            connection.setAllowUserInteraction(true);//允许用户交互
                            connection.setRequestProperty("Range", "bytes="+startPosition+"-"+endPosition);//设置每条线程开始下载的位置
                            InputStream inputStream = new BufferedInputStream(connection.getInputStream(), bufferSize);//使用缓冲区读取文件
                            byte[] b = new byte[bufferSize];
                            int len = 0;
                            Log.e(TAG, "MMMMMM"+inputStream.toString());
                            while(!error && (len = inputStream.read(b)) != -1){
                                    randomAccessFile.write(b,0,len);                           
                                    totalReadSize += len;
                            }
                            if(!error)
                                    Log.d(TAG, "线程"+threadId+"下载完成。。。");
                            else
                                    Log.e(TAG, "线程"+threadId+"下载失败。。。");
                            inputStream.close();
                            randomAccessFile.close();
                            connection.disconnect();
                            countDownLatch.countDown();//每条线程执行完之后减一
                    } catch (Exception e) {
                            Log.e(TAG, "线程"+threadId+"下载失败。。。");
                            error = true;
                            e.printStackTrace();
                            callback.downloadError(e, "");//下载失败的回调
                    }
            }
           
    }
    public interface DownloadFileCallback {
        void downloadSuccess(Object obj);
        void downloadError(Exception e, String msg);
    }
}

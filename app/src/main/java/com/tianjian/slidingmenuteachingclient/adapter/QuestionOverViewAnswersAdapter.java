/**
 * Copyright (c) 2013 Tianjian, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * Tianjian, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Tianjian.
 */
package com.tianjian.slidingmenuteachingclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tianjian.slidingmenuteachingclient.R;
import com.tianjian.slidingmenuteachingclient.bean.InQueryQuestionSrv.InQueryQuestionAnswersDetailSrvOutputItem;
import com.tianjian.slidingmenuteachingclient.util.ToastUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 * <p>Title: QuestionOverViewAnswersAdapter.java</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Tianjian</p>
 * <p>team: TianjianTeam</p>
 * @author: Yehao
 * @date 2016年9月13日上午9:16:44
 * @version 1.0
 * 
 */
public class QuestionOverViewAnswersAdapter extends BaseAdapter{
	private List<InQueryQuestionAnswersDetailSrvOutputItem>  list = new LinkedList<InQueryQuestionAnswersDetailSrvOutputItem>();
	/**context**/
	private Context myContext;
//	private String currentFileName="";
//	private String currentFileType="";
//	private String currentFileUrl="";
//	protected String saveDir;
//	private Boolean canceled = false;
	private static final int UPDATE_CHECKCOMPLETED = 1;//已经完成检测新版本
	private static final int UPDATE_DOWNLOADING = 2; //下载中
	private static final int UPDATE_DOWNLOAD_ERROR = 3; //下载出错
	private static final int UPDATE_DOWNLOAD_COMPLETED = 4; //下载完成
	private static final int UPDATE_DOWNLOAD_CANCELED = 5;//取消下载
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	public QuestionOverViewAnswersAdapter(List<InQueryQuestionAnswersDetailSrvOutputItem>  list,
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
		View  view = LayoutInflater.from(myContext).inflate(R.layout.questionoverviewanswers_listitem, null);
			initInfos(position, view);
		return view;
		
	}
	
	private void initInfos(final int position, View view) {
		TextView text = (TextView) view.findViewById(R.id.questionoverview_answertext);
		LinearLayout resourceslayout = (LinearLayout) view.findViewById(R.id.questionoverview_resources_content);
		TextView time = (TextView) view.findViewById(R.id.questionoverview_answertime);
		
		if(list.get(position).getTYPE().equals("1")){//追问
			String contexttext = list.get(position).getNAME()+"追问："+list.get(position).getANSWERCONTENT();
			int start=0;
	        int end=list.get(position).getNAME().length();
	        SpannableStringBuilder style=new SpannableStringBuilder(contexttext); 
	        style.setSpan(new ForegroundColorSpan(Color.parseColor("#019dd9")),start,end,Spannable.SPAN_EXCLUSIVE_INCLUSIVE); 
	        style.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6600")),end,end+2,Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
	        text.setText(style);
		}else{//导师回复
			String contexttext = list.get(position).getNAME()+"导师："+list.get(position).getANSWERCONTENT();
			int start=0;
	        int end=list.get(position).getNAME().length();
	        SpannableStringBuilder style=new SpannableStringBuilder(contexttext); 
	        style.setSpan(new ForegroundColorSpan(Color.parseColor("#019dd9")),start,end,Spannable.SPAN_EXCLUSIVE_INCLUSIVE); 
	        text.setText(style);
		}
		
		
		resourceslayout.setOrientation(LinearLayout.VERTICAL);
		
		if(null!=list.get(position).getANSWERSRESOURCESDETAILLINE() && null!=list.get(position).getANSWERSRESOURCESDETAILLINE().getInQueryQuestionAnswersResourcesDetailSrvOutputItem()){
			for (int i = 0; i < list.get(position).getANSWERSRESOURCESDETAILLINE().getInQueryQuestionAnswersResourcesDetailSrvOutputItem().size(); i++) {
				ImageView iv;
				final TextView tv;
				final Button bt;
				LayoutInflater mInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				RelativeLayout layout = (RelativeLayout) mInflater.inflate(R.layout.resources_item_layout, null);
				iv = (ImageView) layout.findViewById(R.id.resources_listitem_image);
				tv = (TextView) layout.findViewById(R.id.resources_listitem_name);
				bt = (Button) layout.findViewById(R.id.resources_listitem_button);
				
				if(list.get(position).getANSWERSRESOURCESDETAILLINE().getInQueryQuestionAnswersResourcesDetailSrvOutputItem().get(i).getRESOURCESTYPE().equals("1")){
					iv.setBackgroundResource(R.drawable.mp4_icon);
				}else if(list.get(position).getANSWERSRESOURCESDETAILLINE().getInQueryQuestionAnswersResourcesDetailSrvOutputItem().get(i).getRESOURCESTYPE().equals("2")){
					iv.setBackgroundResource(R.drawable.word_icon);
				}else if(list.get(position).getANSWERSRESOURCESDETAILLINE().getInQueryQuestionAnswersResourcesDetailSrvOutputItem().get(i).getRESOURCESTYPE().equals("3")){
					iv.setBackgroundResource(R.drawable.ppt_icon);
				}else if(list.get(position).getANSWERSRESOURCESDETAILLINE().getInQueryQuestionAnswersResourcesDetailSrvOutputItem().get(i).getRESOURCESTYPE().equals("4")){
					iv.setBackgroundResource(R.drawable.pdf_icon);
				}else if(list.get(position).getANSWERSRESOURCESDETAILLINE().getInQueryQuestionAnswersResourcesDetailSrvOutputItem().get(i).getRESOURCESTYPE().equals("5")){
					iv.setBackgroundResource(R.drawable.pic_icon);
				}else if(list.get(position).getANSWERSRESOURCESDETAILLINE().getInQueryQuestionAnswersResourcesDetailSrvOutputItem().get(i).getRESOURCESTYPE().equals("6")){
					iv.setBackgroundResource(R.drawable.record_icon);
				}
				tv.setText(list.get(position).getANSWERSRESOURCESDETAILLINE().getInQueryQuestionAnswersResourcesDetailSrvOutputItem().get(i).getRESOURCESNAME());
				
				String currentFileUrl = list.get(position).getANSWERSRESOURCESDETAILLINE().getInQueryQuestionAnswersResourcesDetailSrvOutputItem().get(i).getRESOURCESURL();
				final String currentFileName = list.get(position).getANSWERSRESOURCESDETAILLINE().getInQueryQuestionAnswersResourcesDetailSrvOutputItem().get(i).getRESOURCESNAME();
				final String currentFileType = list.get(position).getANSWERSRESOURCESDETAILLINE().getInQueryQuestionAnswersResourcesDetailSrvOutputItem().get(i).getRESOURCESURL().substring(list.get(position).getANSWERSRESOURCESDETAILLINE().getInQueryQuestionAnswersResourcesDetailSrvOutputItem().get(i).getRESOURCESURL().lastIndexOf("."),list.get(position).getANSWERSRESOURCESDETAILLINE().getInQueryQuestionAnswersResourcesDetailSrvOutputItem().get(i).getRESOURCESURL().length()).toLowerCase();
				
				File file = new File(Environment.getExternalStorageDirectory()+"/分级诊疗下载文件/"+currentFileName+currentFileType);
		        if(!file.exists()){
		        	download(currentFileUrl,currentFileName,currentFileType);
		        }
		        
		        bt.setText("查看");
		        bt.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
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
								myContext.startActivity(intent);
							} catch (Exception e) {
								ToastUtil.showToast(myContext, "打开文件失败，请安装WPS文档浏览工具！");
							}
						}
					}
				});
		        
				resourceslayout.addView(layout);
			}
		}
		
		time.setText(list.get(position).getTIME().replace(".0", ""));
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
		                String saveDir = Environment.getExternalStorageDirectory() + "/分级诊疗下载文件/";
		                
		                //如果文件存在，就先删除
		                File sdfile = new File(saveDir,fileName+fileType);  		                
		                if(sdfile.exists())
		                {
		                	sdfile.delete();
		                }
		                
		                Boolean canceled = false;
		                
		                FileOutputStream fos = new FileOutputStream(sdfile);  
		                 
		                int count = 0;  
		                byte buf[] = new byte[512];  
		                  
		                do{  
		                    int numread = is.read(buf);  
		                    count += numread;  
		                    //计算下载的进度
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

	}
	
	//下载完成
	public void downloadCompleted(Boolean sucess, String errorMsg) {
		if (sucess) {
			notifyDataSetChanged();
		} else {
			ToastUtil.showToast(myContext, "下载失败！");
		}
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
	
	public static Intent openFile(String filePath){ 
        /* 取得扩展名 */  
        String end=filePath.substring(filePath.lastIndexOf(".") + 1,filePath.length()).toLowerCase();   
        if(end.equals("mp4")){  
            return getVideoFileIntent(filePath);  
        }else if(end.equals("jpg")){  
            return getImageFileIntent(filePath);  
        }else if(end.equals("ppt")){  
            return getPptFileIntent(filePath);  
        }else if(end.equals("doc")){  
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
	
	public List<InQueryQuestionAnswersDetailSrvOutputItem> getList() {
		return list;
	}

	public void setList(List<InQueryQuestionAnswersDetailSrvOutputItem> list) {
		this.list = list;
	}

}

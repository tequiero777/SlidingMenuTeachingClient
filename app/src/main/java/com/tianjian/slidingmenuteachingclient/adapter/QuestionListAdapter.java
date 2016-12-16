package com.tianjian.slidingmenuteachingclient.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tianjian.slidingmenuteachingclient.R;
import com.tianjian.slidingmenuteachingclient.bean.InQueryQuestionSrv.InQueryQuestionSrvOutputItem;
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
 * <p>Title: QuestionListAdapter.java</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Tianjian</p>
 * <p>team: TianjianTeam</p>
 * @author: Yehao
 * @date 2016年9月7日上午8:52:47
 * @version 1.0
 * 
 */
public class QuestionListAdapter extends BaseAdapter{
	private List<InQueryQuestionSrvOutputItem>  list = new LinkedList<InQueryQuestionSrvOutputItem>();
	/**context**/
	private Context myContext;
	private String currentFileName="";
	private String currentFileType="";
	private String currentFileUrl="";
	ImageView iv;
	LinearLayout imagelayout;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	private static final int UPDATE_CHECKCOMPLETED = 1;//已经完成检测新版本
	private static final int UPDATE_DOWNLOADING = 2; //下载中
	private static final int UPDATE_DOWNLOAD_ERROR = 3; //下载出错
	private static final int UPDATE_DOWNLOAD_COMPLETED = 4; //下载完成
	private static final int UPDATE_DOWNLOAD_CANCELED = 5;//取消下载
	private Boolean canceled = false;
	public QuestionListAdapter(List<InQueryQuestionSrvOutputItem> list,
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
		View  view = LayoutInflater.from(myContext).inflate(R.layout.question_listitem, null);
			initInfos(position, view);
		return view;
		
	}
	
	private void initInfos(final int position, View view) {
		ImageView image = (ImageView) view.findViewById(R.id.question_listitem_imageview); 
		TextView name = (TextView) view.findViewById(R.id.question_listitem_name); 
		TextView content = (TextView) view.findViewById(R.id.question_listitem_content); 
		TextView time = (TextView) view.findViewById(R.id.question_listitem_time); 
		TextView flag = (TextView) view.findViewById(R.id.question_listitem_flag); 
		imagelayout = (LinearLayout) view.findViewById(R.id.question_listitem_imagelayout);
		
		if(null!=list.get(position).getIMAGE()){
			image.setImageBitmap(BitmapFactory.decodeByteArray(list.get(position).getIMAGE(), 0, list.get(position).getIMAGE().length));
		}
		
		name.setText(list.get(position).getNAME());
		time.setText(list.get(position).getTIME().replace(".0", ""));
		
		if(list.get(position).getCLASSTYPE().equals("1")){
			content.setText(list.get(position).getCONTENT());
			flag.setText("常规");
		}else{
			content.setText(list.get(position).getCONTENT().split("！@#-")[0]+"，"+list.get(position).getCONTENT().split("！@#-")[1]+"，"+list.get(position).getCONTENT().split("！@#-")[2]+"岁。"+list.get(position).getCONTENT().split("！@#-")[3]);
			flag.setText("会诊");
		}
		
		if(null!=list.get(position).getRESOURCESDETAILLINE() && list.get(position).getRESOURCESDETAILLINE().getInQueryQuestionResourcesDetailSrvOutputItem().size()>0){
			for (int i = 0; i < list.get(position).getRESOURCESDETAILLINE().getInQueryQuestionResourcesDetailSrvOutputItem().size(); i++) {
				String currentFileUrl = list.get(position).getRESOURCESDETAILLINE().getInQueryQuestionResourcesDetailSrvOutputItem().get(i).getRESOURCESURL();
				String currentFileName = list.get(position).getRESOURCESDETAILLINE().getInQueryQuestionResourcesDetailSrvOutputItem().get(i).getRESOURCESNAME();
				String currentFileType = list.get(position).getRESOURCESDETAILLINE().getInQueryQuestionResourcesDetailSrvOutputItem().get(i).getRESOURCESURL().substring(list.get(position).getRESOURCESDETAILLINE().getInQueryQuestionResourcesDetailSrvOutputItem().get(i).getRESOURCESURL().lastIndexOf("."),list.get(position).getRESOURCESDETAILLINE().getInQueryQuestionResourcesDetailSrvOutputItem().get(i).getRESOURCESURL().length()).toLowerCase();
				
				File file = new File(Environment.getExternalStorageDirectory()+"/分级诊疗下载文件/"+currentFileName+currentFileType);
				if(!file.exists()){
					download(currentFileUrl,currentFileName,currentFileType);
				}else{
					iv = new ImageView(myContext);
					String path=Environment.getExternalStorageDirectory()+"/分级诊疗下载文件/"+currentFileName+currentFileType;
					iv.setImageBitmap(loadBitmap(path,200,200));
					iv.setLayoutParams(new LayoutParams(200, 200));
					iv.setPadding(0, 0, 20, 0);
					imagelayout.addView(iv);
				}
			}
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
		                String saveDir = Environment.getExternalStorageDirectory() + "/分级诊疗下载文件/";
		                
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
		                   //把进度传给updateHandler更新ProgressDialog,从消息池中拿来一个msg 不需要另开辟空间
		                    updateHandler.sendMessage(updateHandler.obtainMessage(UPDATE_DOWNLOADING)); 
		                    if(numread <= 0){     
		                    	Message message=new Message();  
		                    	Bundle bundle=new Bundle();  
		                        bundle.putString("currentFileName", fileName); 
		                        bundle.putString("currentFileType", fileType);
		                        message.setData(bundle);
		                        message.what=UPDATE_DOWNLOAD_COMPLETED;
		                        updateHandler.sendMessage(message);
//		                    	updateHandler.sendEmptyMessage(UPDATE_DOWNLOAD_COMPLETED);
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
				downloadCompleted(false, msg.obj!=null?msg.obj.toString():"",null,null);
				break;
			case UPDATE_DOWNLOAD_COMPLETED:
				//下载完成
				String name = msg.getData().getString("currentFileName");
				String type = msg.getData().getString("currentFileType");
				downloadCompleted(true, "",name,type);
//				downloadCompleted(true, "");
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
	public void downloadCompleted(Boolean sucess, String errorMsg, String name, String type) {
		if (sucess) {
			if(currentFileType.equals("jpg")){
				iv = new ImageView(myContext);
				String path=Environment.getExternalStorageDirectory()+"/分级诊疗下载文件/"+name+type;
				iv.setImageBitmap(loadBitmap(path,100,200));
				iv.setLayoutParams(new LayoutParams(100, 200));
				iv.setPadding(0, 0, 20, 0);
				imagelayout.addView(iv);
			}
		} else {
			ToastUtil.showToast(myContext, "下载失败！");
		}
		QuestionListAdapter.this.notifyDataSetChanged();
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
	
	public List<InQueryQuestionSrvOutputItem> getList() {
		return list;
	}

	public void setList(List<InQueryQuestionSrvOutputItem> list) {
		this.list = list;
	}

}

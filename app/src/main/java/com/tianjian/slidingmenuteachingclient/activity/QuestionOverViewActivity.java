package com.tianjian.slidingmenuteachingclient.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tianjian.slidingmenuteachingclient.R;
import com.tianjian.slidingmenuteachingclient.adapter.QuestionOverViewAnswersAdapter;
import com.tianjian.slidingmenuteachingclient.adapter.QuestionOverViewResourceAdapter;
import com.tianjian.slidingmenuteachingclient.application.SystemApplcation;
import com.tianjian.slidingmenuteachingclient.bean.InLoginSrv.InLoginSrvOutputItem;
import com.tianjian.slidingmenuteachingclient.bean.InQueryQuestionSrv.InQueryQuestionAnswersDetailSrvOutputItem;
import com.tianjian.slidingmenuteachingclient.bean.InQueryQuestionSrv.InQueryQuestionResourcesDetailSrvOutputItem;
import com.tianjian.slidingmenuteachingclient.bean.InQueryQuestionSrv.InQueryQuestionSrvOutputCollection;
import com.tianjian.slidingmenuteachingclient.bean.InQueryQuestionSrv.InQueryQuestionSrvOutputItem;
import com.tianjian.slidingmenuteachingclient.bean.InQueryQuestionSrv.InQueryQuestionSrvResponse;
import com.tianjian.slidingmenuteachingclient.util.ToastUtil;
import com.tianjian.slidingmenuteachingclient.util.network.callback.INetWorkCallBack;
import com.tianjian.slidingmenuteachingclient.util.network.helper.NetWorkHepler;

import org.ksoap2.serialization.SoapObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 提问详情
 * <p>Title: QuestionOverViewActivity.java</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Tianjian</p>
 * <p>team: TianjianTeam</p>
 * @author: Yehao
 * @date 2016年9月7日下午1:58:44
 * @version 1.0
 * 
 */
public class QuestionOverViewActivity extends BaseActivity{
	private SystemApplcation applcation;
	private InLoginSrvOutputItem userDict;
	private ImageButton button_back;
	private TextView content,time;
	private InQueryQuestionSrvOutputItem item;
	private QuestionOverViewResourceAdapter adapter;
	private GridView listview;
	private String currentFileName="";
	private String currentFileType="";
	private String currentFileUrl="";
	protected String saveDir;
	private Button replay_button;
	private ListView answers_listview;
	private QuestionOverViewAnswersAdapter answersadapter;
	String question_id="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questionoverview_layout);
		
		applcation = (SystemApplcation)getApplication();
		userDict = systemApplcation.getUserDict();
		applcation.addActivity(this);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		
		Gson gson = new Gson();
//		item = gson.fromJson(getIntent().getStringExtra("list"),new TypeToken<InQueryQuestionSrvOutputItem>(){}.getType());
		question_id = getIntent().getStringExtra("id");
		initView();
	}

	@Override
	protected void onResume() {
		super.onResume();
		initView();
	}
	
	private void initView() {
		//回退按钮
		button_back = (ImageButton) findViewById(R.id.questionoverview_button_back);
		button_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("QUESTION_ID", question_id);
		hashMap.put("OPERATE_TYPE", "6");
		queryQuestion(hashMap);
	}

	
	private void queryQuestion(HashMap<String, Object> hashMap) {
		NetWorkHepler.postWsData("queryquestionWs", "process", hashMap, new INetWorkCallBack() {
			SoapObject objectResult;
			@Override
			public void onResult(Object result) {
				if(result == null){
					ToastUtil.showToast(QuestionOverViewActivity.this, "查询数据失败！");
				}else if(result instanceof SoapObject) {
					objectResult = (SoapObject) result;
				}else{
					ToastUtil.showToast(QuestionOverViewActivity.this, "服务器连接失败！");
					return;
				}
				InQueryQuestionSrvResponse response = new InQueryQuestionSrvResponse();
				try {
					for(int i=0;i<objectResult.getPropertyCount();i++){
						response.setProperty(i, objectResult.getProperty(i));
					}
				} catch (Exception e) {
					ToastUtil.showToast(QuestionOverViewActivity.this, "数据出错了，请重试！");
				}
				
				if("Y".equals(response.getErrorFlag()) && "查询成功".equals(response.getErrorMessage())){
					List<InQueryQuestionSrvOutputItem> list = new ArrayList<InQueryQuestionSrvOutputItem>();
					Gson gson = new Gson();
					InQueryQuestionSrvOutputCollection collection = gson.fromJson(response.getJSON(), new TypeToken<InQueryQuestionSrvOutputCollection>() {}.getType());
					item = collection.getInQueryQuestionSrvOutputItem().get(0);
					initViews();
				}else{
					ToastUtil.showToast(QuestionOverViewActivity.this, "查询数据失败！");
				}
				
			}
			
			@Override
			public void onProgressUpdate(Integer[] values) {
			}
			
			@Override
			public void onPreExecute() {
			}
		});
	}
	
	protected void initViews() {
		content = (TextView) findViewById(R.id.questionoverview_questiontext);
		content.setMovementMethod(new ScrollingMovementMethod());
		time = (TextView) findViewById(R.id.questionoverview_questiontime);
		if(item.getCLASSTYPE().equals("2")){
			content.setText(item.getCONTENT().split("！@#-")[0]+"，"+item.getCONTENT().split("！@#-")[1]+"，"+item.getCONTENT().split("！@#-")[2]+"岁。"+item.getCONTENT().split("！@#-")[3]);
		}else{
			content.setText(item.getCONTENT());
		}
		
		time.setText(item.getTIME().replace(".0", ""));
		
		listview = (GridView) findViewById(R.id.questionoverview_list);
//		AnimationSet set = new AnimationSet(false);  
//		Animation animation = new AlphaAnimation(0,1);   //AlphaAnimation 控制渐变透明的动画效果  
//		animation.setDuration(500);     //动画时间毫秒数  
//		set.addAnimation(animation);
//		LayoutAnimationController controller = new LayoutAnimationController(set, 1);  
//		listview.setLayoutAnimation(controller); 
		
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				currentFileUrl = item.getRESOURCESDETAILLINE().getInQueryQuestionResourcesDetailSrvOutputItem().get(position).getRESOURCESURL();
				currentFileName = item.getRESOURCESDETAILLINE().getInQueryQuestionResourcesDetailSrvOutputItem().get(position).getRESOURCESNAME();
				currentFileType = item.getRESOURCESDETAILLINE().getInQueryQuestionResourcesDetailSrvOutputItem().get(position).getRESOURCESURL().substring(item.getRESOURCESDETAILLINE().getInQueryQuestionResourcesDetailSrvOutputItem().get(position).getRESOURCESURL().lastIndexOf("."),item.getRESOURCESDETAILLINE().getInQueryQuestionResourcesDetailSrvOutputItem().get(position).getRESOURCESURL().length()).toLowerCase();
				
            	try {
					Intent intent = openFile(Environment
							.getExternalStorageDirectory()
							+ "/分级诊疗下载文件/"
							+ currentFileName + currentFileType);
					startActivity(intent);
				} catch (Exception e) {
					ToastUtil.showToast(QuestionOverViewActivity.this, "打开文件失败，请安装WPS文档浏览工具！");
				}
			}
		});
		
		if(null!=item.getRESOURCESDETAILLINE()){
			if(null!=item.getRESOURCESDETAILLINE().getInQueryQuestionResourcesDetailSrvOutputItem() && item.getRESOURCESDETAILLINE().getInQueryQuestionResourcesDetailSrvOutputItem().size()>0){
				initListView(item.getRESOURCESDETAILLINE().getInQueryQuestionResourcesDetailSrvOutputItem());
			}
		}
		
		answers_listview = (ListView) findViewById(R.id.questionoverview_answers_list);
		if(null!=item.getANSWERSDETAILLINE()){
			if(null!=item.getANSWERSDETAILLINE().getInQueryQuestionAnswersDetailSrvOutputItem() && item.getANSWERSDETAILLINE().getInQueryQuestionAnswersDetailSrvOutputItem().size()>0){
				initAnswersListView(item.getANSWERSDETAILLINE().getInQueryQuestionAnswersDetailSrvOutputItem());
			}
		}
		
		replay_button = (Button) findViewById(R.id.questionoverview_replay);
		if(userDict.getTYPE().equals("1") && item.getUSERNAME().equals(userDict.getUSERNAME()) && item.getEFFECTIVESTATUS().equals("1")){
			replay_button.setText("追问");
			replay_button.setVisibility(View.VISIBLE);
		}else if(userDict.getTYPE().equals("2") && item.getEFFECTIVESTATUS().equals("1")){
			replay_button.setText("回复");
			replay_button.setVisibility(View.VISIBLE);
		}
		
		if(userDict.getTYPE().equals("1") && item.getUSERNAME().equals(userDict.getUSERNAME())){
			replay_button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(QuestionOverViewActivity.this, ReAskQuestionActivity.class);
					intent.putExtra("questionid", item.getQUESTIONID());
					startActivity(intent);	
				}
			});
		}else if(userDict.getTYPE().equals("2")){
			replay_button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(QuestionOverViewActivity.this, ReplayQuestionActivity.class);
					intent.putExtra("questionid", item.getQUESTIONID());
					startActivity(intent);	
				}
			});
		}
		
	}

	private void initListView(List<InQueryQuestionResourcesDetailSrvOutputItem> inQueryQuestionResourcesDetailSrvOutputItem) {
		if(adapter == null){
			adapter = new QuestionOverViewResourceAdapter(inQueryQuestionResourcesDetailSrvOutputItem, QuestionOverViewActivity.this);
			listview.setAdapter(adapter);
		}else{
			adapter.setList(inQueryQuestionResourcesDetailSrvOutputItem);
			adapter.notifyDataSetChanged();
		}
	}
	
	private void initAnswersListView(
			List<InQueryQuestionAnswersDetailSrvOutputItem> inQueryQuestionAnswersDetailSrvOutputItem) {
		if(answersadapter == null){
			answersadapter = new QuestionOverViewAnswersAdapter(inQueryQuestionAnswersDetailSrvOutputItem, QuestionOverViewActivity.this);
			answers_listview.setAdapter(answersadapter);
		}else{
			answersadapter.setList(inQueryQuestionAnswersDetailSrvOutputItem);
			answersadapter.notifyDataSetChanged();
		}
		
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

package com.tianjian.slidingmenuteachingclient.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 提问页面
 * <p>Title: AskQuestionActivity.java</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Tianjian</p>
 * <p>team: TianjianTeam</p>
 * @author: Yehao
 * @date 2016年9月2日下午2:02:14
 * @version 1.0
 * 
 */
public class AskQuestionActivity extends BaseActivity{
	private final static int REQUEST_PICTURE_CODE = 3;
	private SystemApplcation applcation;
	private ImageButton button_back;
	private InLoginSrvOutputItem userDict;
	private List<InQueryResourcesSrvOutputItem>  resourcesList;
	private ImageView addpic;
	private LinearLayout contentLayout;
	private ScrollView scrollView;
	private CustomerProgress customerProgress;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMddHHmmss");
	private String currentTime = "";
	private RadioGroup radioGroup_type,radioGroup_private;
	private String question_type = "常规";
	private String question_private = "私密";
	private EditText text_pname,text_sex,text_age,text_content;
	private String question_pname="";
	private String question_sex="";
	private String question_age="";
	private String question_content="";
	private RelativeLayout ask_patient_layout;
	private Button ask_save;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.askquestion_layout);
		
		applcation = (SystemApplcation)getApplication();
		applcation.addActivity(this);
		userDict = applcation.getUserDict();
		resourcesList = new ArrayList<InQueryResourcesSrvOutputItem>();
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		
		initView();
	}

	private void initView() {
		//回退按钮
		button_back = (ImageButton) findViewById(R.id.ask_button_back);
		button_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		radioGroup_type = (RadioGroup) findViewById(R.id.ask_type_radiogroup);
		radioGroup_private = (RadioGroup) findViewById(R.id.ask_private_radiogroup);
		
		radioGroup_type.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				int radioButtonId = group.getCheckedRadioButtonId();
                 //根据ID获取RadioButton的实例
				RadioButton rb = (RadioButton)AskQuestionActivity.this.findViewById(radioButtonId);
				//更新文本内容，以符合选中项
				question_type =  rb.getText().toString();
				if(question_type.equals("会诊")){
					ask_patient_layout.setVisibility(View.VISIBLE);
				}else{
					ask_patient_layout.setVisibility(View.GONE);
				}
			}
		});
		
		radioGroup_private.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				int radioButtonId = group.getCheckedRadioButtonId();
                //根据ID获取RadioButton的实例
				RadioButton rb = (RadioButton)AskQuestionActivity.this.findViewById(radioButtonId);
				//更新文本内容，以符合选中项
				question_private =  rb.getText().toString();
			}
		});
		
		ask_patient_layout = (RelativeLayout) findViewById(R.id.ask_patient_layout);
		
		addpic = (ImageView) findViewById(R.id.ask_add_pic_icon);
		addpic.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_PICK);
				startActivityForResult(intent, REQUEST_PICTURE_CODE);
			}
		});
		
		scrollView = (ScrollView) findViewById(R.id.ask_resources_scrollview);
		contentLayout = (LinearLayout) findViewById(R.id.ask_resources_content);
		contentLayout.setOrientation(LinearLayout.VERTICAL);
		
		text_pname = (EditText) findViewById(R.id.ask_patient_name);
		text_sex = (EditText) findViewById(R.id.ask_patient_sex);
		text_age = (EditText) findViewById(R.id.ask_patient_age);
		text_content = (EditText) findViewById(R.id.ask_edittext);
		ask_save = (Button) findViewById(R.id.ask_save);
		ask_save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!text_content.getText().toString().equals("")){
					if(question_type.equals("常规")){
						customerProgress =  new CustomerProgress(AskQuestionActivity.this,com.tianjian.slidingmenuteachingclient.R.style.customer_dialog);
						customerProgress.show();
						Gson gson = new Gson();
						HashMap<String, Object> hashMap = new HashMap<String, Object>();
						hashMap.put("OPRATETYPE", "1");
						hashMap.put("ANSWER_STATUS", "1");
						hashMap.put("EFFECTIVE_STATUS", "1");
						hashMap.put("TITLE", "常规问题");
						hashMap.put("CLASS_TYPE", "1");
						hashMap.put("SECRET_TYPE", question_private.equals("私密")?"1":"2");
						hashMap.put("USERNAME", userDict.getUSERNAME());
						hashMap.put("NAME", userDict.getNAME());
						hashMap.put("CONTENT", text_content.getText().toString());
						hashMap.put("LIST_RESOURCES", gson.toJson(resourcesList));
						saveData(hashMap);
					}else{
						if(!text_pname.getText().toString().equals("") && !text_sex.getText().toString().equals("") && !text_age.getText().toString().equals("")){
							customerProgress =  new CustomerProgress(AskQuestionActivity.this,com.tianjian.slidingmenuteachingclient.R.style.customer_dialog);
							customerProgress.show();
							Gson gson = new Gson();
							HashMap<String, Object> hashMap = new HashMap<String, Object>();
							hashMap.put("OPRATETYPE", "1");
							hashMap.put("ANSWER_STATUS", "1");
							hashMap.put("EFFECTIVE_STATUS", "1");
							hashMap.put("TITLE", "会诊问题");
							hashMap.put("CLASS_TYPE", "2");
							hashMap.put("SECRET_TYPE", question_private.equals("私密")?"1":"2");
							hashMap.put("USERNAME", userDict.getUSERNAME());
							hashMap.put("NAME", userDict.getNAME());
							hashMap.put("CONTENT", text_pname.getText().toString()+"！@#-"+text_sex.getText().toString()+"！@#-"+text_age.getText().toString()+"！@#-"+text_content.getText().toString());
							hashMap.put("LIST_RESOURCES", gson.toJson(resourcesList));
							saveData(hashMap);
						}else{
							ToastUtil.showToast(AskQuestionActivity.this, "病人信息不完整！");
						}
					}
				}else{
					ToastUtil.showToast(AskQuestionActivity.this, "问题内容不能为空！");
				}
			}
		});
	}
	
	protected void saveData(HashMap<String, Object> hashMap) {
		NetWorkHepler.postWsData("importquestionWs", "process", hashMap, new INetWorkCallBack() {
			SoapObject objectResult;
			@Override
			public void onResult(Object result) {
				customerProgress.dismissDialog(customerProgress);
				if(result == null){
					ToastUtil.showToast(AskQuestionActivity.this, "保存数据失败！");
				}else if(result instanceof SoapObject) {
					objectResult = (SoapObject) result;
				}else{
					ToastUtil.showToast(AskQuestionActivity.this, "服务器连接失败！");
					return;
				}
				ImPortTaskSrvResponse response = new ImPortTaskSrvResponse();
				try {
					for(int i=0;i<objectResult.getPropertyCount();i++){
						response.setProperty(i, objectResult.getProperty(i));
					}
				} catch (Exception e) {
					ToastUtil.showToast(AskQuestionActivity.this, "数据出错了，请重试！");
				}
				if("Y".equals(response.getErrorFlag())){
					ToastUtil.showToast(AskQuestionActivity.this, "保存成功！");
					setResult(1,  getIntent());
					finish();
				}else{
					ToastUtil.showToast(AskQuestionActivity.this, "获取数据失败！");
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
				if("上传成功".equals(result)){
		 			InQueryResourcesSrvOutputItem item = new InQueryResourcesSrvOutputItem();
		 			if(fileName.endsWith(".jpg")){
		 				item.setRESOURCESNAME("JPG_"+currentTime);
		 				item.setRESOURCESURL(Constant.IP_ADDRESS+File.separator+"uploadFiles"+File.separator+"pic"+fileName);
		 				item.setRESOURCESTYPE("5");
		 			}
		 			
		 			item.setUPLOADUSERNAME(userDict.getUSERNAME());
		 			item.setUPLOADNAME(userDict.getNAME());
		 			item.setUPLOADTIME(formatter.format(new Date()));
		 			item.setIMAGE(null);
		 			resourcesList.add(item);
				}else{
					contentLayout.removeViewAt(resourcesList.size());
					ToastUtil.showToast(AskQuestionActivity.this, "添加附件失败！");
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

/**
 * Copyright (c) 2013 Tianjian, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * Tianjian, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Tianjian.
 */
package com.tianjian.slidingmenuteachingclient.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tianjian.slidingmenuteachingclient.R;

/**
 * 课件
 * <p>Title: ResourcesFragment.java</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Tianjian</p>
 * <p>team: TianjianTeam</p>
 * @author: Yehao
 * @date 2016年8月17日上午9:01:37
 * @version 1.0
 * 
 */

public class ResourcesFragment extends BaseFragment{
	private View rootView;
//	private EditText search_et_input;
//	private ImageView search_iv_delete;
//	private PullToRefreshGridView listview;
//	private ResourcesAdapter adapter;
//	private int currentPage = 1;
//	private int pageSize;
//	List<InQueryResourcesSrvOutputItem> list = new ArrayList<InQueryResourcesSrvOutputItem>();
//	HashMap<String, Object> hashMap = new HashMap<String, Object>();
//	private ProgressBar proBar;
//	private int progress;
//	private String currentFileName="";
//	private String currentFileType="";
//	private String currentFileUrl="";
//	protected String saveDir;
//	private Boolean canceled = false;
//	private static final int UPDATE_CHECKCOMPLETED = 1;//已经完成检测新版本
//	private static final int UPDATE_DOWNLOADING = 2; //下载中
//	private static final int UPDATE_DOWNLOAD_ERROR = 3; //下载出错
//	private static final int UPDATE_DOWNLOAD_COMPLETED = 4; //下载完成
//	private static final int UPDATE_DOWNLOAD_CANCELED = 5;//取消下载
//	private boolean flag = false;//搜索栏初始字符串是否为空
//
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		if(rootView == null){
//			flag = true;
			rootView = inflater.inflate(R.layout.resources_layout, null);
//			initView();
		}
		return rootView;
	}
//	private void initView() {
//		//搜索框删除按钮
//		search_iv_delete = (ImageView) rootView.findViewById(R.id.resources_search_iv_delete);
//		search_iv_delete.setOnClickListener(new View.OnClickListener(){
//			@Override
//			public void onClick(View v) {
//				search_et_input.setText("");
//				search_iv_delete.setVisibility(View.GONE);
//			}
//		});
//
//		//搜索框Edittext
//		search_et_input = (EditText) rootView.findViewById(R.id.resources_search_et_input);
//		search_et_input.addTextChangedListener(new EditChangedListener());
//		search_et_input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
//                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                    notifyStartSearching(search_et_input.getText().toString());
//                }
//                return true;
//            }
//        });
//
//		proBar = (ProgressBar) rootView.findViewById(R.id.resources_probar);
//		proBar.setVisibility(View.INVISIBLE);
//
//		listview = (PullToRefreshGridView) rootView.findViewById(R.id.resources_list);
//		listview.setOnItemClickListener(new OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				TextView text = (TextView) view.findViewById(R.id.resources_item_button);
//
//				if(proBar.getVisibility() == View.VISIBLE && !text.getText().equals("查看")){
//					ToastUtil.showToast(getActivity(), "请稍后...");
//					return;
//				}
//
//				currentFileUrl = list.get(position).getRESOURCESURL();
//				currentFileName = list.get(position).getRESOURCESNAME();
//				if(!currentFileUrl.equals("") && currentFileUrl.contains(".")){
//					currentFileType = list.get(position).getRESOURCESURL().substring(list.get(position).getRESOURCESURL().lastIndexOf("."),list.get(position).getRESOURCESURL().length()).toLowerCase();
//
//				}
//
//				File file = new File(Environment.getExternalStorageDirectory()+"/分级诊疗下载文件/"+currentFileName+currentFileType);
//                if(!file.exists()){
//                	text.setText("正在下载");
//                	download(currentFileUrl,currentFileName,currentFileType);
//                	proBar.setVisibility(View.VISIBLE);
//                    proBar.setMax(100);
//                    proBar.setProgress(0);
//                }else{
//                	try {
//						Intent intent = openFile(Environment.getExternalStorageDirectory() + "/分级诊疗下载文件/" + currentFileName + currentFileType);
//						startActivity(intent);
//					} catch (Exception e) {
//						ToastUtil.showToast(getActivity(), "打开文件失败，请安装WPS文档浏览工具！");
//					}
//                }
//			}
//		});
//
//
//		listview.setMode(Mode.BOTH);
//		listview.setOnRefreshListener(new OnRefreshListener2<GridView>() {
//
//			@Override
//			public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
//				hashMap.remove("CURRENT_PAGE");
//				hashMap.put("CURRENT_PAGE", 1);
//				hashMap.remove("PAGE_SIZE");
//				hashMap.put("PAGE_SIZE", pageSize);
//				queryRefreshData(hashMap);
//			}
//
//			@Override
//			public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
//				currentPage = Integer.parseInt(hashMap.get("CURRENT_PAGE").toString());
//				++currentPage;
//				hashMap.remove("CURRENT_PAGE");
//				hashMap.put("CURRENT_PAGE", currentPage);
//				queryMoreData(hashMap);
//
//			}
//		});
//		listview.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {
//
//			@Override
//			public void onLastItemVisible() {
//
//			}
//		});
//		initListViewTipText();
//
//		pageSize = Constant.PAGE_SIZE;
//		hashMap.put("OPERATE_TYPE", "1");
//		hashMap.put("CURRENT_PAGE", currentPage);
//		hashMap.put("PAGE_SIZE", Constant.PAGE_SIZE);
//		queryData(hashMap);
//	}
//
//	private void initListViewTipText() {
//        // 设置上拉刷新文本
//    	com.handmark.pulltorefresh.library.ILoadingLayout  startLabels = listview.getLoadingLayoutProxy(true,
//                false);
//        startLabels.setPullLabel("下拉刷新...");
//        startLabels.setReleaseLabel("放开立即刷新...");
//        startLabels.setRefreshingLabel("正在刷新...");
//
//        // 设置下拉刷新文本
//        com.handmark.pulltorefresh.library.ILoadingLayout endLabels = listview.getLoadingLayoutProxy(false, true);
//        endLabels.setPullLabel("上拉加载更多...");
//        endLabels.setReleaseLabel("放开加载更多...");
//        endLabels.setRefreshingLabel("正在加载...");
//     }
//
//	private void notifyStartSearching(String text){
//	    //隐藏软键盘
//	    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//	    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//	}
//
//	private class EditChangedListener implements TextWatcher {
//	    @Override
//	    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
//	    	if(charSequence.toString().equals("")){
//	    		flag = false;
//	    	}else{
//	    		flag = true;
//	    	}
//	    }
//
//	    @Override
//	    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
//	    		if (!"".equals(charSequence.toString()) && charSequence.toString().length()>1) {
//		        	search_iv_delete.setVisibility(View.VISIBLE);
//		    		hashMap.put("OPERATE_TYPE", "2");
//		    		hashMap.put("RESOURCES_NAME", charSequence.toString());
//		    		hashMap.put("CURRENT_PAGE", 1);
//		    		hashMap.put("PAGE_SIZE", Constant.PAGE_SIZE);
//		    		searchData(hashMap);
//		        } else if("".equals(charSequence.toString()) && flag){
//		        	search_iv_delete.setVisibility(View.GONE);
//		    		hashMap.put("OPERATE_TYPE", "1");
//		    		hashMap.put("CURRENT_PAGE", currentPage);
//		    		hashMap.put("PAGE_SIZE", pageSize);
//		    		queryData(hashMap);
//		        }else {
//		        	search_iv_delete.setVisibility(View.GONE);
//
//		        }
//	    }
//
//	    @Override
//	    public void afterTextChanged(Editable editable) {
//	    }
//	}
//
//	private void queryData(HashMap<String, Object> hashMap) {
//		final CustomerProgress customerProgress =  new CustomerProgress(getActivity(),com.tianjian.teachingclient.R.style.customer_dialog);
//		NetWorkHepler.postWsData("resourcesWs", "process", hashMap, new INetWorkCallBack() {
//			SoapObject objectResult;
//			@Override
//			public void onResult(Object result) {
//				customerProgress.dismissDialog(customerProgress);
//				if(result == null){
//					ToastUtil.showToast(getActivity(), "课件资料查询失败！");
//				}else if(result instanceof SoapObject) {
//					objectResult = (SoapObject) result;
//				}else{
//					ToastUtil.showToast(getActivity(), "服务器连接失败！");
//					return;
//				}
//				InQueryResourcesSrvResponse response = new InQueryResourcesSrvResponse();
//				try {
//					for(int i=0;i<objectResult.getPropertyCount();i++){
//						response.setProperty(i, objectResult.getProperty(i));
//					}
//				} catch (Exception e) {
//					ToastUtil.showToast(getActivity(), "数据出错了，请重试！");
//				}
//
//				if("Y".equals(response.getErrorFlag())){
//					list = response.getInQueryResourcesSrvOutputCollection().getInQueryResourcesSrvOutputItem();
//					initListView(list);
//				}else{
//					ToastUtil.showToast(getActivity(), "暂无数据！");
//				}
//
//			}
//
//			@Override
//			public void onProgressUpdate(Integer[] values) {
//
//			}
//
//			@Override
//			public void onPreExecute() {
//				customerProgress.show();
//			}
//		});
//	}
//
//	protected void queryRefreshData(HashMap<String, Object> hashMap) {
//		final CustomerProgress customerProgress =  new CustomerProgress(getActivity(),com.tianjian.teachingclient.R.style.customer_dialog);
//		NetWorkHepler.postWsData("resourcesWs", "process", hashMap, new INetWorkCallBack() {
//			SoapObject objectResult;
//			@Override
//			public void onResult(Object result) {
//				listview.onRefreshComplete();
//				customerProgress.dismissDialog(customerProgress);
//				if(result == null){
//					ToastUtil.showToast(getActivity(), "课件资料查询失败！");
//				}else if(result instanceof SoapObject) {
//					objectResult = (SoapObject) result;
//				}else{
//					ToastUtil.showToast(getActivity(), "服务器连接失败！");
//					return;
//				}
//				InQueryResourcesSrvResponse response = new InQueryResourcesSrvResponse();
//				try {
//					for(int i=0;i<objectResult.getPropertyCount();i++){
//						response.setProperty(i, objectResult.getProperty(i));
//					}
//				} catch (Exception e) {
//					ToastUtil.showToast(getActivity(), "数据出错了，请重试！");
//				}
//				if("Y".equals(response.getErrorFlag())){
//					list = response.getInQueryResourcesSrvOutputCollection().getInQueryResourcesSrvOutputItem();
//					initListView(list);
//				}else{
//					ToastUtil.showToast(getActivity(), "课件资料查询失败！");
//				}
//
//			}
//
//			@Override
//			public void onProgressUpdate(Integer[] values) {
//
//			}
//
//			@Override
//			public void onPreExecute() {
//				customerProgress.show();
//			}
//		});
//	}
//
//	protected void queryMoreData(final HashMap<String, Object> hashMap) {
//		final CustomerProgress customerProgress =  new CustomerProgress(getActivity(),com.tianjian.teachingclient.R.style.customer_dialog);
//		NetWorkHepler.postWsData("resourcesWs", "process", hashMap, new INetWorkCallBack() {
//			SoapObject objectResult;
//			@Override
//			public void onResult(Object result) {
//				listview.onRefreshComplete();
//				customerProgress.dismissDialog(customerProgress);
//				if(result == null){
//					ToastUtil.showToast(getActivity(), "课件资料查询失败！");
//				}else if(result instanceof SoapObject) {
//					objectResult = (SoapObject) result;
//				}else{
//					ToastUtil.showToast(getActivity(), "服务器连接失败！");
//					return;
//				}
//				InQueryResourcesSrvResponse response = new InQueryResourcesSrvResponse();
//				try {
//					for(int i=0;i<objectResult.getPropertyCount();i++){
//						response.setProperty(i, objectResult.getProperty(i));
//					}
//				} catch (Exception e) {
//					ToastUtil.showToast(getActivity(), "数据出错了，请重试！");
//				}
//				if(null!=response.getInQueryResourcesSrvOutputCollection()){
//					List<InQueryResourcesSrvOutputItem> listNew = response.getInQueryResourcesSrvOutputCollection().getInQueryResourcesSrvOutputItem();
//					if("Y".equals(response.getErrorFlag())){
//						pageSize +=listNew.size();
//						for(InQueryResourcesSrvOutputItem item : listNew){
//							list.add(item);
//						}
//						adapter.setList(list);
//						adapter.notifyDataSetChanged();
//					}else{
//						ToastUtil.showToast(getActivity(), "课件资料查询失败！");
//					}
//				}else{
//					currentPage--;
//					hashMap.put("CURRENT_PAGE", currentPage);
//					ToastUtil.showToast(getActivity(), "暂无更多课件！");
//				}
//
//
//			}
//
//			@Override
//			public void onProgressUpdate(Integer[] values) {
//
//			}
//
//			@Override
//			public void onPreExecute() {
//				customerProgress.show();
//			}
//		});
//	}
//
//	public void searchData(HashMap<String, Object> hashMap) {
//		final CustomerProgress customerProgress =  new CustomerProgress(getActivity(),com.tianjian.teachingclient.R.style.customer_dialog);
//		NetWorkHepler.postWsData("resourcesWs", "process", hashMap, new INetWorkCallBack() {
//			SoapObject objectResult;
//			@Override
//			public void onResult(Object result) {
//				customerProgress.dismissDialog(customerProgress);
//				if(result == null){
//					ToastUtil.showToast(getActivity(), "课件资料查询失败！");
//				}else if(result instanceof SoapObject) {
//					objectResult = (SoapObject) result;
//				}else{
//					ToastUtil.showToast(getActivity(), "服务器连接失败！");
//					return;
//				}
//				InQueryResourcesSrvResponse response = new InQueryResourcesSrvResponse();
//				try {
//					for(int i=0;i<objectResult.getPropertyCount();i++){
//						response.setProperty(i, objectResult.getProperty(i));
//					}
//				} catch (Exception e) {
//					ToastUtil.showToast(getActivity(), "数据出错了，请重试！");
//				}
//				if("Y".equals(response.getErrorFlag())){
//					list = response.getInQueryResourcesSrvOutputCollection().getInQueryResourcesSrvOutputItem();
//					initListView(list);
//				}else{
//					list = new ArrayList<InQueryResourcesSrvOutputItem>();
//					initListView(list);
//					ToastUtil.showToast(getActivity(), "未查到课件！");
//				}
//
//			}
//
//			@Override
//			public void onProgressUpdate(Integer[] values) {
//
//			}
//
//			@Override
//			public void onPreExecute() {
//				customerProgress.show();
//			}
//		});
//	}
//
//	protected void initListView(List<InQueryResourcesSrvOutputItem> list) {
//		if(adapter == null){
//			adapter = new ResourcesAdapter(list, getActivity());
//			listview.setAdapter(adapter);
//		}else{
//			adapter.setList(list);
//			adapter.notifyDataSetChanged();
//		}
//	}
//
//	// 下载
//	public void download(final String filePath, final String fileName, final String fileType){
//		new Thread() {
//			@Override
//		        public void run() {
//		            try {
//		                URL url = new URL(filePath);
//		                //新建连接并获取资源
//		                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//		                conn.setConnectTimeout(5000);
//		                conn.setRequestMethod("POST");
//		                conn.setRequestProperty("Accept-Encoding", "identity");
//		                conn.connect();
//		                int length = conn.getContentLength();
//
//		                InputStream is = conn.getInputStream();
//
//		                File file = new File(Environment.getExternalStorageDirectory()+"/分级诊疗下载文件/");
//		                if(!file.exists()){
//		                	file.mkdirs();
//		                }
//		                saveDir = Environment.getExternalStorageDirectory() + "/分级诊疗下载文件/";
//
//		                //如果文件存在，就先删除
//		                File sdfile = new File(saveDir,fileName+fileType);
//		                if(sdfile.exists())
//		                {
//		                	sdfile.delete();
//		                }
//
//		                FileOutputStream fos = new FileOutputStream(sdfile);
//
//		                int count = 0;
//		                byte buf[] = new byte[512];
//
//		                do{
//		                    int numread = is.read(buf);
//		                    count += numread;
//		                    //计算下载的进度
//		                    progress =(int)(((float)count / length) * 100);
//		                   //把进度传给updateHandler更新ProgressDialog,从消息池中拿来一个msg 不需要另开辟空间
//		                    updateHandler.sendMessage(updateHandler.obtainMessage(UPDATE_DOWNLOADING));
//		                    if(numread <= 0){
//		                    	updateHandler.sendEmptyMessage(UPDATE_DOWNLOAD_COMPLETED);
//		                        break;
//		                    }
//		                    fos.write(buf,0,numread);
//		                }while(!canceled);
//			                fos.close();
//			                is.close();
//		            } catch (MalformedURLException e) {
//		                e.printStackTrace();
//		                updateHandler.sendMessage(updateHandler.obtainMessage(UPDATE_DOWNLOAD_ERROR,e.getMessage()));
//		            } catch(IOException e){
//		                e.printStackTrace();
//		                updateHandler.sendMessage(updateHandler.obtainMessage(UPDATE_DOWNLOAD_ERROR,e.getMessage()));
//		            }
//
//		        }
//		}.start();
//	}
//
//	Handler updateHandler = new Handler()
//	{
//		@Override
//		public void handleMessage(Message msg) {
//
//			switch (msg.what) {
//			case UPDATE_DOWNLOADING:
//				//更新ProgressDialog的进度
//				downloadProgressChanged(progress);
//				break;
//			case UPDATE_DOWNLOAD_ERROR:
//				//下载失败
//				downloadCompleted(false, msg.obj!=null?msg.obj.toString():"");
//				break;
//			case UPDATE_DOWNLOAD_COMPLETED:
//				//下载完成
//				downloadCompleted(true, "");
//				break;
//			default:
//				break;
//			}
//		}
//	};
//
//	//更新ProgressDialog的进度
//	public void downloadProgressChanged(int progress) {
//		if (proBar != null) {
//			proBar.setProgress(progress);
//		}
//
//	}
//
//	//下载完成
//	public void downloadCompleted(Boolean sucess, String errorMsg) {
//		if (proBar != null) {
//			proBar.setVisibility(View.INVISIBLE);
//			proBar.setProgress(0);
//		}
//		if (sucess) {
//			adapter.setList(list);
//			adapter.notifyDataSetChanged();
//			ToastUtil.showToast(getActivity(), "下载完成！");
////			Intent intent = openFile(saveDir+"/"+currentFileName+currentFileType);
////			startActivity(intent);
//		} else {
//			proBar.setVisibility(View.INVISIBLE);
//			proBar.setProgress(0);
//			adapter.notifyDataSetChanged();
//			ToastUtil.showToast(getActivity(), "下载失败！");
//		}
//	}
//
//	public static Intent openFile(String filePath){
//        /* 取得扩展名 */
//        String end=filePath.substring(filePath.lastIndexOf(".") + 1,filePath.length()).toLowerCase();
//        if(end.equals("mp4")){
//            return getVideoFileIntent(filePath);
//        }else if(end.equals("jpg")){
//            return getImageFileIntent(filePath);
//        }else if(end.equals("ppt")|| end.equals("pptx")){
//            return getPptFileIntent(filePath);
//        }else if(end.equals("doc") || end.equals("docx")){
//            return getWordFileIntent(filePath);
//        }else if(end.equals("pdf")){
//            return getPdfFileIntent(filePath);
//        }else{
//        	return getAllIntent(filePath);
//        }
//	}
//
//	public static Intent getAllIntent( String param ) {
//
//        Intent intent = new Intent();
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.setAction(Intent.ACTION_VIEW);
//        Uri uri = Uri.fromFile(new File(param ));
//        intent.setDataAndType(uri,"*/*");
//        return intent;
//    }
//
//	public static Intent getVideoFileIntent( String param ) {
//
//        Intent intent = new Intent("android.intent.action.VIEW");
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.putExtra("oneshot", 0);
//        intent.putExtra("configchange", 0);
//        Uri uri = Uri.fromFile(new File(param ));
//        intent.setDataAndType(uri, "video/*");
//        return intent;
//    }
//
//	public static Intent getImageFileIntent( String param ) {
//
//        Intent intent = new Intent("android.intent.action.VIEW");
//        intent.addCategory("android.intent.category.DEFAULT");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Uri uri = Uri.fromFile(new File(param ));
//        intent.setDataAndType(uri, "image/*");
//        return intent;
//    }
//
//	public static Intent getPptFileIntent( String param ){
//
//        Intent intent = new Intent("android.intent.action.VIEW");
//        intent.addCategory("android.intent.category.DEFAULT");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Uri uri = Uri.fromFile(new File(param ));
//        intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
//        return intent;
//    }
//
//	public static Intent getWordFileIntent( String param ){
//
//        Intent intent = new Intent("android.intent.action.VIEW");
//        intent.addCategory("android.intent.category.DEFAULT");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Uri uri = Uri.fromFile(new File(param ));
//        intent.setDataAndType(uri, "application/msword");
//        return intent;
//    }
//
//	public static Intent getPdfFileIntent( String param ){
//
//        Intent intent = new Intent("android.intent.action.VIEW");
//        intent.addCategory("android.intent.category.DEFAULT");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Uri uri = Uri.fromFile(new File(param ));
//        intent.setDataAndType(uri, "application/pdf");
//        return intent;
//    }
}

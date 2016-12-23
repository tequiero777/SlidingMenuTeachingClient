package com.tianjian.slidingmenuteachingclient.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tianjian.slidingmenuteachingclient.R;
import com.tianjian.slidingmenuteachingclient.application.CrashHandler;
import com.tianjian.slidingmenuteachingclient.application.SystemApplcation;
import com.tianjian.slidingmenuteachingclient.bean.InLoginSrv.InLoginSrvOutputItem;
import com.tianjian.slidingmenuteachingclient.bean.InLoginSrv.InLoginSrvResponse;
import com.tianjian.slidingmenuteachingclient.fragment.HomePageMentorFragment;
import com.tianjian.slidingmenuteachingclient.fragment.HomePageStudentFragment;
import com.tianjian.slidingmenuteachingclient.fragment.QuestionsMentorFragment;
import com.tianjian.slidingmenuteachingclient.fragment.QuestionsStudentFragment;
import com.tianjian.slidingmenuteachingclient.fragment.ResourcesFragment;
import com.tianjian.slidingmenuteachingclient.fragment.TasksMentorFragment;
import com.tianjian.slidingmenuteachingclient.fragment.TasksStudentFragment;
import com.tianjian.slidingmenuteachingclient.fragment.UserInfoMentorFragment;
import com.tianjian.slidingmenuteachingclient.fragment.UserInfoStudentFragment;
import com.tianjian.slidingmenuteachingclient.util.ImageUtil;
import com.tianjian.slidingmenuteachingclient.util.ToastUtil;
import com.tianjian.slidingmenuteachingclient.util.network.callback.INetWorkCallBack;
import com.tianjian.slidingmenuteachingclient.util.network.helper.NetWorkHepler;
import com.tianjian.slidingmenuteachingclient.view.ColumnChartDialog;
import com.tianjian.slidingmenuteachingclient.view.CustomerPopwindow;
import com.tianjian.slidingmenuteachingclient.view.CustomerProgress;

import org.ksoap2.serialization.SoapObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.widget.LinearLayout.LayoutParams;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public final static int REQUEST_PICTURE_CHOOSE = 1;
    public final static int  REQUEST_CAMERA_IMAGE = 2;
    public final static int REQUEST_CROP_IMAGE = 3;
    private Bitmap mImage = null;
    private byte[] mImageData = null;
    private InLoginSrvOutputItem userDict;
    private SystemApplcation systemApplcation;
    private View headerLayout;
    private TextView userName,userPhone;
    private ImageView imageView;
    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;
    private HomePageStudentFragment homepageFragment_stu;
    private HomePageMentorFragment homepageFragment_men;
    private ResourcesFragment resourcesFragment;
    private QuestionsStudentFragment questionsFragment_stu;
    private QuestionsMentorFragment questionsFragment_men;
    private TasksStudentFragment tasksFragment_stu;
    private TasksMentorFragment tasksFragment_men;
    private UserInfoStudentFragment userInfoFragment_stu;
    private UserInfoMentorFragment userInfoFragment_men;
    private SharedPreferences preferences;
    private int loginflag;
    private Toolbar toolbar;
    private PopupWindow popupWindow;
    private View popuRootView;
    private ColumnChartView mColumnCharView;
    /*========== 状态相关 ==========*/
    private boolean isHasAxes = true;                       //是否显示坐标轴
    private boolean isHasAxesNames = true;                  //是否显示坐标轴
    private boolean isHasColumnLabels = false;              //是否显示列标签
    private boolean isColumnsHasSelected = false;           //设置列点击后效果(消失/显示标签)
    /*========== 标志位相关 ==========*/
    private static final int DEFAULT_DATA = 0;              //默认数据标志位
    private static final int SUBCOLUMNS_DATA = 1;           //多子列数据标志位
    private static final int NEGATIVE_SUBCOLUMNS_DATA = 2;  //反向多子列标志位
    private static final int STACKED_DATA = 3;              //堆放数据标志位
    private static final int NEGATIVE_STACKED_DATA = 4;     //反向堆放数据标志位
    private static boolean IS_NEGATIVE = false;             //是否需要反向标志位

    /*========== 数据相关 ==========*/
    private ColumnChartData mColumnChartData;               //柱状图数据
    private int dataType = DEFAULT_DATA;                    //默认数据状态

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        headerLayout = (View) navigationView.inflateHeaderView(R.layout.nav_header_main);

        systemApplcation = (SystemApplcation) MainActivity.this.getApplication();
        userDict = systemApplcation.getUserDict();

        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);

        Intent intent = getIntent();
        loginflag = intent.getIntExtra("loginflag", 0);

        if(loginflag == 1){
            if(null!=systemApplcation.getHomePageStudentFragment()){
                showFragment(systemApplcation.getHomePageStudentFragment());
            }else{
                homepageFragment_stu = new HomePageStudentFragment();
                systemApplcation.setHomePageStudentFragment(homepageFragment_stu);
                showFragment(homepageFragment_stu);
            }
        }else if(loginflag == 2){
            if(null!=systemApplcation.getHomePageMentorFragment()){
                showFragment(systemApplcation.getHomePageMentorFragment());
            }else{
                homepageFragment_men = new HomePageMentorFragment();
                systemApplcation.setHomePageMentorFragment(homepageFragment_men);
                showFragment(homepageFragment_men);
            }
        }

        initData();
    }

    private void initData() {
        preferences = MainActivity.this.getSharedPreferences("TEACHING", MainActivity.this.MODE_PRIVATE);

        userName = (TextView) headerLayout.findViewById(R.id.userName);
        userPhone = (TextView) headerLayout.findViewById(R.id.userPhone);
        imageView = (ImageView) headerLayout.findViewById(R.id.imageView);

        userName.setText(userDict.getNAME());
        userPhone.setText("Phone:"+userDict.getUSERNAME());
        if(userDict.getIMAGE()!=null){
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(userDict.getIMAGE(), 0, userDict.getIMAGE().length));
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //选择图片
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(intent, REQUEST_PICTURE_CHOOSE);
            }
        });

        toolbar.inflateMenu(R.menu.main);//设置右上角的填充菜单
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.action_settings) {
                    if(popuRootView == null){
                        popuRootView = LayoutInflater.from(MainActivity.this).inflate(R.layout.columnchartdialog, null);
                    }
                    mColumnCharView = (ColumnChartView) popuRootView.findViewById(R.id.columnChartView);
                    setColumnDatas();
                    if(popupWindow == null){
                        popupWindow = new CustomerPopwindow(Color.TRANSPARENT, popuRootView);
                        popupWindow.setHeight(LayoutParams.MATCH_PARENT);
                        popupWindow.setWidth(LayoutParams.MATCH_PARENT);
                        popupWindow.setTouchable(true);
                        popupWindow.setOutsideTouchable(true);
                    }
                    popupWindow.showAsDropDown(toolbar);
                }
                return true;
            }
        });
    }

    private void setColumnDatas() {
        switch (dataType) {
            case DEFAULT_DATA:
                IS_NEGATIVE = false;                                            //设置反向标志位：不反向
                setColumnDatasByParams(1, 4, false, IS_NEGATIVE);               //设置数据：单子列 总八列 不堆叠 不反向
                break;
            case SUBCOLUMNS_DATA:
                IS_NEGATIVE = false;                                            //设置反向标志位：不反向
                setColumnDatasByParams(4, 8, false, IS_NEGATIVE);               //设置数据：四子列 总八列 不堆叠 不反向
                break;
            case NEGATIVE_SUBCOLUMNS_DATA:
                IS_NEGATIVE = true;                                             //设置反向标志位：反向
                setColumnDatasByParams(4, 8, false, IS_NEGATIVE);               //设置数据：四子列 总八列 不堆叠 反向
                break;
            case STACKED_DATA:
                IS_NEGATIVE = false;                                            //设置反向标志位：不反向
                setColumnDatasByParams(4, 8, true, IS_NEGATIVE);                //设置数据：四子列 总八列 堆叠 不反向
                break;
            case NEGATIVE_STACKED_DATA:
                IS_NEGATIVE = true;                                             //设置反向标志位：反向
                setColumnDatasByParams(4, 8, true, IS_NEGATIVE);                //设置数据：四子列 总八列 堆叠 反向
                break;
            default:
                IS_NEGATIVE = false;                                            //设置反向标志位：不反向
                setColumnDatasByParams(1, 8, false, IS_NEGATIVE);               //设置数据：单列 总八列 不堆叠 不反向
                break;
        }
    }

    private void setColumnDatasByParams(int numSubcolumns, int numColumns, boolean isStack, boolean isNegative) {
        List<Column> columns = new ArrayList<>();
        List<SubcolumnValue> values;
        //双重for循环给每个子列设置随机的值和随机的颜色
        for (int i = 0; i < numColumns; ++i) {
            values = new ArrayList<>();
            for (int j = 0; j < numSubcolumns; ++j) {
                //确定是否反向
                int negativeSign = getNegativeSign(isNegative);
                //根据反向值 设置列的值
                values.add(new SubcolumnValue((float) Math.random() * 50f * negativeSign + 5 * negativeSign, ChartUtils.pickColor()));
            }

            /*===== 柱状图相关设置 =====*/
            Column column = new Column(values);
            column.setHasLabels(isHasColumnLabels);                    //没有标签
            column.setHasLabelsOnlyForSelected(isColumnsHasSelected);  //点击只放大
            columns.add(column);
        }
        mColumnChartData = new ColumnChartData(columns);               //设置数据
        mColumnChartData.setStacked(isStack);                          //设置是否堆叠

        /*===== 坐标轴相关设置 类似于Line Charts =====*/
        if (isHasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (isHasAxesNames) {
                axisX.setName("Axis X");
                axisY.setName("Axis Y");
            }
            mColumnChartData.setAxisXBottom(axisX);
            mColumnChartData.setAxisYLeft(axisY);
        } else {
            mColumnChartData.setAxisXBottom(null);
            mColumnChartData.setAxisYLeft(null);
        }
        mColumnCharView.setColumnChartData(mColumnChartData);
    }

    private int getNegativeSign(boolean isNegative) {
        if (isNegative) {
            int[] sign = new int[]{-1, 1};                      //-1：反向 1：正向
            return sign[Math.round((float) Math.random())];     //随机确定子列正反
        }
        return 1;                                               //默认全为正向
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != MainActivity.this.RESULT_OK) {
            return;
        }

        String fileSrc = null;
        if (requestCode == REQUEST_PICTURE_CHOOSE) {
            if ("file".equals(data.getData().getScheme())) {
                // 有些低版本机型返回的Uri模式为file
                fileSrc = data.getData().getPath();
            } else {
                // Uri模型为content
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor cursor = MainActivity.this.getContentResolver().query(data.getData(), proj,
                        null, null, null);
                cursor.moveToFirst();
                int idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                fileSrc = cursor.getString(idx);
                cursor.close();
            }
            // 跳转到图片裁剪页面
            cropPicture(MainActivity.this, Uri.fromFile(new File(fileSrc)));
        }else if (requestCode == ImageUtil.REQUEST_CROP_IMAGE) {
            // 获取返回数据
            Bitmap bmp = data.getParcelableExtra("data");
            // 获取图片保存路径
            fileSrc = ImageUtil.getImagePath(MainActivity.this);
            // 获取图片的宽和高
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            mImage = BitmapFactory.decodeFile(fileSrc, options);

            // 压缩图片
            options.inSampleSize = Math.max(1, (int) Math.ceil(Math.max(
                    (double) options.outWidth / 1024f,
                    (double) options.outHeight / 1024f)));
            options.inJustDecodeBounds = false;
            mImage = BitmapFactory.decodeFile(fileSrc, options);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            //可根据流量及网络状况对图片进行压缩
            mImage.compress(Bitmap.CompressFormat.JPEG, 80, baos);
            mImageData = baos.toByteArray();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("imagebitmap", Base64.encodeToString(mImageData, Base64.DEFAULT));
            editor.commit();
            userDict.setIMAGE(mImageData);
            systemApplcation.setUserDict(userDict);
            imageView.setImageBitmap(mImage);

            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("USERNAME", userDict.getUSERNAME());
            hashMap.put("IMAGE", Base64.encodeToString(mImageData, Base64.DEFAULT));
            hashMap.put("OPERATE_TYPE", "3");
            querySaveImage(hashMap);
        }

    }

    private void querySaveImage(HashMap<String, Object> hashMap) {
        final CustomerProgress customerProgress =  new CustomerProgress(MainActivity.this,com.tianjian.slidingmenuteachingclient.R.style.customer_dialog);
        NetWorkHepler.postWsData("loginWs", "process", hashMap, new INetWorkCallBack() {
            SoapObject objectResult;
            @Override
            public void onResult(Object result) {
                customerProgress.dismissDialog(customerProgress);
                if(result == null){
                    ToastUtil.showToast(MainActivity.this, "更新头像失败！");
                }else if(result instanceof SoapObject) {
                    objectResult = (SoapObject) result;
                }else{
                    ToastUtil.showToast(MainActivity.this, "服务器连接失败！");
                    return;
                }
                InLoginSrvResponse response = new InLoginSrvResponse();
                try {
                    for(int i=0;i<objectResult.getPropertyCount();i++){
                        response.setProperty(i, objectResult.getProperty(i));
                    }
                } catch (Exception e) {
                    ToastUtil.showToast(MainActivity.this, "数据出错了，请重试！");
                }

                if("Y".equals(response.getErrorFlag())){
                    ToastUtil.showToast(MainActivity.this, "更新头像成功！");
                }else{
                    ToastUtil.showToast(MainActivity.this, "更新头像失败！");
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("确定要退出吗？");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SystemApplcation sys = (SystemApplcation)MainActivity.this.getApplication();
                    sys.exit();
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            toolbar.setTitle("主页");
            if(loginflag == 1){
                if(null!=systemApplcation.getHomePageStudentFragment()){
                    showFragment(systemApplcation.getHomePageStudentFragment());
                }else{
                    homepageFragment_stu = new HomePageStudentFragment();
                    systemApplcation.setHomePageStudentFragment(homepageFragment_stu);
                    showFragment(homepageFragment_stu);
                }
            }else if(loginflag == 2){
                if(null!=systemApplcation.getHomePageMentorFragment()){
                    showFragment(systemApplcation.getHomePageMentorFragment());
                }else{
                    homepageFragment_men = new HomePageMentorFragment();
                    systemApplcation.setHomePageMentorFragment(homepageFragment_men);
                    showFragment(homepageFragment_men);
                }
            }
        } else if (id == R.id.nav_gallery) {
            toolbar.setTitle("课件");
            if(null!=systemApplcation.getResourcesFragment()){
                showFragment(systemApplcation.getResourcesFragment());
            }else{
                resourcesFragment = new ResourcesFragment();
                systemApplcation.setResourcesFragment(resourcesFragment);
                showFragment(resourcesFragment);
            }
        } else if (id == R.id.nav_slideshow) {
            toolbar.setTitle("提问");
            if(loginflag == 1){
                if(null!=systemApplcation.getQuestionStudentFragment()){
                    showFragment(systemApplcation.getQuestionStudentFragment());
                }else{
                    questionsFragment_stu = new QuestionsStudentFragment();
                    systemApplcation.setQuestionStudentFragment(questionsFragment_stu);
                    showFragment(questionsFragment_stu);
                }
            }else if(loginflag == 2){
                if(null!=systemApplcation.getQuestionMentorFragment()){
                    showFragment(systemApplcation.getQuestionMentorFragment());
                }else{
                    questionsFragment_men = new QuestionsMentorFragment();
                    systemApplcation.setQuestionMentorFragment(questionsFragment_men);
                    showFragment(questionsFragment_men);
                }
            }
        } else if (id == R.id.nav_manage) {
            toolbar.setTitle("任务");
            if(loginflag == 1){
                if(null!=systemApplcation.getTasksStudentFragment()){
                    showFragment(systemApplcation.getTasksStudentFragment());
                }else{
                    tasksFragment_stu = new TasksStudentFragment();
                    systemApplcation.setTasksStudentFragment(tasksFragment_stu);
                    showFragment(tasksFragment_stu);
                }
            }else if(loginflag == 2){
                if(null!=systemApplcation.getTasksMentorFragment()){
                    showFragment(systemApplcation.getTasksMentorFragment());
                }else{
                    tasksFragment_men = new TasksMentorFragment();
                    systemApplcation.setTasksMentorFragment(tasksFragment_men);
                    showFragment(tasksFragment_men);
                }
            }
        } else if (id == R.id.nav_my) {
            toolbar.setTitle("我的");
            if(loginflag == 1){
                if(null!=systemApplcation.getUserInfoStudentFragment()){
                    showFragment(systemApplcation.getUserInfoStudentFragment());
                }else{
                    userInfoFragment_stu = new UserInfoStudentFragment();
                    systemApplcation.setUserInfoStudentFragment(userInfoFragment_stu);
                    showFragment(userInfoFragment_stu);
                }
            }else if(loginflag == 2){
                if(null!=systemApplcation.getUserInfoMentorFragment()){
                    showFragment(systemApplcation.getUserInfoMentorFragment());
                }else{
                    userInfoFragment_men = new UserInfoMentorFragment();
                    systemApplcation.setUserInfoMentorFragment(userInfoFragment_men);
                    showFragment(userInfoFragment_men);
                }
            }
        } else if (id == R.id.nav_share) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,ChangePwdActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_send) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("确定要退出吗？");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SystemApplcation sys = (SystemApplcation)MainActivity.this.getApplication();
                    sys.exit();
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showFragment(Fragment f) {
        if (fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, f);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /***
     * 裁剪图片
     * @param activity Activity
     * @param uri 图片的Uri
     */
    public void cropPicture(Activity activity, Uri uri) {
        Intent innerIntent = new Intent("com.android.camera.action.CROP");
        innerIntent.setDataAndType(uri, "image/*");
        innerIntent.putExtra("crop", "true");// 才能出剪辑的小方框，不然没有剪辑功能，只能选取图片
        innerIntent.putExtra("aspectX", 1); // 放大缩小比例的X
        innerIntent.putExtra("aspectY", 1);// 放大缩小比例的X   这里的比例为：   1:1
        innerIntent.putExtra("outputX", 320);  //这个是限制输出图片大小
        innerIntent.putExtra("outputY", 320);
        innerIntent.putExtra("return-data", true);
        // 切图大小不足输出，无黑框
        innerIntent.putExtra("scale", true);
        innerIntent.putExtra("scaleUpIfNeeded", true);
        File imageFile = new File(getImagePath(activity.getApplicationContext()));
        innerIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
        innerIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(innerIntent, REQUEST_CROP_IMAGE);
    }

    /**
     * 保存裁剪的图片的路径
     * @return
     */
    public String getImagePath(Context context){
        String path;

        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            path = context.getFilesDir().getAbsolutePath();
        } else {
            path =  Environment.getExternalStorageDirectory().getAbsolutePath() + "/msc/";
        }

        if(!path.endsWith("/")) {
            path += "/";
        }

        File folder = new File(path);
        if (folder != null && !folder.exists()) {
            folder.mkdirs();
        }
        path += "ifd.jpg";
        return path;
    }
}

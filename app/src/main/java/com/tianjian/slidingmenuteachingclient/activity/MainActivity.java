package com.tianjian.slidingmenuteachingclient.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import com.tianjian.slidingmenuteachingclient.view.CustomerProgress;

import org.ksoap2.serialization.SoapObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;

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

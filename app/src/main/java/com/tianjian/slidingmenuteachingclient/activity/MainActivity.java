package com.tianjian.slidingmenuteachingclient.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tianjian.slidingmenuteachingclient.R;
import com.tianjian.slidingmenuteachingclient.application.SystemApplcation;
import com.tianjian.slidingmenuteachingclient.bean.InLoginSrv.InLoginSrvOutputItem;
import com.tianjian.slidingmenuteachingclient.fragment.HomePageMentorFragment;
import com.tianjian.slidingmenuteachingclient.fragment.HomePageStudentFragment;
import com.tianjian.slidingmenuteachingclient.fragment.QuestionsMentorFragment;
import com.tianjian.slidingmenuteachingclient.fragment.QuestionsStudentFragment;
import com.tianjian.slidingmenuteachingclient.fragment.ResourcesFragment;
import com.tianjian.slidingmenuteachingclient.fragment.TasksMentorFragment;
import com.tianjian.slidingmenuteachingclient.fragment.TasksStudentFragment;
import com.tianjian.slidingmenuteachingclient.fragment.UserInfoMentorFragment;
import com.tianjian.slidingmenuteachingclient.fragment.UserInfoStudentFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
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

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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

        initData();
    }

    private void initData() {
        userName = (TextView) headerLayout.findViewById(R.id.userName);
        userPhone = (TextView) headerLayout.findViewById(R.id.userPhone);
        imageView = (ImageView) headerLayout.findViewById(R.id.imageView);

        userName.setText(userDict.getNAME());
        userPhone.setText("Phone:"+userDict.getUSERNAME());
        if(userDict.getIMAGE()!=null){
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(userDict.getIMAGE(), 0, userDict.getIMAGE().length));
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            if(null!=systemApplcation.getResourcesFragment()){
                showFragment(systemApplcation.getResourcesFragment());
            }else{
                resourcesFragment = new ResourcesFragment();
                systemApplcation.setResourcesFragment(resourcesFragment);
                showFragment(resourcesFragment);
            }
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

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
}

package com.tianjian.slidingmenuteachingclient.fragment;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tianjian.slidingmenuteachingclient.R;
import com.tianjian.slidingmenuteachingclient.activity.MainActivity;
import com.tianjian.slidingmenuteachingclient.bean.ScreenSize;


public class BaseFragment extends Fragment {
	private static MainActivity baseActivity;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	protected DisplayImageOptions options;
	protected ScreenSize screenSize;
	protected void loadImage(ImageView imageView, String url) {
		imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));
		// 一些相关的设置
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.ic_stub)
				.showImageForEmptyUri(R.drawable.ic_empty)
				.showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.bitmapConfig(Bitmap.Config.RGB_565).build();
		// 显示图片
		imageLoader.displayImage(url, imageView, options);
	}
	public MainActivity getMainActivity(){
		return (MainActivity) getActivity();
	}
	

	protected void  replaceBodyFragment(Fragment fragment){
		FragmentTransaction transaction = getMainActivity().getSupportFragmentManager().beginTransaction();
		transaction.addToBackStack(null);
		transaction.replace(R.id.mainFrameLayout, fragment);
		transaction.commitAllowingStateLoss();
	}
	
	public void getScreenSize() {
		if (screenSize == null) {
			screenSize = new ScreenSize();
		}
		DisplayMetrics dm = new DisplayMetrics();
		getMainActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int pixWidth = dm.widthPixels; // 得到宽度
		int pixHeight = dm.heightPixels; // 得到高度
		// 获得pop显示的宽度
		int width = pixWidth;
		// topLayout.setVisibility(View.INVISIBLE);
		// 获得状态栏高度
		Rect frame = new Rect();
		getMainActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		final int statusBarHeight = frame.top;
		// 获得pup显示的高度
		int height = (pixHeight) - statusBarHeight;
		screenSize.setWidth(width);
		screenSize.setHeight(height);
		screenSize.setStatusBarHeight(statusBarHeight);
	}
}

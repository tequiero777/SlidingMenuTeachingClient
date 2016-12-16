package com.tianjian.slidingmenuteachingclient.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.File;

public class ImageUtil {
	public final static int REQUEST_PICTURE_CHOOSE = 1;
	public final static int  REQUEST_CAMERA_IMAGE = 2;
	public final static int REQUEST_CROP_IMAGE = 3;
	protected static ImageLoader imageLoader = ImageLoader.getInstance();

	protected static DisplayImageOptions options;
	
	public static void loadImage(ImageView imageView,String uri,Context context){
		imageLoader.init(ImageLoaderConfiguration.createDefault(context));
		options = new DisplayImageOptions.Builder()
//		.showImageOnLoading(R.drawable.ic_stub)
//		.showImageForEmptyUri(R.drawable.ic_empty)
//		.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		.bitmapConfig(Bitmap.Config.RGB_565)
		.build();
		imageLoader.displayImage(uri, imageView, options);
	}
	
	public static void loadImage(ImageView imageView,String uri,SimpleImageLoadingListener simpleImageLoadingListener){
		options = new DisplayImageOptions.Builder()
	//	.showImageOnLoading(R.drawable.ic_stub)
	//	.showImageForEmptyUri(R.drawable.ic_empty)
	//	.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		.bitmapConfig(Bitmap.Config.RGB_565)
		.build();
		imageLoader.displayImage(uri, imageView, options,simpleImageLoadingListener);
	}
	
	/***
	 * 裁剪图片
	 * @param activity Activity
	 * @param uri 图片的Uri
	 */
	public static void cropPicture(Activity activity, Uri uri) {
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
		activity.startActivityForResult(innerIntent, REQUEST_CROP_IMAGE);
	}
	
	/**
	 * 保存裁剪的图片的路径
	 * @return
	 */
	public static String getImagePath(Context context){
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

package com.tianjian.slidingmenuteachingclient.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.tianjian.slidingmenuteachingclient.R;


public class CustomDialog extends Dialog { 
    private Context context = null;  
    private static CustomDialog customDialog = null;  
      
    public CustomDialog(Context context){  
        super(context);  
        this.context = context;  
    }  
      
    public CustomDialog(Context context, int theme) {  
        super(context, theme);  
    }  
      
    public static CustomDialog createDialog(Context context){  
        customDialog = new CustomDialog(context, R.style.CustomProgressDialog);
        customDialog.setContentView(R.layout.customprogressdialog);  
        customDialog.getWindow().getAttributes().gravity = Gravity.CENTER;  
          
        return customDialog;  
    }  
   
    public void onWindowFocusChanged(boolean hasFocus){  
          
        if (customDialog == null){  
            return;  
        }  
          
        ImageView imageView = (ImageView) customDialog.findViewById(R.id.loadingImageView);  
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();  
        animationDrawable.start();  
    }  
   
    /** 
     *  
     * [Summary] 
     *       setTitile 标题 
     * @param strTitle 
     * @return 
     * 
     */  
    public CustomDialog setTitile(String strTitle){  
        return customDialog;  
    }  
      
    /** 
     *  
     * [Summary] 
     *       setMessage 提示内容 
     * @param strMessage 
     * @return 
     * 
     */  
    public CustomDialog setMessage(String strMessage){  
        TextView tvMsg = (TextView)customDialog.findViewById(R.id.id_tv_loadingmsg);  
          
        if (tvMsg != null){  
            tvMsg.setText(strMessage);  
        }  
          
        return customDialog;  
    }  

}

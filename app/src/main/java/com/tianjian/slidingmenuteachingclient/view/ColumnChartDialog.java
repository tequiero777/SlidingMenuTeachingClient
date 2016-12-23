package com.tianjian.slidingmenuteachingclient.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import com.tianjian.slidingmenuteachingclient.R;
import com.tianjian.slidingmenuteachingclient.activity.MainActivity;

import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * Created by Ye on 2016/12/23.
 */

public class ColumnChartDialog {
        Context context;
        android.app.AlertDialog ad;
        TextView titleView;
        TextView messageView;
        LinearLayout buttonLayout;
        ColumnChartView columnChart;

        public ColumnChartDialog(Context context) {
            // TODO Auto-generated constructor stub
            this.context=context;
            ad=new android.app.AlertDialog.Builder(context).create();
            ad.show();
            //关键在下面的两行,使用window.setContentView,替换整个对话框窗口的布局
            Window window = ad.getWindow();
            window.setContentView(R.layout.columnchartdialog);
            titleView=(TextView)window.findViewById(R.id.title);

            columnChart = (ColumnChartView) window.findViewById(R.id.columnChartView);
        }
        public void setTitle(int resId)
        {
            titleView.setText(resId);
        }
        public void setTitle(String title) {
            titleView.setText(title);
        }
        public void setMessage(int resId) {
            messageView.setText(resId);
        } 	public void setMessage(String message)
        {
            messageView.setText(message);
        }
        public void dismiss() {
            ad.dismiss();
        }
}

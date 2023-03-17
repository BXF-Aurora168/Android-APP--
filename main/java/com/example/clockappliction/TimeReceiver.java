package com.example.clockappliction;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class TimeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

            Toast.makeText(context, "时间到了", Toast.LENGTH_SHORT).show();

//            AlertDialog.Builder builder = new AlertDialog.Builder(context);
//            builder.setTitle("事件闹钟：");
//            builder.setMessage("提醒您:");
//            builder.setIcon(R.mipmap.ic_launcher);
//            builder.setCancelable(false);            //点击对话框以外的区域是否让对话框消失
//            //设置正面按钮
//            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                }
//            });
//            AlertDialog dialog = builder.create();      //创建AlertDialog对象
//            dialog.show();                              //显示对话框
    }


}

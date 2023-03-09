package com.example.clockappliction;

import static android.icu.text.DateTimePatternGenerator.DAY;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.TimeZone;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class SetTimeActivity extends AppCompatActivity implements View.OnClickListener , TimePickerDialog.OnTimeSetListener {

    private int hour,minute;
    private TextView tv_time;
    private Button btn_send;
    private EditText et_remind;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);
        tv_time = findViewById(R.id.tv_time);
        findViewById(R.id.btn_time).setOnClickListener(this);
        btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);
        et_remind = (EditText) findViewById(R.id.et_remind);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btn_time){
            Calendar calendar = Calendar.getInstance();
            TimePickerDialog dialog = new TimePickerDialog(this,this,
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true);
            dialog.show();
        }
        else if (view.getId()==R.id.btn_send) {
            int hours = getHours();
            int minutes =getMinute();

            Intent intent = new Intent(this, AlarmReceiver.class);
            String mes = et_remind.getText().toString();
            intent.putExtra("mes",mes);
            PendingIntent sender = PendingIntent.getBroadcast(this,0, intent,PendingIntent.FLAG_MUTABLE);

            long firstTime = SystemClock.elapsedRealtime();    // 开机之后到现在的运行时间(包括睡眠时间)
            long systemTime = System.currentTimeMillis();

            android.icu.util.Calendar calendar = android.icu.util.Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
// 这里时区需要设置一下，不然会有8个小时的时间差
            calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            calendar.set(android.icu.util.Calendar.MINUTE,getMinute());
            calendar.set(android.icu.util.Calendar.HOUR_OF_DAY,getHours());
            calendar.set(android.icu.util.Calendar.SECOND,0);
            calendar.set(android.icu.util.Calendar.MILLISECOND,0);
// 选择的定时时间
            long selectTime = calendar.getTimeInMillis();
// 如果当前时间大于设置的时间，那么就从第二天的设定时间开始
            if(systemTime > selectTime) {
                Toast.makeText(this," ", Toast.LENGTH_SHORT).show();//设置的时间小于当前时间
                calendar.add(android.icu.util.Calendar.DAY_OF_MONTH,0);
                selectTime = calendar.getTimeInMillis();
            }
// 计算现在时间到设定时间的时间差
            long time = selectTime - systemTime;
            firstTime += time;
// 进行闹铃注册
            AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
            manager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    firstTime, DAY, sender);
            Log.i(TAG,"time ==== " + time +", selectTime ===== "
                    + selectTime + ", systemTime ==== " + systemTime +", firstTime === " + firstTime);
            Toast.makeText(this,"设置提醒闹铃成功! ", Toast.LENGTH_LONG).show();

//            Intent intent = new Intent(this,RemindActivity.class);
//
//            intent.putExtra("t_hour",hours);
//            intent.putExtra("t_minute",minutes);

        }
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hours_of_day, int minute) {
        String desc = String.format("你选择的时间是%d时%d分",hours_of_day,minute);
        tv_time.setText(desc);
        setHours(hours_of_day);
        setMinute(minute);
    }


    public int getHours(){
        return hour;
    }

    public int getMinute(){
        return minute;
    }
    public void setHours(int hour){
        this.hour=hour;
    }
    public void setMinute(int minute){
        this.minute=minute;
    }
}

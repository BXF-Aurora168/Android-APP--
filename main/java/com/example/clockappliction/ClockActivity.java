package com.example.clockappliction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.clockappliction.DataBase.CRUD;
import com.example.clockappliction.Information.Clock;
import java.util.Calendar;

public class ClockActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_dk_word,et_dk_summary;
    private Button btn_dk_add;
    private TextView tv_dk_keep,tv_dk_maxday,tv_dk_date;
    private static int jud ;
    static int KeepCou=1;
    static int MaxCou=1;
    //检查判断值为0--》允许打卡
    //检查判断值为1--》判断日期是否变更--》变更---判断设为0 -----允许打卡
    //                              》未变更---判断设为1----不允许打卡
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_in);

        tv_dk_keep = (TextView) findViewById(R.id.tv_dk_keep);
        tv_dk_maxday = (TextView) findViewById(R.id.tv_dk_maxday);
        tv_dk_date = (TextView) findViewById(R.id.tv_dk_date);


        tv_dk_keep.setText("坚持天数："+ KeepCou);
        tv_dk_maxday.setText("历史最长天数：" +MaxCou);
        tv_dk_date.setText("日期:" +getStringDate());
        et_dk_word = (EditText) findViewById(R.id.et_dk_word);
        et_dk_summary = (EditText) findViewById(R.id.et_dk_summary);

        btn_dk_add = (Button) findViewById(R.id.btn_dk_add);
        btn_dk_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view==findViewById(R.id.btn_dk_add)){
            CRUD crud = new CRUD(this);
            //获取输入框信息
            Intent intent = getIntent();
            String cid = intent.getStringExtra("cid");

            Clock clock1 = crud.getClockByDate(getStringDate());
            if (clock1!=null){
                jud=1;
            } else if (clock1==null) {
                jud=0;
            }

            if (jud==0){

                Clock clock =new Clock();
                clock.cid = cid;
                clock.keep = String.valueOf(KeepCou);
                clock.maxday = String.valueOf(MaxCou);
                clock.date = getStringDate();
                clock.word = et_dk_word.getText().toString();
                clock.summary = et_dk_summary.getText().toString();
                crud.insertClock(clock);
                Toast.makeText(this, "打卡成功！", Toast.LENGTH_SHORT).show();
                KeepCou++;
                MaxCou++;
            }else {
                Toast.makeText(this, "你今天已经打过卡了", Toast.LENGTH_SHORT).show();
            }

        }
    }
    public String getStringDate(){
        java.util.Calendar calendar = java.util.Calendar.getInstance();//取得当前时间的年月日 时分秒
        String year = String.valueOf(calendar.get(java.util.Calendar.YEAR));
        String month = String.valueOf(calendar.get(java.util.Calendar.MONTH)+1);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String date = year + "." + month + "." + day;
        return date;
    }
    public int getDate(){
        java.util.Calendar calendar = java.util.Calendar.getInstance();//取得当前时间的年月日 时分秒
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = (calendar.get(Calendar.MONTH)+1)*100;
        int year = calendar.get(Calendar.YEAR)*10000;
        int ymd = day+month+year;
        //20230308
        return ymd;
    }
}

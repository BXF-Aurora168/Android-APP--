package com.example.clockappliction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clockappliction.DataBase.CRUD;
import com.example.clockappliction.Information.Clock;
import com.example.clockappliction.adapter.ListViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_my_name;
    private String cid=null;
    private Button btn_mu_dk,btn_mu_remind;
    private RecyclerView mList;
    private List<Clock> mData;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btn_mu_dk = (Button) findViewById(R.id.btn_mu_dk);
        btn_mu_dk.setOnClickListener(this);

        btn_mu_remind = (Button) findViewById(R.id.btn_mu_remind);
        btn_mu_remind.setOnClickListener(this);

        tv_my_name = (TextView) findViewById(R.id.tv_my_name);
        Intent intent = getIntent();
        String st_id =intent.getStringExtra("st_id");
        String st_name =intent.getStringExtra("st_name");

        cid = st_id;
        tv_my_name.setText("欢迎使用打卡APP:"+ st_name +"!");
        //
        mList = (RecyclerView) this.findViewById(R.id.menu_recycler_view);
        initData();
        //
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_menu);
        btn_mu_dk = (Button) findViewById(R.id.btn_mu_dk);
        btn_mu_dk.setOnClickListener(this);

        btn_mu_remind = (Button) findViewById(R.id.btn_mu_remind);
        btn_mu_remind.setOnClickListener(this);

        tv_my_name = (TextView) findViewById(R.id.tv_my_name);
        Intent intent = getIntent();
        String st_id =intent.getStringExtra("st_id");
        String st_name =intent.getStringExtra("st_name");

        cid = st_id;
        tv_my_name.setText("欢迎使用打卡APP!");
        //
        mList = (RecyclerView) this.findViewById(R.id.menu_recycler_view);
        initData();
        //
    }

    //view加入数据
    private void initData() {
        mData = new ArrayList<>();
        CRUD crud = new CRUD(this);
        //导入数据

        for (int i = 1; i <= crud.getCountClock(); i++) {

            Clock data = new Clock();
            Clock Data=crud.getClockByKeyWord(i);
            data.date= Data.date;
            data.word= "关键字:"+Data.word;
            data.summary= "总结:"+Data.summary;
            data.keep= "坚持天数:"+Data.keep;
            mData.add(data);
        }
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mList.setLayoutManager(linearLayoutManager);
        //创建适配器
        ListViewAdapter adapter = new ListViewAdapter(mData);
        //设置到rview里
        mList.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {


        if (view==findViewById(R.id.btn_mu_dk)){
            Intent intent = new Intent(this,ClockActivity.class);
            intent.putExtra("cid",cid);
            startActivity(intent);
        } else if (view==findViewById(R.id.btn_mu_remind)) {
            Intent intent = new Intent(this,SetTimeActivity.class);

            startActivity(intent);
        }

    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu2,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.back_view:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}

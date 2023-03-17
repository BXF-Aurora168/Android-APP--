package com.example.clockappliction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clockappliction.DataBase.CRUD;
import com.example.clockappliction.Information.Clock;
import com.example.clockappliction.adapter.ListViewAdapter;
import com.example.clockappliction.adapter.ListViewAdapter2;

import java.util.ArrayList;
import java.util.List;

public class A3Activity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView mList;
    private List<Clock> mData;
    private TextView mNum;
    int cou = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a3);
        mList = (RecyclerView) this.findViewById(R.id.a3_RecycleView);
        mNum  = this.findViewById(R.id.tv_a3_num);
        initData();

        mNum.setText("发表总次数: "+cou);
    }

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
            cou++;
        }
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mList.setLayoutManager(linearLayoutManager);
        //创建适配器
        ListViewAdapter2 adapter = new ListViewAdapter2(mData);
        //设置到rview里
        mList.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu2,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.back_view:
                Intent intent = new Intent(this,InfoActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
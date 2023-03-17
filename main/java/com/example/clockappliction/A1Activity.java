package com.example.clockappliction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.clockappliction.DataBase.CRUD;
import com.example.clockappliction.Information.Clock;
import com.example.clockappliction.adapter.ListViewAdapter;
import com.example.clockappliction.DataBase.DBHelper;
import java.util.ArrayList;
import java.util.List;
public class A1Activity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView mList;
    private List<Clock> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a1);
        mList = (RecyclerView) this.findViewById(R.id.a1_RecycleView);

        initData();
    }

    private void initData() {

        mData = new ArrayList<>();
        CRUD crud = new CRUD(this);

        Intent intent = getIntent();
        String t_word = intent.getStringExtra("s1_text");

        ArrayList<Clock> list1 = crud.getClockByWord(t_word);

        //导入数据
        for (int i = 0; i < list1.size() ; i++) {

            Clock data = new Clock();
            Clock Data=list1.get(i);
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

    }
}
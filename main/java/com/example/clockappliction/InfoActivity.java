package com.example.clockappliction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener {

    Button mBtn_search1,mBtn_search2,mBtn_search3;
    EditText mEt_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        mBtn_search1 = findViewById(R.id.btn_teacher_search1);
        mBtn_search2 = findViewById(R.id.btn_teacher_search2);
        mBtn_search3 = findViewById(R.id.btn_teacher_search3);
        mBtn_search1.setOnClickListener(this);
        mBtn_search2.setOnClickListener(this);
        mBtn_search3.setOnClickListener(this);
        mEt_search   = (EditText) findViewById(R.id.et_teacher_search);
    }

    @Override
    public void onClick(View view) {

        String search_text = mEt_search.getText().toString();

        Intent intent = null;
        switch (view.getId()){
            case R.id.btn_teacher_search1:
                intent = new Intent(this, A1Activity.class);
                intent.putExtra("s1_text",search_text);
                startActivity(intent);
                break;
            case R.id.btn_teacher_search2:
                intent = new Intent(this, A2Activity.class);
                intent.putExtra("s2_text",search_text);
                startActivity(intent);
                break;
            case R.id.btn_teacher_search3:
                intent = new Intent(this, A3Activity.class);
                startActivity(intent);
                break;
        }

    }

}
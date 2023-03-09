package com.example.clockappliction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.clockappliction.DataBase.CRUD;
import com.example.clockappliction.Information.Student;

public class RegActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_reg_add;

    private EditText et_reg_id,et_reg_name,et_reg_grade,et_reg_phone,et_reg_password;
    private int keys  = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_reg_add = (Button) findViewById(R.id.btn_reg_add);
        btn_reg_add.setOnClickListener(this);

        et_reg_id = (EditText) findViewById(R.id.et_reg_id);
        et_reg_name = (EditText) findViewById(R.id.et_reg_name);
        et_reg_grade = (EditText) findViewById(R.id.et_reg_grade);
        et_reg_phone = (EditText) findViewById(R.id.et_reg_phone);
        et_reg_password = (EditText) findViewById(R.id.et_reg_password);

        Intent intent = getIntent();
        keys = intent.getIntExtra("keys",0);
        CRUD crud = new CRUD(this);
        Student student = new Student();
        student = crud.getStudentByKeyWord(keys);
        //从数据库中得到本来的数据
        et_reg_id.setText(student.id);
        et_reg_name.setText(student.name);
        et_reg_grade.setText(student.grade);
        et_reg_phone.setText(student.phone);
        et_reg_password.setText(student.password);
    }

    @Override
    public void onClick(View view) {

        if (view == findViewById(R.id.btn_reg_add)){

            CRUD crud = new CRUD(this);
            Student student = new Student();
            //接收输入框的数据
            student.keyword = keys;
            student.id = et_reg_id.getText().toString();
            student.name = et_reg_name.getText().toString();
            student.grade = et_reg_grade.getText().toString();
            student.phone = et_reg_phone.getText().toString();
            student.password = et_reg_password.getText().toString();

            keys = crud.insertStudent(student);

            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        }
    }


}

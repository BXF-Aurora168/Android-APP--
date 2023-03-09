package com.example.clockappliction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clockappliction.DataBase.CRUD;
import com.example.clockappliction.Information.Student;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_login, btn_reg;

    private EditText et_log_id,et_log_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        btn_reg = (Button) findViewById(R.id.btn_reg);
        btn_reg.setOnClickListener(this);

        et_log_id = (EditText) findViewById(R.id.et_log_id);
        et_log_password = (EditText) findViewById(R.id.et_log_password);

    }

    @Override
    public void onClick(View view) {

        if (view == findViewById(R.id.btn_login)){

            Student student = new Student();
            CRUD crud = new CRUD(this);
            //获取输入框的学生信息
            student.id = et_log_id.getText().toString();
            student.password = et_log_password.getText().toString();
            //通过id获取数据库学生信息
            Student studentData =crud.getStudentById(student.id);
            //登录
            if (student.id.equals(studentData.id)){

                if (student.password.equals(studentData.password)){
                    student.name= studentData.name;
                    Intent intent =new Intent(this,MenuActivity.class);
                    intent.putExtra("st_id",student.id);
                    intent.putExtra("st_name",student.name);
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "密码不正确", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "学号不正确", Toast.LENGTH_SHORT).show();
            }

        } else if (view == findViewById(R.id.btn_reg)) {
            //跳转到注册页面
            Intent intent = new Intent(this,RegActivity.class);
            intent.putExtra("keys",0);
            startActivity(intent);
        }


    }

}

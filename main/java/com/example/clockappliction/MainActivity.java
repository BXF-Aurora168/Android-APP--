package com.example.clockappliction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clockappliction.DataBase.CRUD;
import com.example.clockappliction.Information.Student;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_login, btn_reg;

    private EditText et_log_id,et_log_password;
    private CheckBox cb_save1;
    private String username,password;

    SharedPreferences sp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = this.getSharedPreferences("userinfo", Context.MODE_PRIVATE);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        btn_reg = (Button) findViewById(R.id.btn_reg);
        btn_reg.setOnClickListener(this);

        et_log_id = (EditText) findViewById(R.id.et_log_id);
        et_log_password = (EditText) findViewById(R.id.et_log_password);

        cb_save1 = (CheckBox) findViewById(R.id.cb_save1);

        if (sp.getBoolean("CheckBoxLogin", false)) {
            et_log_id.setText(sp.getString("username", null));
            et_log_password.setText(sp.getString("password", null));
            cb_save1.setChecked(true);
        }else {
            et_log_id.setText(sp.getString("username", username));
            et_log_password.setText(sp.getString("password", password));
            cb_save1.setChecked(true);
        }


    }

    @Override
    public void onClick(View view) {

        username=et_log_id.getText().toString();
        password=et_log_password.getText().toString();


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

                    boolean CheckBoxLogin = cb_save1.isChecked();
                    SharedPreferences.Editor editor = sp.edit();
                    if (CheckBoxLogin) {
                        editor.putString("username", username);
                        editor.putString("password", password);
                        editor.putBoolean("auto", true);
                    }else {
                        editor.putString("username", null);
                        editor.putString("password", null);
                        editor.putBoolean("auto", false);
                    }
                    editor.commit();

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
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.teacher_view:
                Toast.makeText(this, "转换到教师登录页面！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,TeacherActivity.class);
                startActivity(intent);
                break;
            case R.id.student_view:
                Toast.makeText(this, "已经是学生登录页面！", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}

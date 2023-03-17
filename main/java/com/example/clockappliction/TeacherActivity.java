package com.example.clockappliction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Toast;

import com.example.clockappliction.Information.Student;

public class TeacherActivity extends AppCompatActivity implements View.OnClickListener  {

    private Button mBtn_log;
    private EditText eText_id,eText_password;
    private CheckBox cb_save2;
    private String username,password;

    SharedPreferences sp1 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        sp1 = this.getSharedPreferences("teacherinfo", Context.MODE_PRIVATE);

        mBtn_log = findViewById(R.id.btn_teacher_login);
        mBtn_log.setOnClickListener(this);

        eText_id = findViewById(R.id.et_teacher_id);
        eText_password = findViewById(R.id.et_teacher_password);

        cb_save2 = (CheckBox) findViewById(R.id.cb_save2);

        if (sp1.getBoolean("CheckBoxLogin", false)) {
            eText_id.setText(sp1.getString("username", null));
            eText_password.setText(sp1.getString("password", null));
            cb_save2.setChecked(true);
        }else {
            eText_id.setText(sp1.getString("username", username));
            eText_password.setText(sp1.getString("password", password));
            cb_save2.setChecked(true);
        }
    }

    @Override
    public void onClick(View view) {

        username=eText_id.getText().toString();
        password=eText_password.getText().toString();


        if (view == findViewById(R.id.btn_teacher_login)){
            //获取输入框的学生信息
            Student student = new Student();
            student.id = eText_id.getText().toString();
            student.password = eText_password.getText().toString();
            if (student.id.equals("teacher")){
                if (student.password.equals("123")){

                    boolean CheckBoxLogin = cb_save2.isChecked();
                    SharedPreferences.Editor editor = sp1.edit();
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
                    Intent intent = new Intent(this,InfoActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "账号错误", Toast.LENGTH_SHORT).show();
            }

        }

    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.teacher_view:
                Toast.makeText(this, "已经是教师登录页面！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.student_view:
                Toast.makeText(this, "转换到学生登录页面！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
package com.example.xiaozhu.helloworld;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UserInforDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_detail);

        final EditText name = (EditText)findViewById(R.id.edit_text_name);
        final EditText id = (EditText)findViewById(R.id.edit_text_id);
        final EditText department = (EditText)findViewById(R.id.edit_department);
        final EditText authority = (EditText)findViewById(R.id.edit_text_authrity);
        final EditText mail = (EditText)findViewById(R.id.edit_text_mail);
        final EditText phone = (EditText)findViewById(R.id.edit_text_phone);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_show_user_info);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab_save);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //获得输入
                String user_name = name.getText().toString();
                String user_department = department.getText().toString();
                int user_workNumber = Integer.getInteger(id.getText().toString());
                String user_authority = authority.getText().toString();
                String user_mail = mail.getText().toString();
                String user_phone = phone.getText().toString();

                //保存到数据库
                UserLitepal userLitepal = new UserLitepal();
                userLitepal.setName(user_name);
                userLitepal.setDepartment(user_department);
                userLitepal.setAuthority(user_authority);
                userLitepal.setMail(user_mail);
                userLitepal.setTelephone(user_phone);
                userLitepal.setWorkNumber(user_workNumber);









                finish();
            }
        });
    }
}

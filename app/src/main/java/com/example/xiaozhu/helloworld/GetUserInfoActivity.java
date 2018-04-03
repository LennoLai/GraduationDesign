package com.example.xiaozhu.helloworld;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GetUserInfoActivity extends AppCompatActivity {

    public static final int TAKE_PHOTO = 1;
    private Uri imageUri;
    byte[] headShot;

    EditText name;
    EditText id;
    EditText department;
    EditText authority;
    EditText mail;
    EditText phone;
    UserLitepal userLitepal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_user_info);



        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_show_user_info);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        //拍照设置
        ImageView take_photo = (ImageView)findViewById(R.id.take_photo);
        take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建file对象，用于储存拍照后的图片
                File outputImage = new File(getExternalCacheDir(),"output_image.jpg");
                try{
                    if (outputImage.exists()){
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24){
                    imageUri = FileProvider.getUriForFile(MyApplication.getContext(),"com.example.xiaozhu.helloworld.fileprovider",outputImage);
                }else {
                    imageUri = Uri.fromFile(outputImage);
                }

                //启动相机程序
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);

            }
        });

        //悬浮按键
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab_save);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = (EditText)findViewById(R.id.edit_text_name);
                id = (EditText)findViewById(R.id.edit_text_id);
                department = (EditText)findViewById(R.id.edit_department);
                authority = (EditText)findViewById(R.id.edit_text_authrity);
                mail = (EditText)findViewById(R.id.edit_text_mail);
                phone = (EditText)findViewById(R.id.edit_text_phone);

                String user_name = null;
                String user_department = null;
                int user_id = 0;
                String user_authority = null;
                String user_mail = null;
                String user_phone = null;

                //获得输入
                try{
                    user_name = name.getText().toString();
                    user_department = department.getText().toString();
                    user_id = Integer.parseInt(id.getText().toString());
                    user_authority = authority.getText().toString();
                    user_mail = mail.getText().toString();
                    user_phone = phone.getText().toString();
                }catch (Exception e){
                    e.printStackTrace();
                }

                //保存到数据库
                userLitepal = new UserLitepal();
                userLitepal.setName(user_name);
                userLitepal.setDepartment(user_department);
                userLitepal.setAuthority(user_authority);
                userLitepal.setMail(user_mail);
                userLitepal.setTelephone(user_phone);
                userLitepal.setId(user_id);
                userLitepal.setHeadshot(headShot);
                if (user_name != null && user_department != null && user_authority != null && user_mail!= null &&
                        user_phone != null && user_id != 0 && headShot.length != 0) {
                    userLitepal.save();

                }


                finish();



          }
     });
    }
    //处理拍摄过后的照片
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK){
                    try{
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        ImageView imageView = (ImageView)findViewById(R.id.picture);
                        imageView.setImageBitmap(bitmap);
                        headShot = UserLitepal.img(bitmap);
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
                break;
                default: break;
        }
    }
}

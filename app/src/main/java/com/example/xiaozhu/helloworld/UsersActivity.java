package com.example.xiaozhu.helloworld;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class UsersActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        //加载toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //加载nav和导航按钮HomeAsUp
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView)findViewById(R.id.nav_view);
        final ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24px);
        }

        //nav的菜单项设立监听事件
        navView.setCheckedItem(R.id.nav_users);  //默认选项
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_statistics:
                        Intent intent = new Intent(MyApplication.getContext(),StatisticsActivity.class);
                        startActivity(intent);
                        mDrawerLayout.closeDrawers();
                        finish();
                        break;
                    case R.id.nav_users:
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_about_us:
                        Intent intent1 = new Intent(MyApplication.getContext(),AboutUsActivity.class);
                        startActivity(intent1);
                        mDrawerLayout.closeDrawers();
                        break;
                    default:
                }
                return true;
            }
        });


        //设置浮动按钮点击事件
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyApplication.getContext(),UserInforDetailActivity.class);
                startActivity(intent);
            }
        });
        /*
        LitePal.getDatabase();
        UserLitepal userLitepal = new UserLitepal();
        userLitepal.setName("庄周");
        userLitepal.setHeadshot(getResources(),R.drawable.zhuangzhou);
        userLitepal.save();
        */
        //删除数据库所有内容
        DataSupport.deleteAll(UserLitepal.class);


        //recyclerView
        initUserInfo();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        UserAdapter adapter = new UserAdapter(UserInfoList);
        recyclerView.setAdapter(adapter);

    }



    //根据数据库初始化recycleView

    private List<UserInfo> UserInfoList = new ArrayList<>();
    List<UserLitepal> users = DataSupport.findAll(UserLitepal.class);
    private void initUserInfo(){
        for (UserLitepal user : users){
            Bitmap bitmap  = BitmapFactory.decodeByteArray(user.getHeadshot(), 0, user.getHeadshot().length);
            UserInfo userInfo = new UserInfo(user.getName(),bitmap);
            UserInfoList.add(userInfo);
        }

    }


    //加载toolbar上的菜单(搜索)
    private void setSearchView(Menu menu){
        MenuItem item = menu.getItem(0);
        SearchView searchView = new SearchView(this);
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("搜索");
        searchView.setSubmitButtonEnabled(true);
        item.setActionView(searchView);
        //设置输入文字的样式
        TextView textView = (TextView)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        textView.setHintTextColor(Color.WHITE);
        textView.setTextColor(Color.WHITE);
        //设置关闭按钮
        ImageView closeButton = (ImageView)searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        closeButton.setImageResource(R.drawable.ic_backup_24px);//设置为自定义的Icon
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        setSearchView(menu);
        return true;
    }

    //toolbar上菜单监听事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }




/*
  //初始化用户列表
    //recyclerView
    private List<UserInfo> UserInfoList = new ArrayList<>();

    private void initUserInfo(){
        for(int i = 0;i < 4; i++){
            UserInfo Xiaoming1 = new UserInfo("张一",R.drawable.ic_person_24px,R.drawable.ic_line_24px);
            UserInfoList.add(Xiaoming1);
            UserInfo Xiaoming2 = new UserInfo("张一",R.drawable.ic_person_24px,R.drawable.ic_line_24px);
            UserInfoList.add(Xiaoming2);
            UserInfo Xiaoming3 = new UserInfo("张一",R.drawable.ic_person_24px,R.drawable.ic_line_24px);
            UserInfoList.add(Xiaoming3);
            UserInfo Xiaoming4 = new UserInfo("张一",R.drawable.ic_person_24px,R.drawable.ic_line_24px);
            UserInfoList.add(Xiaoming4);
            UserInfo Xiaoming5 = new UserInfo("张一",R.drawable.ic_person_24px,R.drawable.ic_line_24px);
            UserInfoList.add(Xiaoming5);
            UserInfo Xiaoming6 = new UserInfo("张一",R.drawable.ic_person_24px,R.drawable.ic_line_24px);
            UserInfoList.add(Xiaoming6);
            UserInfo Xiaoming7 = new UserInfo("张一",R.drawable.ic_person_24px,R.drawable.ic_line_24px);
            UserInfoList.add(Xiaoming7);
            UserInfo Xiaoming8 = new UserInfo("张一",R.drawable.ic_person_24px,R.drawable.ic_line_24px);
            UserInfoList.add(Xiaoming8);
            UserInfo Xiaoming9 = new UserInfo("张一",R.drawable.ic_person_24px,R.drawable.ic_line_24px);
            UserInfoList.add(Xiaoming9);
            UserInfo Xiaoming10 = new UserInfo("张一",R.drawable.ic_person_24px,R.drawable.ic_line_24px);
            UserInfoList.add(Xiaoming10);
        }
    }

    private String name;
    private Bitmap headshot;
    private String department;
    private String authority;
    private int workNumber;
    private String telephone;
    private String mail;
    */





}

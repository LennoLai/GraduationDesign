package com.example.xiaozhu.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class PeopleManagementActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    PeopleManagementAdapter adapter;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_management);

        //加载toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //加载nav和导航按钮HomeAsUp
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        final NavigationView navView = (NavigationView)findViewById(R.id.nav_view);
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
                Intent intent = new Intent(MyApplication.getContext(),GetUserInfoActivity.class);
                startActivity(intent);
            }
        });

        LitePal.getDatabase();


        //删除数据库所有内容
        //DataSupport.deleteAll(UserLitepal.class);


        //recyclerView
        initUserInfo();
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        //recyclerView的缓存问题
        if(recyclerView.getRecycledViewPool()!=null){
            recyclerView.getRecycledViewPool().setMaxRecycledViews(0, 10);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PeopleManagementAdapter();
        adapter.appendList(UserInfoList);
        recyclerView.setAdapter(adapter);

    }


    //根据数据库初始化recycleView
    private List<UserInfo> UserInfoList = new ArrayList<UserInfo>();
      List<UserLitepal> users = DataSupport.findAll(UserLitepal.class);
      private void initUserInfo(){
        for (UserLitepal user : users){
            Log.d("ILOVEYOU", user.getName());
            try {
                Bitmap bitmap = BitmapFactory.decodeByteArray(user.getHeadshot(), 0, user.getHeadshot().length);
                UserInfo userInfo = new UserInfo(user.getName(), bitmap);
                UserInfoList.add(userInfo);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }



    //加载toolbar上的菜单(搜索)
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        final MenuItem item = menu.findItem(R.id.searchView);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

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
        closeButton.setImageResource(R.drawable.ic_close_24px);//设置为自定义的Icon
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


    @Override
    protected void onResume(){
        Log.d("ILOVEYOU", "Resume success");
        super.onResume();
        if(adapter != null){
            UserInfoList.clear();
            List<UserLitepal> users = DataSupport.findAll(UserLitepal.class);
                for (UserLitepal user : users){
                    try {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(user.getHeadshot(), 0, user.getHeadshot().length);
                        UserInfo userInfo = new UserInfo(user.getName(), bitmap);
                        UserInfoList.add(userInfo);
                        adapter = new PeopleManagementAdapter();
                        adapter.appendList(UserInfoList);
                        recyclerView.setAdapter(adapter);
                        Log.d("ILOVEYOU", user.getName());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
        }
    }



}

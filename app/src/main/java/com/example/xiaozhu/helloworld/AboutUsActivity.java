package com.example.xiaozhu.helloworld;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public  class AboutUsActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        //加载toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //加载nav和导航按钮HomeAsUp
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout_about_us);
        NavigationView navView = (NavigationView)findViewById(R.id.nav_view);
        final ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24px);
        }

        //nav的菜单项设立监听事件
        navView.setCheckedItem(R.id.nav_about_us);  //默认选项
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
                        Intent intent1 = new Intent(MyApplication.getContext(),PeopleManagementActivity.class);
                        mDrawerLayout.closeDrawers();
                        finish();
                        break;
                    case R.id.nav_about_us:
                        mDrawerLayout.closeDrawers();
                        break;
                    default:
                }
                return true;
            }
        });
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
}

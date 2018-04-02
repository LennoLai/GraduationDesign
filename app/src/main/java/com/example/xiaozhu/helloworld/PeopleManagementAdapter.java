package com.example.xiaozhu.helloworld;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by xiaozhu on 2017/11/7.
 */
//用户列表的adapter
public class PeopleManagementAdapter extends RecyclerView.Adapter<PeopleManagementAdapter.ViewHolder>{
    private List<UserInfo> mUserList;


    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView userImage;

        TextView userName;

        public ViewHolder(View view){
            super(view);
            userImage =(ImageView)view.findViewById(R.id.person_image);
            userName =(TextView)view.findViewById(R.id.person_name);

        }
    }

    public PeopleManagementAdapter(List<UserInfo> userList){
        mUserList = userList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.people_management_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        UserInfo userInfo = mUserList.get(position);
        holder.userImage.setImageBitmap(userInfo.getImageId_user());

        holder.userName.setText(userInfo.getName());

    }

    @Override
    public int getItemCount(){
        return  mUserList.size();
    }
}

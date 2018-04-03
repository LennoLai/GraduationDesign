package com.example.xiaozhu.helloworld;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaozhu on 2017/11/7.
 */
//用户列表的adapter
public class PeopleManagementAdapter extends RecyclerView.Adapter<PeopleManagementAdapter.ViewHolder> implements Filterable{
    private List<UserInfo> mUserList;
    private List<UserInfo>mFilterList;

    public void appendList(List<UserInfo> list){
        mUserList = list;
        //初始化filterList
        mFilterList = list;
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView userImage;

        TextView userName;

        public ViewHolder(View view){
            super(view);
            userImage =(ImageView)view.findViewById(R.id.person_image);
            userName =(TextView)view.findViewById(R.id.person_name);

        }
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.people_management_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        UserInfo userInfo = mFilterList.get(position);
        holder.userImage.setImageBitmap(userInfo.getImageId_user());

        holder.userName.setText(userInfo.getName());

    }

    @Override
    public int getItemCount(){
        return  mFilterList.size();
    }


    //以下均为Filterable接口的实现
    @Override
    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()){
                    //没有过滤的内容，则使用源数据
                    mFilterList = mUserList;
                }else {
                    List<UserInfo> filteredList = new ArrayList<>();
                    for (UserInfo userInfo : mUserList){
                        //这里根据需求，添加匹配规则
                        if (userInfo.getName().contains(charString)){
                            filteredList.add(userInfo);
                        }
                    }

                    mFilterList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilterList;
                return filterResults;
            }

            //把过滤后的值返回出来
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilterList = (ArrayList<UserInfo>)filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}

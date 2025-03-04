package com.cn.miraclestar.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cn.miraclestar.FriendHomePageActivity;
import com.cn.miraclestar.R;
import com.cn.miraclestar.constants.UrlConstant;
import com.cn.miraclestar.dto.RequestFriendRelationshipDto;


import java.util.ArrayList;
import java.util.List;

public class ListFriendAdapter extends RecyclerView.Adapter<ListFriendAdapter.FriendViewHolder> {
    private List<RequestFriendRelationshipDto> _list_friend_data;
    private Context context;
    //添加布局监视

    public ListFriendAdapter( Context context,List<RequestFriendRelationshipDto> _list_friend_data) {
        this.context = context;
        this._list_friend_data = _list_friend_data;
    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_friend, parent, false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {
        // 这里可以根据需求绑定数据到视图
        // 例如：holder.textView.setText(friendList.get(position));

        Resources resources = context.getResources();

        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    v.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.light_gray, null));
//                        return false;
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    v.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.white, null));
//                        return true;
                }
                if(event.getAction() == MotionEvent.ACTION_CANCEL){
                    v.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.white, null));
//                        return true;
                }
                return false;
            }
        });

        TextView username = holder.itemView.findViewById(R.id.list_friend_name_text);
        username.setText(_list_friend_data.get(position).getUsername());

        ImageView avatar = holder.itemView.findViewById(R.id.list_friend_avatar_image);
        Glide.with(context)
                .load(UrlConstant.AVATAR_URL+_list_friend_data.get(position).getAvatarUrl())
                .into(avatar);

        TextView friendTime = holder.itemView.findViewById(R.id.list_friend_date_text);
        friendTime.setText(_list_friend_data.get(position).getFriendAt());

        //布局传值,事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FriendHomePageActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return _list_friend_data.size();
    }

    public static class FriendViewHolder extends RecyclerView.ViewHolder {
        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            // 这里可以初始化视图组件
            // 例如：TextView textView = itemView.findViewById(R.id.friend_name);
        }
    }
}

package com.cn.miraclestar.adapter;

import android.annotation.SuppressLint;
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
import com.cn.miraclestar.SendMessageActivity;
import com.cn.miraclestar.constants.UrlConstant;
import com.cn.miraclestar.dto.RequestFriendRelationshipDto;

import java.util.List;

public class SearchFriendAdapter extends RecyclerView.Adapter<SearchFriendAdapter.SearchFriendViewHolder> {

    private RequestFriendRelationshipDto _search_friend_data;
    private Context context;
    public SearchFriendAdapter(Context context,RequestFriendRelationshipDto _search_friend_data){
        this.context = context;
        this._search_friend_data = _search_friend_data;
    }

    @NonNull
    @Override
    public SearchFriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_friend_data,parent,false);
        return new SearchFriendViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SearchFriendViewHolder holder, int position) {
        //添加基础样式变化事件
        Resources resources = context.getResources();

        //初始化头像
        ImageView avatar = holder.itemView.findViewById(R.id.search_add_friend_avatar_image);
        Glide.with(context)
                .load(UrlConstant.AVATAR_URL + _search_friend_data.getAvatarUrl())
                        .error(R.mipmap.avatar_err)
                                .into(avatar);

        TextView name = holder.itemView.findViewById(R.id.search_add_friend_name_text);
        name.setText(_search_friend_data.getUsername());

        TextView id = holder.itemView.findViewById(R.id.search_add_friend_id_text);
        id.setText(resources.getString(R.string.app_id_string)+_search_friend_data.getUserId().toString());

        //添加点击事件
        holder.itemView.setOnClickListener(v->{
            Intent intent = new Intent(context, FriendHomePageActivity.class);
            intent.putExtra("userId",_search_friend_data.getUserId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return (_search_friend_data.getUsername() == null || _search_friend_data.getUsername().isEmpty())?0:1;
    }

    public static class SearchFriendViewHolder extends RecyclerView.ViewHolder{
        public SearchFriendViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

package com.cn.miraclestar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cn.miraclestar.R;
import com.cn.miraclestar.SeeMessageActivity;
import com.cn.miraclestar.constants.UrlConstant;
import com.cn.miraclestar.pojo.Result;
import com.cn.miraclestar.service.logic_service.callbacks.FriendRelationshipCallBack;
import com.cn.miraclestar.service.sql_service.FriendRelationshipService;
import com.cn.miraclestar.service.sql_service.FriendRequestService;
import com.cn.miraclestar.service.sql_service.callbacks.FriendRequestLongCallBack;
import com.cn.miraclestar.service.sql_service.impl.FriendRelationshipServiceImpl;
import com.cn.miraclestar.service.sql_service.impl.FriendRequestServiceImpl;
import com.cn.miraclestar.sql.entity.FriendRelationship;
import com.cn.miraclestar.sql.entity.FriendRequest;
import com.cn.miraclestar.utils.TokenManagerUtil;

import org.w3c.dom.Text;

import java.util.List;

public class SeeMessageAdapter extends RecyclerView.Adapter<SeeMessageAdapter.SeeMessageViewHolder> {

    private Context context;
    private List<FriendRequest> _friend_request_list;
    private FriendRequestService _friend_request_service ;
    private FriendRelationshipService friend_relationship_service;
    private TokenManagerUtil _token_manager_util;

    public SeeMessageAdapter(Context context,List<FriendRequest> friendRequestList){
        this.context = context;
        this._friend_request_list = friendRequestList;
        this._token_manager_util = new TokenManagerUtil(context);
        this._friend_request_service = new FriendRequestServiceImpl();
        this.friend_relationship_service = new FriendRelationshipServiceImpl();
    }

    @NonNull
    @Override
    public SeeMessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.see_message_item,parent,false);
        return new SeeMessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeeMessageViewHolder holder, int position) {

        ImageView avatar = holder.itemView.findViewById(R.id.see_message_item_avatar_image);
        Glide.with(context)
                .load(UrlConstant.AVATAR_URL + _friend_request_list.get(position).getReceiverAvatarUrl())
                .error(R.mipmap.avatar_err)
                .into(avatar);

        TextView content = holder.itemView.findViewById(R.id.see_message_item_content_text);
        content.setText("MSID为 "+_friend_request_list.get(position).getSenderId()+" 发起了好友申请");

        Button okBt = holder.itemView.findViewById(R.id.see_message_item_yes_button);
        Button noBt = holder.itemView.findViewById(R.id.see_message_item_no_button);

        okBt.setOnClickListener(v -> {
            FriendRelationship friendRelationship = new FriendRelationship(null,_friend_request_list.get(position).getSenderId(),_friend_request_list.get(position).getReceiverId(),null);

            friend_relationship_service.insertFriendRelationship(_token_manager_util.getToken(), friendRelationship, new FriendRelationshipCallBack() {
                @Override
                public void onSuccess(Result<FriendRelationship> result) {
                    if(result.getCode() % 10 == 1 ){
                        Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT).show();
                        // 从数据源中移除该条请求
                        _friend_request_list.remove(position);
                        // 通知 RecyclerView 数据发生了变化
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, _friend_request_list.size());
                    }
                }

                @Override
                public void onFailure(String errorMessage) {
                    System.out.println("---------- "+errorMessage+" ----------");
                    Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
                }
            });


        });

        noBt.setOnClickListener(v->{
            _friend_request_service.deleteFriendRequest(_token_manager_util.getToken(), _friend_request_list.get(position).getSenderId(), new FriendRequestLongCallBack() {
                @Override
                public void onSuccess(Result<Long> result) {
                    if(result.getCode() % 10 == 1){
                        Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                        // 从数据源中移除该条请求
                        _friend_request_list.remove(position);
                        // 通知 RecyclerView 数据发生了变化
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, _friend_request_list.size());
                    }
                }

                @Override
                public void onFailure(String errorMessage) {
                    System.out.println("---------- "+errorMessage+" ----------");
                    Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
                }
            });
        });


    }

    @Override
    public int getItemCount() {
        return this._friend_request_list.size();
    }

    public static class SeeMessageViewHolder extends RecyclerView.ViewHolder{
        public SeeMessageViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}

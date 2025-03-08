package com.cn.miraclestar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.bumptech.glide.Glide;
import com.cn.miraclestar.constants.UrlConstant;
import com.cn.miraclestar.databinding.ActivityFriendHomePageBinding;
import com.cn.miraclestar.dto.RequestFriendRelationshipDto;
import com.cn.miraclestar.pojo.Result;
import com.cn.miraclestar.service.logic_service.RequestFriendRelationshipLogicService;
import com.cn.miraclestar.service.logic_service.callbacks.ObjectCallBack;
import com.cn.miraclestar.service.logic_service.callbacks.RequestFriendRelationshipCallBack;
import com.cn.miraclestar.service.logic_service.impl.RequestFriendRelationshipLogicServiceImpl;
import com.cn.miraclestar.service.sql_service.FriendRequestService;
import com.cn.miraclestar.service.sql_service.callbacks.FriendRequestCallBack;
import com.cn.miraclestar.service.sql_service.impl.FriendRequestServiceImpl;
import com.cn.miraclestar.sql.entity.FriendRequest;
import com.cn.miraclestar.utils.TokenManagerUtil;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.List;

public class FriendHomePageActivity extends AppCompatActivity {

    private FriendRequestService _friend_request_service;

    private ActivityFriendHomePageBinding _activity_friend_home_page_binding;
    private RequestFriendRelationshipLogicService _request_friend_relationship_logic_service;
    private TokenManagerUtil _token_manager_util;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _activity_friend_home_page_binding = ActivityFriendHomePageBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(_activity_friend_home_page_binding.getRoot());
        inIt();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
    private void inIt(){
        initData();
        backMainPage();
        setFrienData();
        addFriend();
        gotoSend();
    }
    private void initData(){
        _token_manager_util = new TokenManagerUtil(FriendHomePageActivity.this);
        _request_friend_relationship_logic_service = new RequestFriendRelationshipLogicServiceImpl();
        _friend_request_service = new FriendRequestServiceImpl();
    }
    private void backMainPage(){
        _activity_friend_home_page_binding.friendHomeBackTopbar
                .setNavigationOnClickListener(v->{
                    finish();
                });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void gotoSend(){

        Resources resources = FriendHomePageActivity.this.getResources();

        _activity_friend_home_page_binding.friendHomeSendChickLl
                        .setOnTouchListener(new View.OnTouchListener() {
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

        _activity_friend_home_page_binding.friendHomeSendChickLl
                .setOnClickListener(v->{
                    Intent intent = new Intent(FriendHomePageActivity.this, SendMessageActivity.class);
                    startActivity(intent);
                });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void addFriend(){

        Resources resources = FriendHomePageActivity.this.getResources();
        Intent intent = getIntent();
        Long userId = intent.getLongExtra("userId",-1);

        _activity_friend_home_page_binding.friendHomeAddChickLl
                .setOnTouchListener(new View.OnTouchListener() {
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

        _activity_friend_home_page_binding.friendHomeAddChickLl
                .setOnClickListener(v->{
                    FriendRequest friendRequest = new FriendRequest(null,Long.parseLong(_token_manager_util.getUserId()),userId,null,null);
                    _friend_request_service.insertFriendRequest(_token_manager_util.getToken(), friendRequest, new FriendRequestCallBack() {
                        @Override
                        public void onSuccess(Result<FriendRequest> result) {
                            if(result.getCode() % 10 == 1 && result.getData().getSenderId() != null && result.getData().getReceiverId() != null){
                                Toast.makeText(FriendHomePageActivity.this, "已发送添加请求", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(String errorMessage) {
                            System.out.println("----- " + errorMessage + " -----");
                            Toast.makeText(FriendHomePageActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                        }
                    });
                });
    }

    private void setFrienData(){

        Intent intent = getIntent();
        Long userId = intent.getLongExtra("userId",-1);

        _request_friend_relationship_logic_service.getFriendData(_token_manager_util.getToken(),userId, new RequestFriendRelationshipCallBack() {
            @Override
            public void onSuccess(Result<List<RequestFriendRelationshipDto>> result) {

            }

            @Override
            public void onSuccessOne(Result<RequestFriendRelationshipDto> result) {
                if(result.getCode() % 10 == 1){
                    Glide.with(FriendHomePageActivity.this)
                            .load(UrlConstant.AVATAR_URL+result.getData().getAvatarUrl())
                            .error(R.mipmap.avatar_err)
                            .into(_activity_friend_home_page_binding.friendHomePageAvatarImage);

                    _activity_friend_home_page_binding.friendHomePageIdText
                            .setText(getResources().getString(R.string.app_id_string)+result.getData().getUserId().toString());

                    _activity_friend_home_page_binding.friendHomePageNameText
                            .setText(result.getData().getUsername());

                    _request_friend_relationship_logic_service.getIsFriend(_token_manager_util.getToken(), userId, new ObjectCallBack() {
                        @Override
                        public void onSuccess(Result<Short> result) {
                            if(result.getCode() % 10 == 1){
                                if(result.getData() == 1){
                                    _activity_friend_home_page_binding.friendHomeSendChickLl
                                            .setVisibility(View.VISIBLE);

                                }else{
                                    _activity_friend_home_page_binding.friendHomeAddChickLl
                                            .setVisibility(View.VISIBLE);
                                }
                            }
                        }

                        @Override
                        public void onFailure(String errorMessage) {
                            System.out.println("----- " + errorMessage + " -----");
                            Toast.makeText(FriendHomePageActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(String errorMessage) {
                System.out.println("----- " + errorMessage + " -----");
                Toast.makeText(FriendHomePageActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
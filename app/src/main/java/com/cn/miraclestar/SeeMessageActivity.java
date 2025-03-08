package com.cn.miraclestar;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cn.miraclestar.adapter.SeeMessageAdapter;
import com.cn.miraclestar.databinding.ActivitySeeMessageBinding;
import com.cn.miraclestar.pojo.Result;
import com.cn.miraclestar.service.sql_service.FriendRequestService;
import com.cn.miraclestar.service.sql_service.callbacks.FriendRequestListCallBack;
import com.cn.miraclestar.service.sql_service.impl.FriendRequestServiceImpl;
import com.cn.miraclestar.sql.entity.FriendRequest;
import com.cn.miraclestar.utils.TokenManagerUtil;

import java.util.List;

public class SeeMessageActivity extends AppCompatActivity {

    private ActivitySeeMessageBinding _activity_see_message_binding;
    private FriendRequestService _friend_request_service;
    private TokenManagerUtil _token_manager_util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _activity_see_message_binding = ActivitySeeMessageBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(_activity_see_message_binding.getRoot());

        inIt();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void inIt(){
        inItData();
        inItView();
        backMainPage();
    }

    private void inItData(){
        _friend_request_service = new FriendRequestServiceImpl();
        _token_manager_util = new TokenManagerUtil(SeeMessageActivity.this);
    }

    private void inItView(){
        RecyclerView recyclerView = _activity_see_message_binding.seeMessageSpaceLl;
        recyclerView.setLayoutManager(new LinearLayoutManager(SeeMessageActivity.this));

        _friend_request_service.getFriendRequestList(_token_manager_util.getToken(), Long.parseLong(_token_manager_util.getUserId()), new FriendRequestListCallBack() {
            @Override
            public void onSuccess(Result<List<FriendRequest>> result) {
                if(result.getCode() % 10 == 1){
                    SeeMessageAdapter seeMessageAdapter = new SeeMessageAdapter(SeeMessageActivity.this,result.getData());
                    recyclerView.setAdapter(seeMessageAdapter);
                }
            }

            @Override
            public void onFailure(String errorMessage) {
                System.out.println("---------- "+errorMessage+" ----------");
                Toast.makeText(SeeMessageActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void backMainPage(){
        _activity_see_message_binding.seeMessageBackTopbar
                .setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }


}
package com.cn.miraclestar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cn.miraclestar.adapter.ListFriendAdapter;
import com.cn.miraclestar.adapter.SearchFriendAdapter;
import com.cn.miraclestar.databinding.ActivitySearchBinding;
import com.cn.miraclestar.dto.RequestFriendRelationshipDto;
import com.cn.miraclestar.pojo.Result;
import com.cn.miraclestar.service.logic_service.RequestFriendRelationshipLogicService;
import com.cn.miraclestar.service.logic_service.callbacks.RequestFriendRelationshipCallBack;
import com.cn.miraclestar.service.logic_service.impl.RequestFriendRelationshipLogicServiceImpl;
import com.cn.miraclestar.utils.TokenManagerUtil;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private ActivitySearchBinding _activity_search_binding;
    private TokenManagerUtil _token_manager_util ;
    private RequestFriendRelationshipLogicService _request_friend_relationship_logic_service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _activity_search_binding = ActivitySearchBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);

        setContentView(_activity_search_binding.getRoot());

        inIt();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void inIt(){
        inItData();
        searchFriend();
    }

    private void inItData(){
        _token_manager_util = new TokenManagerUtil(this);
        _request_friend_relationship_logic_service = new RequestFriendRelationshipLogicServiceImpl();
    }

    private void searchFriend(){

        RecyclerView recyclerView = _activity_search_binding.searchAddFriendDataSpace;
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));

        _activity_search_binding.searchChickFriendIdButton
                .setOnClickListener(v->{
                    String msid = _activity_search_binding.searchInputFriendIdEdit.getText().toString();
                    Long msidL = Long.parseLong(msid);

                    if( msid != null && !msid.isEmpty()){
                        _request_friend_relationship_logic_service.getFriendData(_token_manager_util.getToken(), msidL, new RequestFriendRelationshipCallBack() {
                            @Override
                            public void onSuccess(Result<List<RequestFriendRelationshipDto>> result) {

                            }

                            @Override
                            public void onSuccessOne(Result<RequestFriendRelationshipDto> result) {
                                if(result.getCode() % 10 == 1){
                                    //System.out.println("----------- "+result.getData().toString());
                                    SearchFriendAdapter searchFriendAdapter = new SearchFriendAdapter(SearchActivity.this,result.getData());
                                    recyclerView.setAdapter(searchFriendAdapter);
                                }
                            }

                            @Override
                            public void onFailure(String errorMessage) {
                                System.out.println("---------- "+errorMessage+" ----------");
                                Toast.makeText(SearchActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
    }

}
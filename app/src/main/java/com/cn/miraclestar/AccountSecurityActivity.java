package com.cn.miraclestar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cn.miraclestar.databinding.ActivityAccountSecurityBinding;
import com.cn.miraclestar.utils.TokenManagerUtil;

public class AccountSecurityActivity extends AppCompatActivity {

    private ActivityAccountSecurityBinding _activity_account_secrity_binding;
    private TokenManagerUtil _token_manager_util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _activity_account_secrity_binding = ActivityAccountSecurityBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);

        setContentView(_activity_account_secrity_binding.getRoot());

        inIt();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void inIt(){
        inItData();
        backMainPage();
        exitAccounts();
    }

    private void inItData(){
        _token_manager_util = new TokenManagerUtil(AccountSecurityActivity.this);
    }

    private void switchAccounts(){
        _activity_account_secrity_binding.accountSecurityChickSwitchAccounts
                .setOnClickListener(v->{

                });
    }

    private void exitAccounts(){
        _activity_account_secrity_binding.accountSecurityChickExitAccounts
                .setOnClickListener(v->{
                    Intent intent = new Intent(AccountSecurityActivity.this, SignInActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    // 在当前 Activity 中调用 finishAffinity() 关闭除根 Activity 之外的所有 Activity
                    _token_manager_util.clearToken();
                    finishAffinity();
                });
    }

    private void backMainPage(){

        _activity_account_secrity_binding.accountSecurityTopbar
                .setNavigationOnClickListener(v->{
                        finish();
                });

    }
}
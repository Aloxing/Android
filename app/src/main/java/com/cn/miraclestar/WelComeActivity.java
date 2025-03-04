package com.cn.miraclestar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cn.miraclestar.pojo.Result;
import com.cn.miraclestar.service.logic_service.TokenLogicService;
import com.cn.miraclestar.service.logic_service.callbacks.TokenCallBack;
import com.cn.miraclestar.service.logic_service.impl.TokenLogicServiceImpl;
import com.cn.miraclestar.utils.TokenManagerUtil;

public class WelComeActivity extends AppCompatActivity {

    private TokenManagerUtil _token_manager_util;
    private TokenLogicService _token_logic_service = new TokenLogicServiceImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_wel_come);

        verifyToken();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void verifyToken() {
        _token_manager_util = new TokenManagerUtil(this);
        String token = _token_manager_util.getToken();

        _token_logic_service.tokenTrue(token, new TokenCallBack() {
            @Override
            public void onSuccess(Result<String> result) {
                if(result.getCode() == 10001){
                    Intent intent = new Intent(WelComeActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(WelComeActivity.this, SignInActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
            @Override
            public void onFailure(String errorMessage) {
                // 处理失败结果
                System.out.println("----- " + errorMessage + " -----");
                Toast.makeText(WelComeActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(WelComeActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
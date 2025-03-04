package com.cn.miraclestar;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cn.miraclestar.databinding.ActivitySendMessageBinding;

public class SendMessageActivity extends AppCompatActivity {

    private ActivitySendMessageBinding _activity_send_message_binding;
    private LinearLayout mainLayout;
    private int rootViewVisibleHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _activity_send_message_binding = ActivitySendMessageBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);

        setContentView(_activity_send_message_binding.getRoot());

        inIt();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void inIt(){

        setLayoutSize();

        addTopBarClick();

    }

    //释放当前页面
    private void addTopBarClick(){
        _activity_send_message_binding.sendMessageTopbar
                .setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }

    //修改键盘与布局的距离
    private void setLayoutSize(){
        mainLayout = _activity_send_message_binding.main;
        mainLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                mainLayout.getWindowVisibleDisplayFrame(r);
                int visibleHeight = r.height();
                if (rootViewVisibleHeight == 0) {
                    rootViewVisibleHeight = visibleHeight;
                    return;
                }
                // 如果当前可见高度小于之前的可见高度，说明软键盘弹出了
                if (rootViewVisibleHeight > visibleHeight) {
                    int heightDifference = rootViewVisibleHeight - visibleHeight;

                    mainLayout.setPadding(0,
                            _activity_send_message_binding.main.getPaddingTop(), 0, heightDifference+5);
                } else {
                    // 软键盘隐藏，恢复布局
                    mainLayout.setPadding(0,
                            _activity_send_message_binding.main.getPaddingTop(), 0, 40);
                }
            }
        });
    }
}
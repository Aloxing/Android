package com.cn.miraclestar;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cn.miraclestar.databinding.ActivityScrollManagementBinding;

public class ScrollManagementActivity extends AppCompatActivity {

    private ActivityScrollManagementBinding _activity_scroll_management_binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _activity_scroll_management_binding = ActivityScrollManagementBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);

        setContentView(_activity_scroll_management_binding.getRoot());

        inIt();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void inIt(){
        backMainPage();
    }

    private void backMainPage(){
        _activity_scroll_management_binding.scrollManagementTopbar
                .setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }
}
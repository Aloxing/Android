package com.cn.miraclestar;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cn.miraclestar.databinding.ActivitySoftwareStoreBinding;

public class SoftwareStoreActivity extends AppCompatActivity {

    private ActivitySoftwareStoreBinding _activity_software_store_binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _activity_software_store_binding = ActivitySoftwareStoreBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);

        setContentView(_activity_software_store_binding.getRoot());

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
        _activity_software_store_binding.softwareStoreTopbar
                .setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }
}
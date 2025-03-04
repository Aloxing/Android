package com.cn.miraclestar;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cn.miraclestar.databinding.ActivityGameShowcaseBinding;

public class GameShowcaseActivity extends AppCompatActivity {

    private ActivityGameShowcaseBinding _activity_game_showcase_binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _activity_game_showcase_binding = ActivityGameShowcaseBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);

        setContentView(_activity_game_showcase_binding.getRoot());

        inIt();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void inIt(){
        backMainHome();
    }

    private void backMainHome(){
        _activity_game_showcase_binding.gameShowcaseTopbar
                .setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }
}
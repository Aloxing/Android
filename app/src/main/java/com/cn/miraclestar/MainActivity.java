package com.cn.miraclestar;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cn.miraclestar.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding _activity_main_binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _activity_main_binding = ActivityMainBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);

        setContentView(_activity_main_binding.getRoot());

        inIt();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void inIt(){

        //顶部切换处理
        setMainTopFragment();
        //底部切换处理
        setMainBottomFragment();

    }

    //顶部标签逻辑
    private void setMainTopFragment(){
        _activity_main_binding.mainTopAppBar
                .setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.main_search_icon){
                            return true;
                        }
                        if(item.getItemId() == R.id.main_add_friend_icon){

                            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                            startActivity(intent);
                            return true;
                        }
                        if(item.getItemId() == R.id.main_more_see_message){

                            Intent intent = new Intent(MainActivity.this, SeeMessageActivity.class);
                            startActivity(intent);
                            return true;
                        }
                        return false;
                    }
                });
    }

    //底部跳转逻辑
    private void setMainBottomFragment(){

        MainMessageFragment mainMessageFragment = new MainMessageFragment();
        MainFriendFragment mainFriendFragment = new MainFriendFragment();
        MainMeFragment mainMeFragment = new MainMeFragment();

        //获取资源

        // 默认显示 HomeFragment
        replaceFragment(mainMessageFragment);

        _activity_main_binding.mainBottomNavigation
                .setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.main_show_message){
                    replaceFragment(mainMessageFragment);
                    _activity_main_binding.mainTopAppBar.setTitle(getString(R.string.main_message_page_name));
                    _activity_main_binding.mainTopAppBar.setVisibility(View.VISIBLE);
                    return true;
                }

                if (item.getItemId() == R.id.main_show_friend){
                    replaceFragment(mainFriendFragment);
                    _activity_main_binding.mainTopAppBar.setTitle(getString(R.string.main_friend_page_name));
                    _activity_main_binding.mainTopAppBar.setVisibility(View.VISIBLE);
                    return true;
                }

                if (item.getItemId() == R.id.main_show_me){
                    replaceFragment(mainMeFragment);
                    _activity_main_binding.mainTopAppBar.setTitle(getString(R.string.main_me_page_name));
                    _activity_main_binding.mainTopAppBar.setVisibility(View.GONE);
                    return true;
                }

                return false;
            }

        });

    }

    //切换page的工具
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment_container, fragment);
        fragmentTransaction.commit();
    }


}
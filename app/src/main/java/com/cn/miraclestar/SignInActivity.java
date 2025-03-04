package com.cn.miraclestar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.cn.miraclestar.databinding.ActivitySignInBinding;
import com.cn.miraclestar.pojo.Result;
import com.cn.miraclestar.service.logic_service.SignInLogicService;
import com.cn.miraclestar.service.logic_service.TokenLogicService;
import com.cn.miraclestar.service.logic_service.callbacks.SignInCallBack;
import com.cn.miraclestar.service.logic_service.callbacks.TokenCallBack;
import com.cn.miraclestar.service.logic_service.impl.SignInLogicServiceImpl;
import com.cn.miraclestar.service.logic_service.impl.TokenLogicServiceImpl;
import com.cn.miraclestar.service.sql_service.UsersSqlService;
import com.cn.miraclestar.service.sql_service.callbacks.UsersCallback;
import com.cn.miraclestar.service.sql_service.impl.UsersSqlServiceImpl;
import com.cn.miraclestar.sql.dao.UsersDao;
import com.cn.miraclestar.sql.database.AppDatabase;
import com.cn.miraclestar.sql.entity.Users;
import com.cn.miraclestar.sql.service.GetDatabaseService;
import com.cn.miraclestar.sql.service.impl.GetDatabaseServiceImpl;
import com.cn.miraclestar.utils.TokenManagerUtil;
import com.google.gson.Gson;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class SignInActivity extends AppCompatActivity {

    //简化findById
    private ActivitySignInBinding _activity_sign_in_binding;

    //token缓存
    private TokenManagerUtil _token_manager_util;

    //登录判断
    private Integer startNum = 0;

    //网络请求
    private SignInLogicService _sign_in_logic_service = new SignInLogicServiceImpl();
    private TokenLogicService _token_logic_service = new TokenLogicServiceImpl();
    private UsersSqlService _users_sql_service = new UsersSqlServiceImpl();

    //数据库实例
    private CompositeDisposable disposables = new CompositeDisposable();
    private GetDatabaseService _get_database_service = new GetDatabaseServiceImpl();
    private AppDatabase db ;
    private UsersDao usersDao;
    // 线程池


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        _activity_sign_in_binding = ActivitySignInBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);

        setContentView(_activity_sign_in_binding.getRoot());

        inIt();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void inIt(){
        //初始数据库
        inItDatabase();
        //登录校验
        signIn();
    }

    private void inItDatabase(){
        db = _get_database_service.getDatabase(getApplicationContext());
        usersDao = db.usersDao();
    }

    private void signIn(){
        _token_manager_util = new TokenManagerUtil(this);
        _activity_sign_in_binding.signInGoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = _activity_sign_in_binding.
                        signInUsernameEditText.getText().toString()
                        .trim();
                String password = _activity_sign_in_binding.
                        signInPasswordEditText.getText().toString()
                        .trim();

                _sign_in_logic_service.signIn(username, password, new SignInCallBack() {
                    @Override
                    public void onSuccess(Result<String> result) {
                        // 处理成功结果
                        Toast.makeText(SignInActivity.this, result.getMsg(), Toast.LENGTH_SHORT).show();

                        if(result.getCode() == 20041 && startNum == 0){

                            startNum = 1;//防止多次点击

                            //System.out.println("----- 1"+result.getData());

                            _token_manager_util.saveToken(result.getData());

                            //通过token将用户信息本地化
                            saveUserData(_token_manager_util.getToken());
                            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                    @Override
                    public void onFailure(String errorMessage) {
                        // 处理失败结果
                        System.out.println("----- " + errorMessage + " -----");
                        _token_manager_util.clearToken();
                        Toast.makeText(SignInActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void saveUserData(String token){
        _token_logic_service.tokenTrue(token, new TokenCallBack() {
            @Override
            public void onSuccess(Result<String> result) {
                String id= result.getData();
                //System.out.println("----- 2 "+result.getData());
                _token_manager_util.saveUserId(id);
                if(!id.isEmpty()){
                    Long idL = Long.parseLong(id);
                    _users_sql_service.getUser(token,idL, new UsersCallback() {
                        @SuppressLint("CheckResult")
                        @Override
                        public void onSuccess(Result<Users> result) {
                            // 将 JSON 字符串转换为 User 对象
//                            System.out.println("----- "+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+" -----");
//                            System.out.println("----- "+result.getData().toString()+" -----");
                            Users user = result.getData();
//                            System.out.println("----- "+user.toString()+" -----");
//                            System.out.println("----- "+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+" -----");

                            usersDao.insertUser(user)
                                    .subscribeOn(Schedulers.io()) // Run insertion on IO thread
                                    .observeOn(AndroidSchedulers.mainThread()) // Handle result on main thread
                                    .subscribe(
                                            () -> {
                                                // Success case

                                            },
                                            throwable -> {
                                                // Error case
                                                System.out.println("----- Error inserting user: " + throwable.getMessage()+" -----");
                                            }
                                    );
                        }
                        @Override
                        public void onFailure(String errorMessage) {
                            System.out.println("----- " + errorMessage + " -----");
                        }
                    });
                }
            }

            @Override
            public void onFailure(String errorMessage) {
                System.out.println("----- " + errorMessage + " -----");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.dispose(); // Clean up subscriptions
    }

}
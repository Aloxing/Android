package com.cn.miraclestar.models;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cn.miraclestar.sql.dao.UsersDao;
import com.cn.miraclestar.sql.entity.Users;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainMeFragmentViewModel extends ViewModel {
    private final MutableLiveData<Users> user = new MutableLiveData<>();
    private UsersDao usersDao;
    private Long userId;
    private Disposable disposable;

    public LiveData<Users> getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user.setValue(user); // 确保在主线程调用
    }

    public void init(UsersDao usersDao, Long userId) {
        this.usersDao = usersDao;
        this.userId = userId;
        loadUserData();
    }

    private void loadUserData() {
        if (user.getValue() == null && (disposable == null || disposable.isDisposed())) {
            disposable = usersDao.selectUser(userId)
                    .subscribeOn(Schedulers.io()) // 在 IO 线程执行数据库查询
                    .observeOn(AndroidSchedulers.mainThread()) // 切换到主线程处理结果
                    .subscribe(
                            this::setUser, // 在主线程更新 LiveData
                            throwable -> android.util.Log.e("DatabaseError", "Error fetching user: " + throwable.getMessage())
                    );
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}

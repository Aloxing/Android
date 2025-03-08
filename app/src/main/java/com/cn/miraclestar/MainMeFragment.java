package com.cn.miraclestar;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.cn.miraclestar.constants.UrlConstant;
import com.cn.miraclestar.databinding.FragmentMainMeBinding;
import com.cn.miraclestar.models.MainMeFragmentViewModel;
import com.cn.miraclestar.pojo.Result;
import com.cn.miraclestar.service.sql_service.AvatarsSqlService;
import com.cn.miraclestar.service.sql_service.callbacks.AvatarsCallback;
import com.cn.miraclestar.service.sql_service.impl.AvatarsSqlServiceImpl;
import com.cn.miraclestar.sql.dao.UsersDao;
import com.cn.miraclestar.sql.database.AppDatabase;
import com.cn.miraclestar.sql.entity.Avatars;
import com.cn.miraclestar.sql.entity.Users;
import com.cn.miraclestar.sql.service.GetDatabaseService;
import com.cn.miraclestar.sql.service.impl.GetDatabaseServiceImpl;
import com.cn.miraclestar.utils.TokenManagerUtil;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //
    private View view;
    private ImageView avatar;

    //数据库操作
    private CompositeDisposable disposables = new CompositeDisposable();
    private GetDatabaseService _get_database_service = new GetDatabaseServiceImpl();
    private AppDatabase _db;
    private UsersDao _user_dao;
    //token操作
    private TokenManagerUtil _token_manager_util ;

    // 线程池
    private MainMeFragmentViewModel viewModel;
    private FragmentMainMeBinding binding;

    //选择图片权限
    private ActivityResultLauncher<PickVisualMediaRequest> pickMedia;

    //网络请求
    private AvatarsSqlService _avatars_sql_service = new AvatarsSqlServiceImpl();

    public MainMeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainMeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainMeFragment newInstance(String param1, String param2) {
        MainMeFragment fragment = new MainMeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //图片选择注册
        selectPhoto();
        // 在 onCreate 中初始化 ViewModel，确保作用域正确
        viewModel = new ViewModelProvider(this).get(MainMeFragmentViewModel.class);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_main_me, container, false);
        view = binding.getRoot();

        _token_manager_util = new TokenManagerUtil(requireContext());
        _db = _get_database_service.getDatabase(getActivity());
        _user_dao = _db.usersDao();

        String _token = _token_manager_util.getToken();
        Long _userId = Long.parseLong(_token_manager_util.getUserId());
        viewModel.init(_user_dao, _userId);

        binding.setMainMeViewModel(viewModel);
        binding.setLifecycleOwner(this);

        //初始化所有设置选项
        List<Integer> idList = new ArrayList<>();

        //idList.add(R.id.main_me_user_data_page);

        idList.add(R.id.main_me_options_account_security_click);
        idList.add(R.id.main_me_options_dataview_click);
        idList.add(R.id.main_me_options_software_shop_click);

        idList.add(R.id.main_me_options_game_click);
        idList.add(R.id.main_me_options_music_click);
        idList.add(R.id.main_me_options_shop_click);

        idList.add(R.id.main_me_options_blog_click);
        idList.add(R.id.main_me_options_setting_click);


        List<Class<?>> activityList = new ArrayList<>();

        //activityList.add(MyDataActivity.class);

        activityList.add(AccountSecurityActivity.class);
        activityList.add(DataAnalysisActivity.class);
        activityList.add(SoftwareStoreActivity.class);

        activityList.add(GameShowcaseActivity.class);
        activityList.add(MusicCompositionActivity.class);
        activityList.add(ItemShopActivity.class);

        activityList.add(ScrollManagementActivity.class);
        activityList.add(SoftwareSettingActivity.class);

        Resources resources = getResources();

        LinearLayout myData = view.findViewById(R.id.my_home_page_my_data);

        myData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyDataActivity.class);
                startActivity(intent);
            }
        });

        for(int i =0;i<idList.size();++i){

            LinearLayout linearLayout = view.findViewById(idList.get(i));
            linearLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction() == MotionEvent.ACTION_DOWN){
                        v.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.light_gray, null));
//                        return false;
                    }
                    if(event.getAction() == MotionEvent.ACTION_UP){
                        v.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.white, null));
//                        return true;
                    }
                    if(event.getAction() == MotionEvent.ACTION_CANCEL){
                        v.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.white, null));
//                        return true;
                    }
                    return false;
                }
            });

            int finalI = i;
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),activityList.get(finalI));
                    startActivity(intent);
                }
            });
        }

        ImageView avatarImageView = view.findViewById(R.id.my_home_page_avatar_image);
        avatarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //对话框
                new MaterialAlertDialogBuilder(getContext())
                        .setTitle(resources.getString(R.string.my_home_page_avatar_select_title))
                        .setMessage(resources.getString(R.string.my_home_page_avatar_select_message))
                        .setNeutralButton(resources.getString(R.string.cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setPositiveButton(resources.getString(R.string.accept), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (pickMedia != null) {
                                    pickMedia.launch(new PickVisualMediaRequest.Builder()
                                            .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                                            .build());
                                }
                            }
                        }).show();
            }
        });


        avatar = view.findViewById(R.id.my_home_page_avatar_image);
        _avatars_sql_service.getAvatar(_token, _userId, new AvatarsCallback() {
            @Override
            public void onSuccess(Result<Avatars> result) {
                if(result.getCode() % 10 == 1){
                    String avatar_url = UrlConstant.AVATAR_URL+result.getData().getAvatarUrl();

                    Glide.with(requireActivity())
                            .load(avatar_url)
                            .error(R.mipmap.avatar_err)
                            .into(avatar);
                }
            }

            @Override
            public void onSetAvatarsSuccess(Result<String> result) {

            }

            @Override
            public void onFailure(String errorMessage) {
                System.out.println("----- " + errorMessage + " -----");
                Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    //
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // 防止内存泄漏
    }

    //创建实例
    private void selectPhoto(){
                // 直接对类成员变量 pickMedia 进行赋值
                pickMedia = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                    // Callback is invoked after the user selects a media item or closes the
                    // photo picker.
                    if (uri != null) {
                        System.out.println("----- PhotoPicker"+ "Selected URI: " + uri+" -----");
                        uploadImage(uri);
                    } else {
                        System.out.println("----- PhotoPicker"+"No media selected ------");
                    }
                });
    }

    private void uploadImage(Uri uri) {
        String token = _token_manager_util.getToken();
        Long id = Long.parseLong(_token_manager_util.getUserId());
        // 获取文件路径
        String filePath = getRealPathFromURI(uri);
        if (filePath != null) {
            File file = new File(filePath);
            // 创建 RequestBody
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
            // 创建 MultipartBody.Part
            MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
            // 发起 Retrofit 请求

            _avatars_sql_service.setAvatar(token, id, body, new AvatarsCallback() {
                @Override
                public void onSuccess(Result<Avatars> result) {

                }

                @Override
                public void onSetAvatarsSuccess(Result<String> result) {
                    Toast.makeText(getActivity(), result.getMsg(), Toast.LENGTH_SHORT).show();
                    if(result.getCode() % 10 ==1){

                        avatar = view.findViewById(R.id.my_home_page_avatar_image);
                        _avatars_sql_service.getAvatar(token, id, new AvatarsCallback() {
                            @Override
                            public void onSuccess(Result<Avatars> result) {
                                if(result.getCode() % 10 == 1){
                                    String avatar_url = UrlConstant.AVATAR_URL+result.getData().getAvatarUrl();
                                    //System.out.println("---------- "+avatar_url);
                                    Glide.with(requireActivity())
                                            .load(avatar_url)
                                            .error(R.mipmap.avatar_err)
                                            .into(avatar);
                                }
                            }

                            @Override
                            public void onSetAvatarsSuccess(Result<String> result) {

                            }

                            @Override
                            public void onFailure(String errorMessage) {
                                System.out.println("----- " + errorMessage + " -----");
                                Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }

                @Override
                public void onFailure(String errorMessage) {
                    System.out.println("----- " + errorMessage + " -----");
                    Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = requireContext().getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
        return null;
    }

}








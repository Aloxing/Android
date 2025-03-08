package com.cn.miraclestar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cn.miraclestar.adapter.ListFriendAdapter;
import com.cn.miraclestar.dto.RequestFriendRelationshipDto;
import com.cn.miraclestar.pojo.Result;
import com.cn.miraclestar.service.logic_service.RequestFriendRelationshipLogicService;
import com.cn.miraclestar.service.logic_service.callbacks.RequestFriendRelationshipCallBack;
import com.cn.miraclestar.service.logic_service.impl.RequestFriendRelationshipLogicServiceImpl;
import com.cn.miraclestar.utils.TokenManagerUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFriendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFriendFragment extends Fragment {

    private RequestFriendRelationshipLogicService _request_friend_relationship_logic_service = new RequestFriendRelationshipLogicServiceImpl();
    private TokenManagerUtil _token_manager_util ;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFriendFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFriendFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFriendFragment newInstance(String param1, String param2) {
        MainFriendFragment fragment = new MainFriendFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_friend, container, false);

        _token_manager_util = new TokenManagerUtil(requireContext());

        String _token = _token_manager_util.getToken();
        String _userId = _token_manager_util.getUserId();

        RecyclerView recyclerView = view.findViewById(R.id.show_list_friend);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        _request_friend_relationship_logic_service.getFriendList(_token, Long.parseLong(_userId), new RequestFriendRelationshipCallBack() {
            @Override
            public void onSuccess(Result<List<RequestFriendRelationshipDto>> result) {

                if(result.getCode() % 10 == 1){
                    ListFriendAdapter adapter = new ListFriendAdapter(getActivity(),result.getData());
                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onSuccessOne(Result<RequestFriendRelationshipDto> result) {

            }

            @Override
            public void onFailure(String errorMessage) {
                System.out.println("----- " + errorMessage + " -----");
                Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}





























package com.cn.miraclestar.service.logic_service.callbacks;

import com.cn.miraclestar.dto.RequestFriendRelationshipDto;
import com.cn.miraclestar.pojo.Result;

import java.util.List;

public interface RequestFriendRelationshipCallBack {
    void onSuccess(Result<List<RequestFriendRelationshipDto>> result);
    void onSuccessOne(Result<RequestFriendRelationshipDto> result);
    void onFailure(String errorMessage);
}

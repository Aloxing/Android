package com.cn.miraclestar.dto;

import androidx.annotation.NonNull;

public class RequestFriendRelationshipDto {
    private Long userId;
    private String username;
    private String avatarUrl;
    private String friendAt;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getFriendAt() {
        return friendAt;
    }

    public void setFriendAt(String friendAt) {
        this.friendAt = friendAt;
    }

    @Override
    public String toString() {
        return "RequestFriendRelationshipDto{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", friendAt='" + friendAt + '\'' +
                '}';
    }

    public RequestFriendRelationshipDto(Long userId, String username, String avatarUrl, String friendAt) {
        this.userId = userId;
        this.username = username;
        this.avatarUrl = avatarUrl;
        this.friendAt = friendAt;
    }

    public RequestFriendRelationshipDto() {
    }
}

package com.cn.miraclestar.sql.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Avatars {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private Integer id;
    @ColumnInfo(name = "user_id")
    private Long userId;
    @ColumnInfo(name = "avatar_url")
    private String avatarUrl;
    @ColumnInfo(name = "is_default")
    private Short isDefault;
    @ColumnInfo(name = "created_at")
    private String createdAt;
    @ColumnInfo(name = "updated_at")
    private String updatedAt;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Short getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Short isDefault) {
        this.isDefault = isDefault;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Avatars(Integer id, Long userId, String avatarUrl, Short isDefault, String createdAt, String updatedAt) {
        this.id = id;
        this.userId = userId;
        this.avatarUrl = avatarUrl;
        this.isDefault = isDefault;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Avatars() {
    }

    @NonNull
    @Override
    public String toString() {
        return "Avatars{" +
                "id=" + id +
                ", userId=" + userId +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", isDefault=" + isDefault +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}

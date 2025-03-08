package com.cn.miraclestar.sql.entity;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class FriendRelationship {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private Integer id;
    @ColumnInfo(name = "user1_id")
    private Long user1Id;
    @ColumnInfo(name = "user2_id")
    private Long user2Id;
    @ColumnInfo(name = "created_at")
    private String createdAt;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Long getUser1Id() {
        return user1Id;
    }
    public void setUser1Id(Long user1Id) {
        this.user1Id = user1Id;
    }
    public Long getUser2Id() {
        return user2Id;
    }
    public void setUser2Id(Long user2Id) {
        this.user2Id = user2Id;
    }
    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    @Override
    public String toString() {
        return "FriendRelationship [id=" + id + ", user1Id=" + user1Id + ", user2Id=" + user2Id + ", createdAt="
                + createdAt + "]";
    }
    public FriendRelationship() {
    }
    public FriendRelationship(Integer id, Long user1Id, Long user2Id, String createdAt) {
        this.id = id;
        this.user1Id = user1Id;
        this.user2Id = user2Id;
        this.createdAt = createdAt;
    }

}

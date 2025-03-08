package com.cn.miraclestar.sql.entity;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class FriendRequest {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private Integer id;

    @ColumnInfo(name = "sender_id")
    private Long senderId;

    @ColumnInfo(name = "receiver_id")
    private Long receiverId;

    @ColumnInfo(name = "status")
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FriendRequest [id=" + id + ", senderId=" + senderId + ", receiverId=" + receiverId + ", status="
                + status + "]";
    }

    public FriendRequest(Integer id, Long senderId, Long receiverId, String status) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.status = status;
    }

    public FriendRequest() {
    }
}

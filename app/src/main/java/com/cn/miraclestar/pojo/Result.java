package com.cn.miraclestar.pojo;

//返回数据类
public class Result<T> {
    private T data;
    private String msg;
    private Integer code;

    public Result(T data, String msg, Integer code) {
        this.data = data;
        this.msg = msg;
        this.code = code;
    }

    public Result(T data, Integer code) {
        this.data = data;
        this.code = code;
    }

    public Result(Integer code){
        this.code = code;
    }

    public Result() { }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Result{" +
                "data=" + data +
                ", msg='" + msg + '\'' +
                ", code=" + code +
                '}';
    }


}

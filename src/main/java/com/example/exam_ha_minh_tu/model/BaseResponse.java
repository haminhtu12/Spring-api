package com.example.exam_ha_minh_tu.model;

public class BaseResponse<T> {
    public int status = 111;
    public String message = "success";
    public T data;
}

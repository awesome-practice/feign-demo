package com.practice.feign.demo.server.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultObject<T> implements Serializable {

    private String code = "200";

    private String msg;

    private T data;

    public ResultObject() {

    }

    public ResultObject(T data) {
        this.data=data;
    }


}

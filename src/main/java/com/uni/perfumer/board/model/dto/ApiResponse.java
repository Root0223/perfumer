package com.uni.perfumer.board.model.dto;

import lombok.Data;

@Data
public class ApiResponse {
    private Boolean success;
    private String msg;

    public ApiResponse(Boolean success, String msg){
        this.success = success;
        this.msg = msg;
    }
}

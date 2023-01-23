package com.uni.perfumer.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data

public class ResponseDTO {

    private int status;
    private String message;
    private Object data;

    public ResponseDTO(HttpStatus status, String message, Object data) { 
        //자동생성하고 status의 타입을 HttpStatus 로 바꿔주고  status 를 .value()로 다시 인트로 바꿈??
        this.status = status.value();
        this.message = message;
        this.data = data;
    }
}

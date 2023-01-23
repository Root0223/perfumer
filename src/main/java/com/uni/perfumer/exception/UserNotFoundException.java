package com.uni.perfumer.exception;

public class UserNotFoundException extends RuntimeException{
    //생성자를 만들면 프로젝트에서 익셉션으로 표시됨
    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
    
}

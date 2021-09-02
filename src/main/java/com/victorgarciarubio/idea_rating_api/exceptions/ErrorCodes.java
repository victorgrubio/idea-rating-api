package com.victorgarciarubio.idea_rating_api.exceptions;

public enum ErrorCodes {
    IDEA_NOT_FOUND(1001),
    IDEA_NOT_VALID(1002),
    ;

    private final int code;

    ErrorCodes(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}

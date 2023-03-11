package com.rwws.rwserver.exception;

import lombok.Data;

@Data
public class FieldErrorVM {

    private String objectName;

    private String field;

    private String code;


    public FieldErrorVM(String objectName, String field, String code) {
        this.objectName = objectName;
        this.field = field;
        this.code = code;
    }
}

package com.tsystems.util.exceptions;

public class WrongOptionConfigurationException extends Exception {
    private Integer errorCode;

    public WrongOptionConfigurationException(Integer errorCode) {
        super("Wrong option configuration. Error code: " + errorCode + ". Check docs");
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}

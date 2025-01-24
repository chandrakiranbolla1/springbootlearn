package com.learnspringboot.springbootlearn.error;

public class DepartmentNameNotFoundException extends Exception{

    public DepartmentNameNotFoundException() {
        super();
    }

    public DepartmentNameNotFoundException(String message) {
        super(message);
    }

    public DepartmentNameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartmentNameNotFoundException(Throwable cause) {
        super(cause);
    }

    protected DepartmentNameNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

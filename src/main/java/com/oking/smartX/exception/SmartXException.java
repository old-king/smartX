package com.oking.smartX.exception;

/**
 * Created by 谢青(oking) on 2017/4/11 0011.
 */
public class SmartXException extends RuntimeException {
    public SmartXException() {
        super();
    }

    public SmartXException(String message) {
        super(message);
    }

    public SmartXException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmartXException(Throwable cause) {
        super(cause);
    }

    public SmartXException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

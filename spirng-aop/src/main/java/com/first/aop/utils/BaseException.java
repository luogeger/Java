/**
 * All copyright reserved by NewHeight LTD.
 * 
 */
package com.first.aop.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * BaseException
 * 
 * @author wumingchao
 * @since   JDK1.6
 */

public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 779097140579256830L;

    private String            errCode;

    private List<String>      params           = new ArrayList<String>(0);

    private String            message;

    public BaseException() {

    }

    public BaseException(String errCode) {
        this.errCode = errCode;
        if (message == null) {
            this.message = errCode;
        }
    }

    public BaseException(String errCode, List<String> params) {
        this.errCode = errCode;
        this.params = params;
        if (message == null) {
            this.message = errCode;
        }
    }

    public BaseException(String errCode, String[] params) {
        this.errCode = errCode;
        for (String s : params) {
            this.params.add(s);
        }
        if (message == null) {
            this.message = errCode;
        }
    }

    public BaseException(String errCode, String message) {
        this.errCode = errCode;
        this.message = message;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
        if (message == null) {
            this.message = errCode;
        }
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void addParam(String p) {
        params.add(p);
    }
}

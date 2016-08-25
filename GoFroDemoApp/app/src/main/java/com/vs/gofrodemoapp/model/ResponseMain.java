package com.vs.gofrodemoapp.model;

import java.io.Serializable;

/**
 * Created by mayank on 25-Aug-16.
 */
public class ResponseMain implements Serializable {

    private ResponseData responseData;

    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }
}

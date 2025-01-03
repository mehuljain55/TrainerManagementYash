package com.yash.HrManager.Entity.models;

import com.yash.HrManager.Entity.enums.StatusResponse;

public class ApiResponseModel<T> {
   private StatusResponse status;
   private T payload;
   private String message;

    public ApiResponseModel(StatusResponse status, T payload, String message) {
        this.status = status;
        this.payload = payload;
        this.message = message;
    }

    public ApiResponseModel() {
    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

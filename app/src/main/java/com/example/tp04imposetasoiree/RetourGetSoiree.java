package com.example.tp04imposetasoiree;

import java.util.List;

public class RetourGetSoiree {
 private  String request ;
 private boolean success ;
 private List<Soiree> response ;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Soiree> getResponse() {
        return response;
    }

    public void setResponse(List<Soiree> response) {
        this.response = response;
    }
}

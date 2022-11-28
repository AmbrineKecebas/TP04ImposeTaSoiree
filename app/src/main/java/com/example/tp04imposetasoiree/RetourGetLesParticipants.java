package com.example.tp04imposetasoiree;

import java.util.List;

public class RetourGetLesParticipants {
    private  String request ;
    private boolean success ;
    private List<Membre> response ;

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

    public List<Membre> getResponse() {
        return response;
    }

    public void setResponse(List<Membre> response) {
        this.response = response;
    }
}

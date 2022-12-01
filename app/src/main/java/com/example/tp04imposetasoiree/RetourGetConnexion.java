package com.example.tp04imposetasoiree;

public class RetourGetConnexion {
    private String request;
    private boolean success;
    private Membre response;

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

    public Membre getResponse() {
        return response;
    }

    public void setResponse(Membre response) {
        this.response = response;
    }
}

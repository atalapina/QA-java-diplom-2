package org.example;

import groovy.lang.GString;

public class GetUserResponse {
    private String success;
    private UserCredentials user;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public UserCredentials getUser() {
        return user;
    }

    public void setUser(UserCredentials user) {
        this.user = user;
    }
}

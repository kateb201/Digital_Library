package com.example.demo.sprint2;

public class userId {

    private String space;
    private String email;

    public userId(String space, String email) {
        this.space = space;
        this.email = email;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

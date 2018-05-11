package com.luizpaulo.apidesafiocs.vo;

import org.springframework.context.annotation.ComponentScan;

public class LoginVO {

    private String email;

    private String password;

    public LoginVO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginVO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

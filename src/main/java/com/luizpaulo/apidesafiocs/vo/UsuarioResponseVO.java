package com.luizpaulo.apidesafiocs.vo;

public class UsuarioResponseVO {

    private String id;
    private String created;
    private String modified;
    private String last_login;
    private String token;

    public UsuarioResponseVO(String id, String created, String modified, String last_login, String token) {
        this.id = id;
        this.created = created;
        this.modified = modified;
        this.last_login = last_login;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

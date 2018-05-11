package com.luizpaulo.apidesafiocs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Usuario {

    @Id
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID id;

    private String name;

    private String email;

    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Telefone> phones;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", locale = "pt-BR", timezone = "Brazil/East")
    private Date created;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", locale = "pt-BR", timezone = "Brazil/East")
    private Date modified;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", locale = "pt-BR", timezone = "Brazil/East")
    private Date lastLogin;

    private String token;

    public Usuario() {
        this.setId(UUID.randomUUID());
        this.setCreated(new Date());
        this.setLastLogin(new Date());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

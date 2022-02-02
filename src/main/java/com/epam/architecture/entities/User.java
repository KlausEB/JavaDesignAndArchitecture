package com.epam.architecture.entities;

import com.epam.architecture.roles.RoleEnum;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USER", schema = "HOME_LIBRARY")
public class User implements Serializable {
    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
    @Id
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "PASSWORD")
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.role = RoleEnum.USER;
    }

    public User(String login, String password, RoleEnum role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "role=" + role +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!login.equals(user.login)) return false;
        return password.equals(user.password);
    }

    @Override
    public int hashCode() {
        int result = login.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}

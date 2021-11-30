package com.epam.architecture.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "USER", schema = "HOME_LIBRARY")
public class User implements Serializable {
    @Column(name = "ADMINRIGHTS")
    boolean adminRights;
    @Id
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "PASSWORD")
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String password, boolean adminRights) {
        this.login = login;
        this.password = password;
        this.adminRights = adminRights;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "adminRights=" + adminRights +
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

    public boolean isAdminRights() {
        return adminRights;
    }

    public void setAdminRights(boolean adminRights) {
        this.adminRights = adminRights;
    }
}

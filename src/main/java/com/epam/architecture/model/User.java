package com.epam.architecture.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "USER", schema = "HOME_LIBRARY")
public class User implements Serializable {
    @Id
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
    @Column(name = "PASSWORD")
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "USERLOGIN")
    private Set<Bookmark> bookmarks;

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

        if (!Objects.equals(login, user.login)) return false;
        if (role != user.role) return false;
        return Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
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

package com.epam.architecture.service;

import com.epam.architecture.model.RoleEnum;
import com.epam.architecture.model.User;

public interface AccountService {
    User getUser(String login);

    User createUser(String login, String password);

    boolean logInUser(String login, String password);

    boolean addAccount(String login, String password);

    void deleteUser(String login);

    RoleEnum getUserRole(String login);

    String takeHistory();
}

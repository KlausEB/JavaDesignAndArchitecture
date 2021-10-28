package com.epam.designAndArchitecture.account;


import com.epam.designAndArchitecture.entities.User;

import java.util.HashSet;
import java.util.Set;

public class AccountManager {
    private final Set<User> userSet = new HashSet<>();

    public User createUser(String login, String password) {
        return new User(login, password);
    }

    public User createUser(String login) {
        return new User(login);
    }

    public boolean logInUser(String login, String password) {
        User searchUser = createUser(login, password);
        return userSet.contains(searchUser);
    }

    public boolean signUpUser(String login, String password) {
        User newUser = createUser(login, password);
        return userSet.add(newUser);
    }

    public boolean deleteUser(String login) {
        User deletingUser = createUser(login);
        return userSet.remove(deletingUser);
    }
}

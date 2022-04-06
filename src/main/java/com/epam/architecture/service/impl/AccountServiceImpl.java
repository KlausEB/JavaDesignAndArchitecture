package com.epam.architecture.service.impl;


import com.epam.architecture.exceptions.HistoryException;
import com.epam.architecture.model.RoleEnum;
import com.epam.architecture.model.User;
import com.epam.architecture.repository.JpaUserRepository;
import com.epam.architecture.service.AccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Log4j2
@Service
public class AccountServiceImpl implements AccountService {
    private final JpaUserRepository userRepository;
    public File historyFile;
    @Value("${history}")
    private String historyPath;

    public AccountServiceImpl(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    private void historyFileConfig() {
        historyFile = new File(historyPath);
    }

    @Override
    public User getUser(String login) {
        return userRepository.findById(login).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
    }

    @Override
    public User createUser(String login, String password) {
        return new User(login, password);
    }

    @Override
    public boolean logInUser(String login, String password) {
        User userForSearch = createUser(login, password);
        User searchedUser = userRepository.findById(login).get();
        return userForSearch.equals(searchedUser);
    }

    @Override
    public boolean addAccount(String login, String password) {
        if (userRepository.existsById(login)) {
            return false;
        }
        User newUser = createUser(login, password);
        userRepository.save(newUser);
        return true;
    }

    @Override
    public void deleteUser(String login) {
        userRepository.deleteById(login);
    }

    @Override
    public RoleEnum getUserRole(String login) {
        return userRepository.findById(login).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists")).getRole();
    }

    @Override
    public String takeHistory() {
        try {
            return Files.readString(Paths.get(historyFile.getPath()));
        } catch (IOException e) {
            HistoryException exception = new HistoryException(e);
            log.catching(exception);
        }
        return "You cannot read history";
    }
}
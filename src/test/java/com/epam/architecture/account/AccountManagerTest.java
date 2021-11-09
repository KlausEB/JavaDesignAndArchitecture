package com.epam.architecture.account;

import com.epam.architecture.entities.User;
import com.epam.architecture.util.BookmarkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountManagerTest {
    private final String login = "Nikolai";
    private final String password = "12345";

    @InjectMocks
    @Spy
    private AccountManager accountManager;

    @Mock
    private User user;
    @Mock
    private Map<String, User> userMap;
    @Mock
    private BookmarkService bookmarkService;

    @BeforeEach
    void setUp() {
        accountManager.setUserMap(userMap);
        accountManager.setBookmarkService(bookmarkService);
    }

    @Test
    void logInUser_False_NotExistingUserLoginAttempt() {
        //GIVEN
        when(accountManager.createUser(login, password)).thenReturn(user);
        when(userMap.get(login)).thenReturn(null);

        //WHEN
        boolean registrationCheck = accountManager.logInUser(login, password);

        //THEN
        assertFalse(registrationCheck);
        verifyNoInteractions(bookmarkService);
    }

    @Test
    void logInUser_False_IncorrectPasswordUserLoginAttempt() {
        //GIVEN
        String wrongPassword = "11111";
        User userWithWrongPassword = new User(login, wrongPassword);
        when(accountManager.createUser(login, wrongPassword)).thenReturn(userWithWrongPassword);
        when(userMap.get(login)).thenReturn(user);

        //WHEN
        boolean registrationCheck = accountManager.logInUser(login, wrongPassword);

        //THEN
        assertFalse(registrationCheck);
        verifyNoInteractions(bookmarkService);
    }
}
package com.epam.designAndArchitecture.account;

import com.epam.designAndArchitecture.entities.User;
import com.epam.designAndArchitecture.util.BookmarkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        accountManager.setCurrentBookmarks(bookmarkService);
    }

    @Test
    void logInUser_True_ExistingUserLoginAttempt() {
        //GIVEN
        when(accountManager.createUser(login, password)).thenReturn(user);
        when(userMap.get(login)).thenReturn(user);

        //WHEN
        boolean registrationCheck = accountManager.logInUser(login, password);

        //THEN
        assertTrue(registrationCheck);
        verify(bookmarkService).setCurrentUserBookmarks(anyList());
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

    @Test
    void signUpUser_True_NewUserSignUp() {
        //GIVEN
        when(accountManager.appendAccount(login, password)).thenReturn(user);

        //WHEN
        boolean registrationCheck = accountManager.signUpUser(login, password);

        //THEN
        assertTrue(registrationCheck);
        verify(bookmarkService).setCurrentUserBookmarks(anyList());
    }

    @Test
    void appendAccount_True_NewUserAppend() {
        //GIVEN
        when(accountManager.createUser(login, password)).thenReturn(user);
        when(userMap.containsKey(login)).thenReturn(false);

        //WHEN
        User appendedAccount = accountManager.appendAccount(login, password);

        //THEN
        assertEquals(user, appendedAccount);
        verifyNoInteractions(bookmarkService);
    }
}
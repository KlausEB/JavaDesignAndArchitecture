package com.epam.architecture.account;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
//    private final String login = "Nikolai";
//    private final String password = "12345";
//
//    @InjectMocks
//    @Spy
//    private AccountService accountService;
//
//    @Mock
//    private User user;
//    @Mock
//    private Map<String, User> userMap;
//    @Mock
//    private BookmarkService bookmarkService;
//
//    @BeforeEach
//    void setUp() {
//        accountService.setUserMap(userMap);
//        accountService.setBookmarkService(bookmarkService);
//    }
//
//    @Test
//    void logInUser_False_NotExistingUserLoginAttempt() {
//        //GIVEN
//        when(accountService.createUser(login, password)).thenReturn(user);
//        when(userMap.get(login)).thenReturn(null);
//
//        //WHEN
//        boolean registrationCheck = accountService.logInUser(login, password);
//
//        //THEN
//        assertFalse(registrationCheck);
//        verifyNoInteractions(bookmarkService);
//    }
//
//    @Test
//    void logInUser_False_IncorrectPasswordUserLoginAttempt() {
//        //GIVEN
//        String wrongPassword = "11111";
//        User userWithWrongPassword = new User(login, wrongPassword);
//        when(accountService.createUser(login, wrongPassword)).thenReturn(userWithWrongPassword);
//        when(userMap.get(login)).thenReturn(user);
//
//        //WHEN
//        boolean registrationCheck = accountService.logInUser(login, wrongPassword);
//
//        //THEN
//        assertFalse(registrationCheck);
//        verifyNoInteractions(bookmarkService);
//    }
}
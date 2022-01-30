package com.epam.architecture.webapp.commands;

import com.epam.architecture.userinterface.LibraryService;
import com.epam.architecture.webapp.util.CookieService;
import com.epam.architecture.webapp.util.LibraryWebWorker;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieCommand implements WebCommand {
    public static final String LOGIN_NAME = "login";
    public static final String PASSWORD_NAME = "password";
    public static final String RIGHTS = "rights";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] requestCookies = request.getCookies();
        String login = CookieService.searchCookieByName(requestCookies, LOGIN_NAME);
        String password = CookieService.searchCookieByName(requestCookies, PASSWORD_NAME);
        String rights = CookieService.searchCookieByName(requestCookies, RIGHTS);
        if (login != null && password != null) {
            LibraryService libraryService = LibraryWebWorker.takeLibraryService();
            if (libraryService.logInAccount(login, password)) {
                request.getSession().setAttribute(LOGIN_NAME, login);
                request.getSession().setAttribute(PASSWORD_NAME, password);
                request.getSession().setAttribute(RIGHTS, rights);
                request.getRequestDispatcher(LoginCommand.MAIN_PAGE).forward(request, response);
                return;
            }
        }
        request.getRequestDispatcher(LoginCommand.LOGIN_PAGE).forward(request, response);
    }
}

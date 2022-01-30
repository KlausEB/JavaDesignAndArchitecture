package com.epam.architecture.webapp.commands;

import com.epam.architecture.userinterface.LibraryService;
import com.epam.architecture.webapp.util.AuthorizeUtil;
import com.epam.architecture.webapp.util.LibraryWebWorker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCommand implements WebCommand {
    public static final String LOGIN_PAGE = "jsp/login.jsp";
    public static String MAIN_PAGE = "jsp/main.jsp";
    public static String INCORRECT_LOGIN_PASSWORD_MESSAGE = "Incorrect login or password entered";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        if (libraryService.logInAccount(login, password)) {
            AuthorizeUtil.authorizeUser(request, response, login, password);
        } else {
            request.setAttribute("incorrectLoginPassword", INCORRECT_LOGIN_PASSWORD_MESSAGE);
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
    }
}

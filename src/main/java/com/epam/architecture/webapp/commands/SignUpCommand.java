package com.epam.architecture.webapp.commands;

import com.epam.architecture.userinterface.LibraryService;
import com.epam.architecture.webapp.util.AuthorizeUtil;
import com.epam.architecture.webapp.util.LibraryWebWorker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpCommand implements WebCommand {
    public static final String SIGNUP_PAGE = "jsp/signup.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        if (libraryService.signUpAccount(login, password)) {
            AuthorizeUtil.authorizeUser(request, response, login, password);
        } else {
            request.setAttribute("incorrectLoginPassword", LoginCommand.INCORRECT_LOGIN_PASSWORD_MESSAGE);
            request.getRequestDispatcher(SIGNUP_PAGE).forward(request, response);
        }
    }
}

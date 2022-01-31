package com.epam.architecture.webapp.util;

import com.epam.architecture.userinterface.LibraryService;
import com.epam.architecture.webapp.commands.CookieCommand;
import com.epam.architecture.webapp.commands.LoginCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizeUtil {
    public static void authorizeUser(HttpServletRequest request, HttpServletResponse response, String login, String password) throws ServletException, IOException {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        request.getSession().setAttribute(CookieCommand.LOGIN_NAME, login);
        request.getSession().setAttribute(CookieCommand.PASSWORD_NAME, password);
        CookieService.appendCookieResponse(response, CookieCommand.LOGIN_NAME, login);
        CookieService.appendCookieResponse(response, CookieCommand.PASSWORD_NAME, password);
        if (libraryService.userIsAdmin(login)) {
            String adminRights = "admin";
            request.getSession().setAttribute("rights", adminRights);
            request.getSession().setAttribute(CookieCommand.RIGHTS, adminRights);
            CookieService.appendCookieResponse(response, CookieCommand.RIGHTS, adminRights);
        }
        request.getRequestDispatcher(LoginCommand.MAIN_PAGE).forward(request, response);
    }
}

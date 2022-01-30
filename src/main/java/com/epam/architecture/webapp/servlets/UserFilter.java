package com.epam.architecture.webapp.servlets;

import com.epam.architecture.userinterface.LibraryService;
import com.epam.architecture.webapp.commands.CookieCommand;
import com.epam.architecture.webapp.commands.WebCommandFactory;
import com.epam.architecture.webapp.util.LibraryWebWorker;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/jsp/*"})
public class UserFilter extends HttpFilter {
    public static final String COMMAND_KEY_IN_HTTP = WebCommandFactory.COMMAND_KEY_IN_HTTP;

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String login = (String) request.getSession().getAttribute(CookieCommand.LOGIN_NAME);
        String password = (String) request.getSession().getAttribute(CookieCommand.PASSWORD_NAME);
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        if (login == null || password == null || !libraryService.logInAccount(login, password)) {
            request.getRequestDispatcher("login.jsp").forward(request, response);

        }
        filterChain.doFilter(request, response);
    }
}

package com.epam.architecture.webapp.servlets;

import com.epam.architecture.userinterface.LibraryService;
import com.epam.architecture.webapp.commands.CookieCommand;
import com.epam.architecture.webapp.util.LibraryWebWorker;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/jsp/adminRights/*"})
public class AdminFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String login = (String) request.getSession().getAttribute(CookieCommand.LOGIN_NAME);
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        if (login == null || !libraryService.userIsAdmin(login)) {
            request.getRequestDispatcher("../main.jsp").forward(request, response);

        }
        filterChain.doFilter(request, response);
    }
}

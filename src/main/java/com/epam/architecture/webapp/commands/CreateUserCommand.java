package com.epam.architecture.webapp.commands;

import com.epam.architecture.userinterface.LibraryService;
import com.epam.architecture.webapp.util.LibraryWebWorker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateUserCommand implements WebCommand {
    public static String CREATE_USER_PAGE = "jsp/createUser.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adminLogin = (String) request.getSession().getAttribute(CookieCommand.LOGIN_NAME);
        String newLogin = request.getParameter("newLogin");
        String password = request.getParameter("password");
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        if (libraryService.appendNewUser(adminLogin, newLogin, password)) {
            request.setAttribute("result", "The creation was successful");
        }
        request.getRequestDispatcher(CREATE_USER_PAGE).forward(request, response);
    }
}

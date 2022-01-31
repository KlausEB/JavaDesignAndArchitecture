package com.epam.architecture.webapp.commands;

import com.epam.architecture.userinterface.LibraryService;
import com.epam.architecture.webapp.util.LibraryWebWorker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BanUserCommand implements WebCommand {
    public static String BAN_USER_PAGE = "jsp/adminRights/banUser.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deleteLogin = request.getParameter("deleteLogin");
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        if (libraryService.banUser(deleteLogin)) {
            request.setAttribute("result", "The blocking was successful");
        }
        request.getRequestDispatcher(BAN_USER_PAGE).forward(request, response);
    }
}

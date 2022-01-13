package com.epam.architecture.webapp.commands;

import com.epam.architecture.userinterface.LibraryService;
import com.epam.architecture.webapp.util.LibraryWebWorker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HistoryOperationsCommand implements WebCommand {
    public static String SHOW_HISTORY_OPERATIONS_PAGE = "jsp/showHistoryOperations.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getSession().getAttribute(CookieCommand.LOGIN_NAME);
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        String history = libraryService.takeHistory(login);
        request.setAttribute("history", history);
        request.getRequestDispatcher(SHOW_HISTORY_OPERATIONS_PAGE).forward(request, response);
    }
}

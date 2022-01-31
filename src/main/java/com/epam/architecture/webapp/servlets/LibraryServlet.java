package com.epam.architecture.webapp.servlets;

import com.epam.architecture.webapp.commands.WebCommandFactory;
import com.epam.architecture.webapp.util.LibraryWebWorker;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "libraryServlet", value = "/library-servlet")
public class LibraryServlet extends HttpServlet {
    private final ServletConfig servletConfig = this.getServletConfig();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        runCommands(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        runCommands(request, response);
    }

    private void runCommands(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        WebCommandFactory.takeCommand(request).execute(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
        LibraryWebWorker.takeLibraryService().closeResources();
    }
}
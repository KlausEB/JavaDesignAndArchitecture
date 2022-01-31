package com.epam.architecture.webapp.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface WebCommand {
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}

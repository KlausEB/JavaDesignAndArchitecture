package com.epam.architecture.webapp.commands;

import javax.servlet.http.HttpServletRequest;

public class WebCommandFactory {
    public static final String COMMAND_KEY_IN_HTTP = "command";

    public static WebCommand takeCommand(HttpServletRequest request) {
        CommandsEnum currentCommandEnum = CommandsEnum.valueOf(request.getParameter(COMMAND_KEY_IN_HTTP).toUpperCase());
        WebCommand currentCommand = currentCommandEnum.getCurrentCommand();
        return currentCommand;
    }
}

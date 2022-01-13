package com.epam.architecture.webapp.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieService {
    public static int COOKIE_LIFETIME = 10000;

    public static String searchCookieByName(Cookie[] cookies, String name) {
        for (Cookie currentCookie : cookies) {
            if (name.equals(currentCookie.getName())) {
                return currentCookie.getValue();
            }
        }
        return null;
    }

    public static void appendCookieResponse(HttpServletResponse response, String cookieName, String cookieValue) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(COOKIE_LIFETIME);
        response.addCookie(cookie);
    }
}

package com.epam.architecture.roles;

import com.epam.architecture.endpoint.dto.UserDTO;
import com.epam.architecture.userinterface.LibraryService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.Set;

public class AuthorizationUtil {
    public final static Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static Response authorizationRequest(UserDTO user) {
        LibraryService libraryService = LibraryService.getInstanceWithDeserializeData();
        String login = user.getLogin();
        String password = user.getPassword();
        if (libraryService.logInAccount(login, password)) {
            RoleEnum role = libraryService.userRole(login);
            String jwtToken = generateJWTToken(login, role);
            NewCookie cookie = new NewCookie("jwtToken", jwtToken);
            return Response.status(Response.Status.OK).cookie(cookie).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    public static boolean isAuthorizeRequest(ContainerRequestContext containerRequestContext, Set<RoleEnum> roles) {
        Cookie jwtCookie = containerRequestContext.getCookies().get("jwtToken");
        String jwtToken = jwtCookie.getValue();
        RoleEnum role;
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(jwtToken);
            role = RoleEnum.valueOf(claimsJws.getBody().get("role", String.class));
        } catch (Exception e) {
            return false;
        }
        return roles.contains(role);
    }

    public static String getLoginFromRequest(HttpHeaders context) {
        Cookie jwtCookie = context.getCookies().get("jwtToken");
        String jwtToken = jwtCookie.getValue();
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(jwtToken);
        String login = claimsJws.getBody().get("login", String.class);
        if (login != null) {
            return login;
        }
        throw new NotAuthorizedException("Not found login");
    }

    private static String generateJWTToken(String login, RoleEnum role) {
        return Jwts.builder()
                .claim("login", login)
                .claim("role", role)
                .signWith(SECRET_KEY)
                .compact();
    }
}

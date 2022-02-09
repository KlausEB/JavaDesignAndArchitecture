package com.epam.architecture.roles;

import com.epam.architecture.RESTws.DTO.UserDTO;
import com.epam.architecture.userinterface.LibraryService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.xml.ws.WebServiceContext;

import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.security.Key;

public class AuthorizationUtil {
    public static Response authorizationRequest(UserDTO user) {
        LibraryService libraryService = LibraryService.getInstanceWithDeserializeData();
        String login = user.getLogin();
        String password = user.getPassword();
        if (libraryService.logInAccount(login, password)) {
            RoleEnum role = libraryService.userRole(login);
            String jwtToken = generateJWTToken(login, role);
            NewCookie cookie = new NewCookie("jwt", jwtToken);
            return Response.status(Response.Status.OK).cookie(cookie).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    public static String getLoginFromRequest(WebServiceContext context) {
//        SOAPMessage soapMessage = ((AbstractWebServiceContext) context).getRequestPacket().getMessage().readAsSOAPMessage();
//        SOAPEnvelope soapEnv = soapMessage.getSOAPPart().getEnvelope();
//        SOAPHeader soapHeader = soapEnv.getHeader();
//        Iterator<SOAPHeaderElement> headerElements = soapHeader.examineHeaderElements(SOAPConstants.URI_SOAP_ACTOR_NEXT);
//        while (headerElements.hasNext()) {
//            Node node = headerElements.next();
//            if (node.getNodeName().equals("login")) {
//                return node.getValue();
//            }
//        }
//        throw new NotAuthorizedException("Not found login");

        return "Nikolai";
    }

    private static String generateJWTToken(String login, RoleEnum role) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.ES256);
        return Jwts.builder()
                .claim("login", login)
                .claim("role", role)
                .signWith(key)
                .compact();
    }
}

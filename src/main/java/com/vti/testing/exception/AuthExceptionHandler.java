package com.vti.testing.exception;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler {
    @Autowired
    private MessageSource messageSource;

    private String getMessage(String key) {
        return messageSource.getMessage(key, null, "An error occur", LocaleContextHolder.getLocale());
    }
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String message = getMessage("AuthenticationException.message");
        String detailMessage = authException.getLocalizedMessage();
        int code = 8;
        String moreInformation = "http://localhost:8080/api/v1/exception/8";

        ErrorResponse errorResponse = new ErrorResponse(message, detailMessage, null, code, moreInformation);

//        log.error("HttpServletRequest: "+errorResponse);

        Gson gson = new Gson();
        String json = gson.toJson(errorResponse);

        response.setContentType("application/json");
        response.getWriter().write(json);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String message = getMessage("AccessDeniedException.message");
        String detailMessage = accessDeniedException.getLocalizedMessage();
        int code = 9;
        String moreInformation = "http://localhost:8080/api/v1/exception/9";

        ErrorResponse errorResponse = new ErrorResponse(message, detailMessage, null, code, moreInformation);

//        log.error("HttpServletRequest: "+errorResponse);
        Gson gson = new Gson();
        String json = gson.toJson(errorResponse);

        response.setContentType("application/json");
        response.getWriter().write(json);
        response.setStatus(HttpStatus.FORBIDDEN.value());
    }
}

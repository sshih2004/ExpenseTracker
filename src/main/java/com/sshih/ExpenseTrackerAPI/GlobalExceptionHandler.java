package com.sshih.ExpenseTrackerAPI;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleAllError(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = (Authentication) request.getUserPrincipal();
        if (auth != null) {
            // Perform logout
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }
}
package com.example.osmarspetitions.controller;

import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    public CustomErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        ServletWebRequest webRequest = new ServletWebRequest(request);
        Map<String, Object> attrs = errorAttributes.getErrorAttributes(
                webRequest,
                ErrorAttributeOptions.of(
                        ErrorAttributeOptions.Include.MESSAGE,
                        ErrorAttributeOptions.Include.EXCEPTION
                )
        );

        // Copia os campos úteis para o modelo (se existirem)
        model.addAttribute("timestamp", attrs.get("timestamp"));
        model.addAttribute("status", attrs.get("status"));
        model.addAttribute("error", attrs.get("error"));
        model.addAttribute("message", attrs.get("message"));
        model.addAttribute("path", attrs.get("path"));

        return "error"; // templates/error.html
    }
}

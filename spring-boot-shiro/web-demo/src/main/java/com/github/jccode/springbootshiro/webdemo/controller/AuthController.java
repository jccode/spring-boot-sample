package com.github.jccode.springbootshiro.webdemo.controller;

import com.github.jccode.springbootshiro.webdemo.filter.RestAuthenticationFilter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping
public class AuthController {

    @RequestMapping(value = "login")
    public String loginFailed(HttpServletRequest request, HttpServletResponse response) {
        boolean result = (boolean) request.getAttribute(RestAuthenticationFilter.LOGIN_RESULT);
        if (result) {
            response.setStatus(HttpServletResponse.SC_OK);
            return "login success";
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return "login failed";
        }
    }

    @RequestMapping(value = "unauthrized")
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public String unAuthorized() {
        return "un-authorized";
    }

    @RequestMapping(value = "logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "logout success";
    }
}

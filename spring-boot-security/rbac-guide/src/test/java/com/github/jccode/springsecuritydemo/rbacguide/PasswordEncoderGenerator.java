package com.github.jccode.springsecuritydemo.rbacguide;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * PasswordEncoderGenerator
 *
 * @author 01372461
 */
public class PasswordEncoderGenerator {

    public static void main(String[] args) {
        System.out.println(pwd("admin"));
        System.out.println(pwd("cat"));
        System.out.println(pwd("lucky"));
        System.out.println(pwd("000000"));
    }

    private static String pwd(String password) {
        return encoder().encode(password);
    }

    private static BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
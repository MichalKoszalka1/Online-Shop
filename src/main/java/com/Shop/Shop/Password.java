package com.Shop.Shop;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Password {

    public static void main(String[]args){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "";
        String encoderPassword = encoder.encode(rawPassword);
        System.out.println(encoderPassword);
    }
}

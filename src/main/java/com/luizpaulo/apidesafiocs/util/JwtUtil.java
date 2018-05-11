package com.luizpaulo.apidesafiocs.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

    public static final String SECRET_KEY = "des@fiocs";

    public static String getToken(String username, String email) {

        String JWT = Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .claim("email", email)
                .compact();

        return JWT;

    }

}

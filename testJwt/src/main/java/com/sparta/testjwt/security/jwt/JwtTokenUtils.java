package com.sparta.testjwt.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sparta.testjwt.security.UserDetailsImpl;

import java.util.Date;

public final class JwtTokenUtils {
//생성될 토큰의 유효기간과 알고리즘 설정을 해줌.
    private static final int SEC = 1;
    private static final int MINUTE = 60 * SEC;
    private static final int HOUR = 60 * MINUTE;
    private static final int DAY = 24 * HOUR;

    // JWT 토큰의 유효기간: 3일 (단위: seconds)
    private static final int JWT_TOKEN_VALID_SEC = 3 * DAY;
    // JWT 토큰의 유효기간: 3일 (단위: milliseconds)
    private static final int JWT_TOKEN_VALID_MILLI_SEC = JWT_TOKEN_VALID_SEC * 1000;


    public static final String CLAIM_EXPIRED_DATE = "EXPIRED_DATE";
    public static final String CLAIM_USER_NAME = "USER_NAME";
    public static final String JWT_SECRET = "jwt_secret_!@#$%";

    public static String generateJwtToken(UserDetailsImpl userDetails) {
        System.out.println("userDetails.getUsername() = " + userDetails.getUsername());
        String token = null;
        try {
            token = JWT.create()
                    .withIssuer("sparta")
                    .withClaim(CLAIM_USER_NAME, userDetails.getUsername())  // payload에 들어가는 내용
                    .withClaim(CLAIM_EXPIRED_DATE, new Date(System.currentTimeMillis() + JWT_TOKEN_VALID_MILLI_SEC))
                    .sign(generateAlgorithm());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("JwtTokenUtils token = " + token);
        return token;
    }

    private static Algorithm generateAlgorithm() {
        return Algorithm.HMAC256(JWT_SECRET);
    }
}
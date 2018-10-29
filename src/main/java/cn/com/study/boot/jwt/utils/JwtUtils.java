package cn.com.study.boot.jwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static final String SECRET = "STUDY_JWT_TOKEN";

    public static Claims parseJwtToken(String token) {
        Jws<Claims> jws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        String signature = jws.getSignature();
        Map<String, String> header = jws.getHeader();
        Claims claims = jws.getBody();
        return claims;
    }


    public static String getJwtToken(String userId){
        Date iatDate = new Date();
        // expire time
        Calendar nowTime = Calendar.getInstance();
        //有10天有效期
        nowTime.add(Calendar.DATE, 10);
        Date expiresDate = nowTime.getTime();
        Claims claims = Jwts.claims();
        claims.put("userId", userId);
        claims.setAudience("cy");
        claims.setIssuer("cy");
        String token = Jwts.builder().setClaims(claims).setExpiration(expiresDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return token;
    }
}

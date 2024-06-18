package com.cdut.tiktok.common.utils;

import com.cdut.tiktok.common.exception.ExceptionCode;
import com.cdut.tiktok.common.exception.MyException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtils {
    public static final int ACCESS_EXPIRE = 604800;
    private static final SecureDigestAlgorithm<SecretKey, SecretKey> ALGORITHM = Jwts.SIG.HS256;
    private static final String SECRET = "jsabdhjvamavnamajkagjvveavneakdsdmnfsjmkdndbsndnsterwf";
    public static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
    private static final String JWT_ISS = "zzzlyly";
    private static final String SUBJECT = "Tiktokusers";
    private static final String HEADER = "Authorization";

    // 新增方法获取 userid
    public Long getUserIdFromToken(String token) {
        if (token == null || token.trim().isEmpty()) {
            throw new MyException(ExceptionCode.INVALID_ARGUMENT);
        }
        try {
            Claims claims = parsePayload(token);
            if (isTokenExpired(claims)) {
                throw new MyException(ExceptionCode.UNAUTHENTICATED);
            }
            String userId = claims.get("userid", String.class);
            if (userId == null) {
                throw new MyException(ExceptionCode.INVALID_ARGUMENT);
            }

            return Long.parseLong(userId);
        } catch (MyException e) {
            // 日志记录错误或其他处理
            throw new MyException(ExceptionCode.UNAUTHENTICATED);
        }
    }




    public String generateToken(String userid) {
        String uuid = UUID.randomUUID().toString();
        Date exprireDate = Date.from(Instant.now().plusSeconds(ACCESS_EXPIRE));

        return Jwts.builder()
                .header()
                .add("typ", "JWT")
                .add("alg", "HS256")
                .and()
                .claim("userid", userid)
                .id(uuid)
                .expiration(exprireDate)
                .issuedAt(new Date())
                .subject(SUBJECT)
                .issuer(JWT_ISS)
                .signWith(KEY, ALGORITHM)
                .compact();
    }

    public static Jws<Claims> parseClaim(String token) {
        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token);
    }

    public String getHeader() {
        return HEADER;
    }

    public JwsHeader parseHeader(String token) {
        return parseClaim(token).getHeader();
    }

    public Claims parsePayload(String token) {
        return parseClaim(token).getPayload();
    }

    public boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

}

package utils;

import enums.TokenEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

/**
 * @program: spring-security
 * @description:
 * @author: fbl
 * @create: 2020-12-02 14:08
 **/
public class JwtTokenUtils {
    // 创建token
    public static String createToken(String username, String permission, boolean isRememberMe) {
        byte[] keyBytes = Decoders.BASE64.decode(TokenEnum.SECRET.getValue());
        Key key = Keys.hmacShaKeyFor(keyBytes);

        long expiration = isRememberMe ? TokenEnum.EXPIRATION_REMEMBER.getTime() : TokenEnum.EXPIRATION.getTime();
        HashMap<String, Object> map = new HashMap<>();
        map.put(TokenEnum.ROLE_CLAIMS.getValue(), permission);
        return Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS512)
                // 这里要早set一点，放到后面会覆盖别的字段
                .setClaims(map)
                .setIssuer(TokenEnum.ISS.getValue())
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    // 从token中获取用户名
    public static String getUsername(String token) {
        return getTokenBody(token).getSubject();
    }

    // 是否已过期
    public static boolean isExpiration(String token) {
        return getTokenBody(token).getExpiration().before(new Date());
    }

    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(TokenEnum.SECRET.getValue())
                .parseClaimsJws(token)
                .getBody();
    }

    public static String getUserPermission(String token) {
        return (String) getTokenBody(token).get(TokenEnum.ROLE_CLAIMS.getValue());
    }
}


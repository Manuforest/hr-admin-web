package com.edu.common.utils;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {
    private static final long JWT_EXPIRE = 30 * 60 * 1000L;  //半小时//令牌秘钥
    private static final String JWT_KEY = "123456";

    public String createToken(Object data) {
        //当前时间
        long currentTime = System.currentTimeMillis();
        //过期时间
        long expTime = currentTime + JWT_EXPIRE;
        //构建jwt
        JwtBuilder builder = Jwts.builder()
                .setId(String.valueOf(UUID.randomUUID()))
                .setSubject(JSON.toJSONString(data))
                .setIssuer("system")
                .setIssuedAt(new Date(currentTime))
                .signWith(SignatureAlgorithm.HS256, encodeSecret())
                .setExpiration(new Date(expTime));
        return builder.compact();


    }

    private SecretKey encodeSecret() {
        byte[] encode = Base64.getEncoder().encode(JwtUtil.JWT_KEY.getBytes());
        return new SecretKeySpec(encode, 0, encode.length, "AES");
    }

    public Claims parseToken(String token) {


        return Jwts.parser()
                .setSigningKey(encodeSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T parseToken(String token, Class<T> clazz) {
        Claims body = Jwts.parser()
                .setSigningKey(encodeSecret())
                .parseClaimsJws(token)
                .getBody();
        return JSON.parseObject(body.getSubject(), clazz);
    }
}

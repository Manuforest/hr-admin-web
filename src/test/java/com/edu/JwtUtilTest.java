package com.edu;

import com.edu.sys.entity.User;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtUtilTest {
    @Autowired
    private com.edu.common.utils.JwtUtil JwtUtil;

    @Test
    public void testCreateJwt(){
        User user = new User();
        user.setUsername("wtz");
        user.setPhone("12312312345");
        String token = JwtUtil.createToken(user);
        System.out.println(token);
    }

    @Test
    public void testParseJwt(){
        String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIwMWNhNmIyNi01MWE0LTQ1YzAtYjUwMC1iZDMzODU1NGQ0YjMiLCJzdWIiOiJ7XCJwaG9uZVwiOlwiMTIzMTIzMTIzNDVcIixcInVzZXJuYW1lXCI6XCJ3dHpcIn0iLCJpc3MiOiJzeXN0ZW0iLCJpYXQiOjE2OTU2MjY1NTQsImV4cCI6MTY5NTYyODM1NH0.qle0uRuaGm0pvkfozWPDJ6kfZ_WMHZPebSOdaVIIMPI";
        Claims claims = JwtUtil.parseToken(token);
        System.out.println(claims);
    }
}

package org.example.stylish;

import org.example.stylish.service.serviceImpl.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestJwt {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

//    @Test
//    public void test() {
//        String token = jwtTokenProvider.createToken("native", "Arvin", "Arvintest@gmail.com", "null");
//        System.out.println(token);
//    }

}

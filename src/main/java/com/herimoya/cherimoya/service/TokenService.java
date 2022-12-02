package com.herimoya.cherimoya.service;

import com.Project.Post2.dao.TokenRepository;
import com.Project.Post2.entity.Token;
import com.Project.Post2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
public class TokenService {
    @Autowired
    private TokenRepository tokenRepository;

    public Integer makeToken() {
        return new Random().nextInt(900000) + 100000;
    }

    public Token getToken(Integer token) {
        return (Token) tokenRepository.findByToken(token).orElse(null);
    }

    public Token saveToken(User user, Integer tokenCode) {
        Token token = Token.builder()
                .time(new Date(System.currentTimeMillis()))
                .token(tokenCode)
                .user(user)
                .build();
        tokenRepository.save(token);
        return token;
    }

    public void deleteToken(Token token) {
        tokenRepository.delete(token);
    }

    public Token findByUserAndToken(User users, Integer token) {
        return (Token) tokenRepository.findByTokenAndUser(token, users)
                .orElseThrow(() -> new NoSuchElementException(String.format("Не найден токен %s для пользователя", token, users.getEmail())));
    }
}
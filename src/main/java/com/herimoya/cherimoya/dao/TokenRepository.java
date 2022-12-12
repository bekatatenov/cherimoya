package com.herimoya.cherimoya.dao;


import com.herimoya.cherimoya.entity.Token;
import com.herimoya.cherimoya.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
    Optional<Object> findByTokenAndUser(Integer token, User user);
    Optional<Object> findByToken(Integer token);
}
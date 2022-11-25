package com.cherimoya.cherimoya.dao;

import com.cherimoya.cherimoya.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findFirstByEmail(String email);
    User findFirstByPassword(String password);
}

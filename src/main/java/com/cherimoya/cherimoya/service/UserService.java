package com.cherimoya.cherimoya.service;

import com.cherimoya.cherimoya.dao.UserRepository;
import com.cherimoya.cherimoya.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public void save(User user){
        this.userRepository.save(user);
    }

}

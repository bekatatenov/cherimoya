package com.herimoya.cherimoya.service;

import com.herimoya.cherimoya.dao.UserRepository;
import com.herimoya.cherimoya.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public void save(User user){
        this.userRepository.save(user);
    }

}

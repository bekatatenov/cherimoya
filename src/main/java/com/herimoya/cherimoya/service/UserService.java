package com.herimoya.cherimoya.service;

import com.Project.Post2.dao.UserRepository;
import com.Project.Post2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    public void save(User user){
        this.userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("Не найден пользователь по email: " + email));
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),grantedAuthorities);
    }

    public void update(User user) {
    }
}


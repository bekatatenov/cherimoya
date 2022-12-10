package com.herimoya.cherimoya.service;

import com.herimoya.cherimoya.dao.UserRepository;
import com.herimoya.cherimoya.enums.RoleStatus;
import com.herimoya.cherimoya.enums.UsersStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
 public class UserService implements UserDetailsService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserRepository userRepository;

    public void save(com.herimoya.cherimoya.entity.User user) {
        user.setDate(new Date());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRecipent(false);
        user.setRoles((RoleStatus.USER));
        user.setUsersStatus(UsersStatus.ACTIVE);
        user.setActive(Boolean.TRUE);
        this.userRepository.save(user);
    }

    public void delete(String email) {
        com.herimoya.cherimoya.entity.User user = this.userRepository.findByEmail(email);
        if (user != null) {
            user.setUsersStatus(UsersStatus.DELETED);
            this.userRepository.save(user);
        }
    }

    public void ban(String email) {
        com.herimoya.cherimoya.entity.User user = this.userRepository.findByEmail(email);
        if (user != null) {
            user.setUsersStatus(UsersStatus.BANNED);
            this.userRepository.save(user);
        }
    }

    public void active(String email) {
        com.herimoya.cherimoya.entity.User user = this.userRepository.findByEmail(email);
        if (user != null) {
            user.setUsersStatus(UsersStatus.ACTIVE);
            this.userRepository.save(user);
        }
    }

    public void admin(String email) {
        com.herimoya.cherimoya.entity.User user = this.userRepository.findByEmail(email);
        if (user != null) {
            user.setRoles(RoleStatus.ADMIN);
            this.userRepository.save(user);
        }
    }

    public void moder(String email) {
        com.herimoya.cherimoya.entity.User user = this.userRepository.findByEmail(email);
        if (user != null) {
            user.setRoles(RoleStatus.MODER);
            this.userRepository.save(user);
        }
    }

    public void user(String email) {
        com.herimoya.cherimoya.entity.User user = this.userRepository.findByEmail(email);
        if (user != null) {
            user.setRoles(RoleStatus.USER);
            this.userRepository.save(user);
        }
    }

    public void recipient(String email) {
        com.herimoya.cherimoya.entity.User user = this.userRepository.findByEmail(email);
        if (user != null) {
            user.setRoles(RoleStatus.RECIPIENT);
            this.userRepository.save(user);
        }
    }


    public com.herimoya.cherimoya.entity.User GetAllUserName(String name) {
        return (com.herimoya.cherimoya.entity.User) userRepository.findAllUserName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.herimoya.cherimoya.entity.User> optionalUser = Optional.ofNullable(userRepository.findFirstByEmail(username));
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }
        com.herimoya.cherimoya.entity.User user = optionalUser.get();
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(String.valueOf(user.getRoles()));
        grantedAuthorities.add(simpleGrantedAuthority);
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), grantedAuthorities);
    }

    public com.herimoya.cherimoya.entity.User FindByLogin(String email) {
       return this.userRepository.findByEmail(email);
    }

    public void update(com.herimoya.cherimoya.entity.User user) {
        this.userRepository.save(user);
    }
/*
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }*/
}




package com.cherimoya.cherimoya.service;

import com.cherimoya.cherimoya.entity.User;
import com.cherimoya.cherimoya.enums.RoleStatus;
import com.cherimoya.cherimoya.dao.UserRepository;
import com.cherimoya.cherimoya.enums.UsersStatus;
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
    public void save(User user){
        user.setDate(new Date());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRecipent(false);
        user.setRoles(String.valueOf(RoleStatus.USER));
        user.setUsersStatus(UsersStatus.ACTIVE);
        user.setActive(Boolean.TRUE);
            this.userRepository.save(user);
    }

    public User getEmail(String email){
        return this.userRepository.findByEmail(email);
    }
    public User getId(Long id){
        return this.userRepository.findById(id).get();
    }

    public void delete(String email) {
        User user = this.userRepository.findByEmail(email);
        if (user != null) {
            user.setUsersStatus(UsersStatus.DELETED);
            this.userRepository.save(user);
        }
    }
    public void ban(String email) {
        User user = this.userRepository.findByEmail(email);
        if (user != null) {
            user.setUsersStatus(UsersStatus.BANNED);
            this.userRepository.save(user);
        }
    }

    public void active(String email) {
        User user = this.userRepository.findByEmail(email);
        if (user != null) {
            user.setUsersStatus(UsersStatus.ACTIVE);
            this.userRepository.save(user);
        }
    }

    public void admin(String email) {
        User user = this.userRepository.findByEmail(email);
        if (user != null) {
            user.setRoles(String.valueOf(RoleStatus.ADMIN));
            this.userRepository.save(user);
        }
    }

    public void moder(String email) {
        User user = this.userRepository.findByEmail(email);
        if (user != null) {
            user.setRoles(String.valueOf(RoleStatus.MODER));
            this.userRepository.save(user);
        }
    }

    public void user(String email) {
        User user = this.userRepository.findByEmail(email);
        if (user != null) {
            user.setRoles(String.valueOf(RoleStatus.USER));
            this.userRepository.save(user);
        }
    }

    public void recipient(String email) {
        User user = this.userRepository.findByEmail(email);
        if (user != null) {
            user.setRoles(String.valueOf(RoleStatus.RECIPIENT));
            this.userRepository.save(user);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findFirstByEmail(username));
        if(!optionalUser.isPresent()){
            throw new UsernameNotFoundException("User not found");
        }
        User user = optionalUser.get();
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.getRoles());
        grantedAuthorities.add(simpleGrantedAuthority);

        return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),grantedAuthorities);
    }
}

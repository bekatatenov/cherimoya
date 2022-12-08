package com.cherimoya.cherimoya.service;

import com.cherimoya.cherimoya.dao.UserRepository;
import com.cherimoya.cherimoya.entity.User;
import com.cherimoya.cherimoya.enums.UsersStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    public void save(User user){
        if (userRepository.findByEmail(user.getEmail())==null||userRepository.findByName(user.getName())==null) this.userRepository.save(user);
    }
    public void getEmail(String email){
        User user = this.userRepository.findByEmail(email);
    }

    public User getId(Long id){
        return this.userRepository.findById(id).get();
    }

    public void update(String email) {
        User user = this.userRepository.findByEmail(email);
        if (user != null) {
            user.setUsersStatus(UsersStatus.DELETED);
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
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(String.valueOf(user.getRole()));
        grantedAuthorities.add(simpleGrantedAuthority);

        return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),grantedAuthorities);
    }
}

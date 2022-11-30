package com.herimoya.cherimoya.service;

import com.herimoya.cherimoya.dao.UserRepository;
import com.herimoya.cherimoya.entity.User;
import com.herimoya.cherimoya.enums.RoleStatus;
import com.herimoya.cherimoya.enums.UsersStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
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

            this.userRepository.save(user);

    }
    public void getEmail(String email){
        User user = this.userRepository.findByEmail(email);
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
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.getRoles());
        grantedAuthorities.add(simpleGrantedAuthority);

        return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),grantedAuthorities);
    }
}

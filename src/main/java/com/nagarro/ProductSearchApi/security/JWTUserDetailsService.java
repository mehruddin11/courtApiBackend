package com.nagarro.ProductSearchApi.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nagarro.ProductSearchApi.model.User;
import com.nagarro.ProductSearchApi.repository.UserRepository;

@Service
public class JWTUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	System.out.println("****************");
        User user = userRepository.findByUsername(username);
        System.out.println("user name " + username);
        
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(),   getAuthorities(user.getRoles())
        );
    }


    public Long getUserId(String username) {
        User user = userRepository.findByUsername(username);
        System.out.println("user"+ user);
        return user != null ? user.getId() : null;
    }
    
    private Collection<? extends GrantedAuthority> getAuthorities(String roles) {
        return Arrays.stream(roles.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}

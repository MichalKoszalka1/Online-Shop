package com.Shop.Shop.Service;

import com.Shop.Shop.model.User;
import com.Shop.Shop.Service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{
    @Autowired
    private UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email);
        if (user ==null){
            throw new UsernameNotFoundException("User Not Found" + email);
        }
        return new com.Shop.Shop.UserDetails(user);
    }
}

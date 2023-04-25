package com.Shop.Shop.Service;

import com.Shop.Shop.Service.repository.UserRepository;
import com.Shop.Shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@SuppressWarnings( "ALL" )
@Service
public class UserService   {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }
}
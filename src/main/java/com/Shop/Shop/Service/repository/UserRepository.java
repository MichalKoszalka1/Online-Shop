package com.Shop.Shop.Service.repository;

import com.Shop.Shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("Select u From User u Where u.email=?1")
    User findByEmail(String email);


}

package com.revature.hikingbuddy.repositories;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.revature.hikingbuddy.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

    public Optional<User> findByUsername(String username);
   

   
    
}

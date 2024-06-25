package com.nebulamart.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nebulamart.backend.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    public User findByEmail(String email);
    
}

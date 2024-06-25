package com.nebulamart.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nebulamart.backend.entities.Address;

public interface AddressRepository extends JpaRepository<Address,Long>{
    
}

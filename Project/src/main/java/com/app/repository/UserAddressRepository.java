package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.stereotype.Repository;

import com.app.entities.UserAddress;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress,Long> {
}

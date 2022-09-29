package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.UserDetailsData;

@Repository
public interface AdminRepository extends JpaRepository<UserDetailsData, Long>{

	UserDetailsData findByEmail(String email);

}

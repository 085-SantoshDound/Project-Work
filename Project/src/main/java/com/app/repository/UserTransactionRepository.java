package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.UserTransactionDetails;

@Repository
public interface UserTransactionRepository extends JpaRepository<UserTransactionDetails, Long>{

}

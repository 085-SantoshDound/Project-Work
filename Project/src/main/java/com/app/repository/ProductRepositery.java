package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.entities.Product;

@Repository
public interface ProductRepositery extends JpaRepository<Product, Long>{

}

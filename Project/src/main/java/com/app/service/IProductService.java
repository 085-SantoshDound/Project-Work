package com.app.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ProductDTO;
import com.app.entities.Product;

public interface IProductService {

	Product getProduct(Long id);

	Product addProduct( ProductDTO proDetails);

	List<Product> getAllProducts();
	List <Product> getAllProductsByAscending();

	Product updateProduct(ProductDTO productDetails, Long id);
	
}

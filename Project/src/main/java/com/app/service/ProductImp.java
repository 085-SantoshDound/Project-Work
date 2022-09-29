package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dto.ProductDTO;
import com.app.entities.Product;
import com.app.repository.ProductRepositery;


@Service
@Transactional
public class ProductImp implements IProductService {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ProductRepositery productRepo;
	
	@Override
	public Product getProduct(Long id) {
		System.out.println("in service method for get product");
		return productRepo.findById(id).get();
	}

	@Override
	public Product addProduct(ProductDTO proDetails) {
		// TODO Auto-generated method stub
		System.out.println("in service method of add product : "+proDetails);
		Product product=mapper.map(proDetails, Product.class);
		Product pro=productRepo.save(product);
		return pro;
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> productList=productRepo.findAll();
	return productList;
	}
	@Override
	public List<Product> getAllProductsByAscending() {
		List<Product> productList=productRepo.findAll(Sort.by(Direction.ASC,"price"));
	return productList;
	}

	@Override
	public Product updateProduct(ProductDTO productDetails, Long id) {
		System.out.println("in service of product update");
		System.out.println("In Service of product update product id: "+id +"product details: "+productDetails );
		Product pro=productRepo.getById(id);
		if(pro==null)
			throw new ResourceNotFoundException("Id mismatch for update details");
		BeanUtils.copyProperties(productDetails, pro);
		Product update=productRepo.save(pro);
		return update;
	}

}

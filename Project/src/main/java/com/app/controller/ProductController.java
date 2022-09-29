package com.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Range;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ErrorResponse;
import com.app.dto.ProductDTO;
import com.app.entities.Product;
import com.app.service.IImageService;
import com.app.service.IProductService;



@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	private IImageService imageService;
	
	@Autowired
	private IProductService service;
	
	public ProductController() {
		System.out.println("In product controller");
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getProduct(@Valid @PathVariable Long id)
	{
		System.out.println("in prodcut get meethod");
		System.out.println("in product controller : "+id);
		return new ResponseEntity<>(service.getProduct(id),HttpStatus.OK);
	}
	
	@GetMapping("/check")
	public String checkController()
	{
		return "working in check from product controller";
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addProduct(@RequestBody @Valid ProductDTO proDetails , BindingResult br )
	{
		System.out.println("In add prodict controller method : "+proDetails);
		if(br.hasFieldErrors())
			return new ResponseEntity<>(new ErrorResponse("Invalid input"),HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(service.addProduct(proDetails),HttpStatus.ACCEPTED);
		
	}
	//@PreAuthorize("hasRole('admin')")
	//@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/get/all")
	public ResponseEntity<?> getAllProduct()
	{
		System.out.println("in get all product controller");
		List<Product> productList=service.getAllProducts();
		System.out.println("Product list :" +productList);
		if(productList.isEmpty())
			return new ResponseEntity<>(new ErrorResponse("No product avilable"),HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(productList,HttpStatus.FOUND);
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get/all/byprice")
	public ResponseEntity<?> getAllProductByPrice()
	{
		System.out.println("in get all product controller");
		List<Product> productList=service.getAllProductsByAscending();
		System.out.println("Product list :" +productList);
		if(productList.isEmpty())
			return new ResponseEntity<>(new ErrorResponse("No product avilable"),HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(productList,HttpStatus.FOUND);
	}	
	
	@PostMapping("/update/{id}")
	public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductDTO productDetails,@PathVariable Long id,BindingResult br)
	{
		System.out.println("in update product");
		System.out.println("in update product details :"+productDetails + "User Id : "+id);
		
		if(br.hasFieldErrors())
			return new ResponseEntity<>(new ErrorResponse("Inavlid input for updatye product"),HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(service.updateProduct(productDetails,id),HttpStatus.ACCEPTED);
		
	}
	@PostMapping("/{id}/image")
	public ResponseEntity<?> uploadImageFile(@PathVariable Long productID, @RequestParam MultipartFile imageFile)throws Exception
	{
		System.out.println("in upload image id: "+productID);
		System.out.println("upload image fiile name : "+imageFile.getOriginalFilename()+
				" Content type : "+imageFile.getContentType()+" image size : "+
				imageFile.getSize());
		
		ProductDTO productDTO=imageService.uploadImage(productID,imageFile);
		return ResponseEntity.ok(productDTO);
	}
	@GetMapping(value = "/{id}/image",produces = {MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE,
			MediaType.IMAGE_PNG_VALUE })
	public ResponseEntity<?>  downloadImage(@PathVariable Long id) throws IOException
	{
		System.out.println("in download image controller method :"+ id);
		//invoke service layer method , to get image data from the server side folder
		byte[] imageContents=imageService.restoreImage(id);
		return ResponseEntity.ok(imageContents);
				
	}
	
}


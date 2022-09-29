package com.app.service;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ProductDTO;

public interface IImageService {


	//byte[] restoreImage(Long id);

	byte[] restoreImage(Long id);

	ProductDTO uploadImage(Long productID, MultipartFile imageFile);

}

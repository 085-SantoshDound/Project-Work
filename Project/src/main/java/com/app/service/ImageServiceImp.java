package com.app.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dto.ProductDTO;
import com.app.entities.Product;
import com.app.repository.ProductRepositery;


@Service
@Transactional
public class ImageServiceImp implements IImageService {

	@Value("${file.upload.location}")
	private String baseFolder;
	
	@Autowired
	private ProductRepositery repo;
	
	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	public ProductDTO uploadImage(Long productID, MultipartFile imageFile)  {
		Product product=repo.findById(productID).orElseThrow(() -> new ResourceNotFoundException("invalid product id"));
		System.out.println(productID);
		String completePath=baseFolder+File.pathSeparator+imageFile.getOriginalFilename();
		System.out.println("Complete path : "+ completePath);
		try {
			System.out.println("Copied no of bytes " +
			Files.copy(imageFile.getInputStream(),Paths.get(completePath),StandardCopyOption.REPLACE_EXISTING));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//(imageFile.getInputStream(),Paths.get(getClass(),StandardCopyOption.REPLACE_EXISTING)));
		
		// save complete path to the image in db
		//In case of saving file in db : simply call : imageFile.getBytes() --> byte[] --call setter on emp !
		product.setImgPath(completePath);
		return mapper.map(product,ProductDTO.class);
	}


	@Override
	public byte[] restoreImage(Long id) {
		// TODO Auto-generated method stub
		// get product dtls from emp id
		Product product=repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
		// product => persistent
				// get complete img path from db --> extract image contents n send it to the
				// caller
		String path=product.getImgPath();
		System.out.println(" Path of image in db : "+path);
		//API of java.nio.file.Files class : public byte[] readAllBytes(Path path)
		
		try {
			return Files.readAllBytes(Paths.get(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//in case of BLOB in DB --simply call emp.getImage() --> byte[] --> ret it to the caller!
		return null;
	}

}

package com.app.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.entities.UserDetailsData;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;

@Service
@Transactional
public class generatePDF {

	public generatePDF()
	{
		System.out.println("Generate pdf in service ");
	}
	//public Optional<Document> getGeneratedPDF(UserDetailsData user)
	public void getGeneratedPDF(UserDetailsData user)
	{
		Document document=new Document(PageSize.A4);
		
		//PdfWriter.getInstance(document.);
		//return ;
	}
	
}

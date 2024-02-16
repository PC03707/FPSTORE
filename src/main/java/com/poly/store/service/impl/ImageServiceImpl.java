package com.poly.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.BookDAO;
import com.poly.dao.ImageDAO;
import com.poly.entity.Book;
import com.poly.entity.Image;
import com.poly.service.ImageService;
import com.poly.service.ProductService;

@Service
public class ImageServiceImpl implements ImageService {
	@Autowired
	ImageDAO imagedao;
	
	@Override
	public Image create(Image image) {	
		return imagedao.save(image);
	}
	@Override
	public Image update(Image image) {
		return imagedao.save(image);
	}

	
	@Override
	public void deleteById(Integer id) {
		imagedao.deleteById(id);
		
	}
	
}

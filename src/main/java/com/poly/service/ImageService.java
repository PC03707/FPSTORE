package com.poly.service;


import com.poly.entity.Image;

public interface ImageService {

	Image create(Image image);

	Image update(Image image);


	void deleteById(Integer id);

}

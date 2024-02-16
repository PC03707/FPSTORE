package com.poly.service;

import java.util.List;

import com.poly.entity.Book;

public interface ProductService {

	
	List<Book> findAll();

	Book findById(Integer id);


	Book create(Book book);

	Book update(Book book);

	void delete(Integer id);

	List<Book> findByCategoryId(Integer cid);

	List<Book> getAllProduct();
}

package com.poly.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.BookDAO;
import com.poly.entity.Book;
import com.poly.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	BookDAO bookdao;

	@Override
	public List<Book> findAll() {
		return bookdao.findAll();
	}

	@Override
	public Book findById(Integer id) {
		return bookdao.findById(id).get();
	}

	@Override
	public List<Book> findByCategoryId(Integer cid) {
		return bookdao.findByCategoryId(cid);
	}

	@Override
	public Book create(Book book) {
		return bookdao.save(book);
	}

	@Override
	public Book update(Book book) {
		return bookdao.save(book);
	}

	@Override
	public void delete(Integer id) {
		bookdao.deleteById(id);
	}

	@Override
	public List<Book> getAllProduct() {
		return bookdao.getAllProduct();
	}
}

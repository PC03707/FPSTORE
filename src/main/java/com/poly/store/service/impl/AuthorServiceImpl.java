package com.poly.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.AuthorDAO;
import com.poly.entity.Author;
import com.poly.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	AuthorDAO audao;

	@Override
	public List<Author> findAll() {

		return audao.findAll();
	}

	@Override
	public Author findById(String id) {

		return audao.findById(id).get();
	}

	@Override
	public Author create(Author author) {
		return audao.save(author);
	}

	@Override
	public Author update(Author author) {
		return audao.save(author);
	}

	@Override
	public void delete(String id) {
		audao.deleteById(id);
	}

}

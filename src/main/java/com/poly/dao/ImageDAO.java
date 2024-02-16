package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.Account;
import com.poly.entity.Author;
import com.poly.entity.Book;
import com.poly.entity.Image;
import java.util.List;


public interface ImageDAO extends JpaRepository<Image, Integer>{
		List<Image> findByBook(Book book);

}

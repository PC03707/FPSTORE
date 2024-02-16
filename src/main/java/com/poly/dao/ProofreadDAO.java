package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.Account;
import com.poly.entity.Author;
import com.poly.entity.Book;
import com.poly.entity.Image;
import com.poly.entity.Proofread;

import java.util.List;


public interface ProofreadDAO extends JpaRepository<Proofread, Integer>{
		List<Proofread> findByBook(Book book);

}

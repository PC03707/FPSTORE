package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.Account;
import com.poly.entity.Book;
import com.poly.entity.Evaluate;

public interface EvaluateDAO extends JpaRepository<Evaluate, Long>{
	Evaluate findByAccountAndBook(Account acc, Book book);

	List<Evaluate> findByBook(Book book);
}

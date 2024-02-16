package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Account;
import com.poly.entity.Favorites;
import com.poly.entity.Book;


public interface FavoritesDAO extends JpaRepository<Favorites, Long>{

	List<Favorites> findByAccount(Account account);
	
	void deleteByBook(Book book);
	
	 Favorites findByAccountAndBook(Account account, Book book);
}

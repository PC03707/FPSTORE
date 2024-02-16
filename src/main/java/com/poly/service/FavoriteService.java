package com.poly.service;

import java.util.List;

import com.poly.entity.Account;
import com.poly.entity.Book;
import com.poly.entity.Favorites;

public interface FavoriteService {

	List<Favorites> findByAccount(Account account);

	Favorites create(Favorites favorite);
	
	void deleteById(Long id);

	Favorites findByAccountAndBook(Account acc, Book book);


	
}

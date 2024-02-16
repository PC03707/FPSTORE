package com.poly.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.FavoritesDAO;
import com.poly.entity.Account;
import com.poly.entity.Book;
import com.poly.entity.Favorites;
import com.poly.service.FavoriteService;

@Service
public class FavoriteServiceImpl implements FavoriteService{

	@Autowired
	FavoritesDAO favoritesDAO;
	
	@Override
	public List<Favorites> findByAccount(Account account) {
		return favoritesDAO.findByAccount(account);
	}
	
	@Override
	public Favorites create(Favorites favorite) {
		return favoritesDAO.save(favorite);
	}
	
	@Override
	public Favorites findByAccountAndBook(Account acc , Book book) {
		return favoritesDAO.findByAccountAndBook(acc, book);
	}
	
	@Override
	public void deleteById(Long id) {
		favoritesDAO.deleteById(id);
	}
}

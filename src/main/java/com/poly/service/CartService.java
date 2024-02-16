package com.poly.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Account;
import com.poly.entity.Book;
import com.poly.entity.Cart;
import com.poly.entity.CartDetail;

public interface CartService {
	Cart findByAccount(Account account);
	Cart findById(Integer id);
	List<CartDetail> findByCart(Cart cart);
	CartDetail findByCartAndBook(Cart cart, Book book);
	void deleteByid(Integer id);
	CartDetail save(CartDetail cd);
	Integer findQuantityByCartid(Integer id);
	void deleteById(Integer id);
	CartDetail findBycdid(Integer id);
	
	@Transactional
	@Modifying
	@Query("Delete From Cartdetail o Where o.cart = ?1")
	void deletecdAllByCart(Cart cart);
}

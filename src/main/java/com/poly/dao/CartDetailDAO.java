package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Book;
import com.poly.entity.Cart;
import com.poly.entity.CartDetail;

public interface CartDetailDAO extends JpaRepository<CartDetail, Integer>{
	List<CartDetail> findByCart(Cart cart);
	CartDetail findByCartAndBook(Cart cart, Book book);
	@Query("SELECT SUM(quantity) FROM CartDetail o WHERE o.cart.id = ?1")
	 Integer findQuantityByCartid(Integer id);
}

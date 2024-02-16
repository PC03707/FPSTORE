package com.poly.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.AccountDAO;
import com.poly.dao.CartDAO;
import com.poly.dao.CartDetailDAO;
import com.poly.entity.Account;
import com.poly.entity.Book;
import com.poly.entity.Cart;
import com.poly.entity.CartDetail;
import com.poly.service.CartService;
@Service
public class CartServiceImpl implements CartService{

	@Autowired
	AccountDAO adao;
	
	@Autowired
	CartDAO cdao;
	
	@Autowired
	CartDetailDAO cddao;
	
	
	@Override
	public Cart findByAccount(Account account) {
		return cdao.findByAccount(account);
	}

	@Override
	public List<CartDetail> findByCart(Cart cart) {
		return cddao.findByCart(cart);
	}

	@Override
	public CartDetail findByCartAndBook(Cart cart, Book book) {
		return cddao.findByCartAndBook(cart, book);
	}

	@Override
	public void deleteByid(Integer id) {
		cddao.deleteById(id);
	}


	@Override
	public Cart findById(Integer id) {
		return cdao.findById(id).get();
	}

	@Override
	public CartDetail save(CartDetail cd) {
		return cddao.save(cd);
	}

	@Override
	public Integer findQuantityByCartid(Integer id) {
		return cddao.findQuantityByCartid(id);
	}

	@Override
	public void deleteById(Integer id) {
		cddao.deleteById(id);
	}

	@Override
	public CartDetail findBycdid(Integer id) {
		return cddao.findById(id).get();
	}

	@Override
	public void deletecdAllByCart(Cart cart) {
	}
}

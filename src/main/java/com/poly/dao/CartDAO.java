package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.Account;
import com.poly.entity.Cart;

public interface CartDAO extends JpaRepository<Cart, Integer>{

	Cart findByAccount(Account acc);

}

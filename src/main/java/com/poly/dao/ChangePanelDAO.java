package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.Account;
import com.poly.entity.Book;
import com.poly.entity.ChangePanel;
import com.poly.entity.Image;

public interface ChangePanelDAO extends JpaRepository<ChangePanel, Long> {
	
	List<ChangePanel> findAll();
	
	

}
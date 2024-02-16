package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.dao.BillDAO;
import com.poly.entity.Account;

@Controller
public class CatnhapController {

	@Autowired
	BillDAO billDao;

	@RequestMapping("/doimatkhau")
	public String admin(Model model ,Account account) {
		
		return "";
	}

}

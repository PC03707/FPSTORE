package com.poly.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.UserVoucherDAO;
import com.poly.dao.VoucherDAO;
import com.poly.entity.Account;
import com.poly.entity.UserVoucher;
import com.poly.entity.Voucher;
import com.poly.service.AccountService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/myvoucher")
public class UserVoucherRestController {

	@Autowired
	UserVoucherDAO usvcdao;

	@Autowired
	AccountService accoutservice;

	@Autowired
	VoucherDAO vcdao;

	@GetMapping("{id}")
	public List<UserVoucher> getVoucherUser(@PathVariable("id") String username) {
		Account account = accoutservice.findById(username);
		return usvcdao.findByAccount(account);
	}

	@DeleteMapping("{id}")
	public void deleteid(@PathVariable("id") Integer id) {
		usvcdao.deleteById(id);
	}

	@PostMapping()
	public UserVoucher addVouhcer(@RequestBody UserVoucher usvc) {
		return usvcdao.save(usvc);
	}

	@GetMapping()
	public List<Voucher> getAllVoucher() {
		return vcdao.findByDate();
	}
}

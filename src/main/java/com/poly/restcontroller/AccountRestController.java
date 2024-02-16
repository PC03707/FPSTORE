package com.poly.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.entity.Account;
import com.poly.service.AccountService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {
	
	@Autowired
	AccountService accountService;
	
	@GetMapping()
	public List<Account> findAll(){
		return accountService.findAll();
	}
	
	@PostMapping()
	public Account save(@RequestBody Account account) {
		return accountService.create(account);
	}

	@PutMapping("{username}")
	public Account put(@PathVariable("username") String username, @RequestBody Account account) {
		return accountService.update(account);
	}
	
	@PutMapping("a/{username}")
	public Account put1(@PathVariable("username") String username, @RequestBody String matkhau) {
		Account ac=accountService.findByUsername(username);
		
		ac.setPassword(matkhau);
		return accountService.update(ac);
	}

	@DeleteMapping("{username}")
	public void delete(@PathVariable("username") String username) {
		accountService.deleteById(username);
	}
	
	@GetMapping("/{username}")
	public Account findByUsername(@PathVariable String username) {
	    return accountService.findByUsername(username);
	}
	
	

}

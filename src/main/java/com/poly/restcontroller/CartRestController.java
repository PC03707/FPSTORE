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
import com.poly.entity.Cart;
import com.poly.entity.CartDetail;
import com.poly.service.AccountService;
import com.poly.service.CartService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/cart")
public class CartRestController {
	
	@Autowired
	CartService cservice;
	
	@Autowired
	AccountService accountService;
	
	@GetMapping("{id}")
	public Cart getcartid(@PathVariable("id") String id) {
		Account account = accountService.findById(id);
		return cservice.findByAccount(account);
	}
	
	@GetMapping("/cartdetails/{id}")
	public List<CartDetail> getCartdetails(@PathVariable("id") String us) {
		Account account = accountService.findById(us);
		Cart cart = cservice.findByAccount(account);
		return cservice.findByCart(cart);
	}
	
	@PostMapping("/addcart")
	public CartDetail addcart(@RequestBody CartDetail cd) {
		CartDetail cdetail = cservice.findByCartAndBook(cd.getCart(), cd.getBook());
		if (cdetail == null) {
			return cservice.save(cd);
		} else {
			cdetail.setQuantity(cdetail.getQuantity()+1);
			return cservice.save(cdetail);
		}
	}
	
	@PutMapping("/updateqty")
	public CartDetail updateqty(@RequestBody CartDetail cd) {
		return cservice.save(cd);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteId(@PathVariable("id") Integer id) {
		cservice.deleteById(id);
	}
	
//	@DeleteMapping("/deleteall/{id}")
//	public void deletealll(@PathVariable("id") Integer id) {
//		Cart cart = cservice.findById(id);
//		cservice.deletecdAllByCart(cart);
//	}
}

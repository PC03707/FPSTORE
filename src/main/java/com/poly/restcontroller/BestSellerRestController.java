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

import com.poly.dao.DiscountDAO;
import com.poly.entity.Book;
import com.poly.entity.Discount;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/bestseller")
public class BestSellerRestController {
	@Autowired
	DiscountDAO discountdao;
	
	@GetMapping()
	public List<Discount> findAll(){
		return discountdao.findAll();
	}
	@PostMapping()
	public Discount create(@RequestBody Discount discount) {
		return discountdao.save(discount);
	}

	@PutMapping("{id}")
	public Discount update(@PathVariable("id") Integer id, @RequestBody Discount discount) {
		return discountdao.save(discount);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		discountdao.deleteById(id);
	}
	

}

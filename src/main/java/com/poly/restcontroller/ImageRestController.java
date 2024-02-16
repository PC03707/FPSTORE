package com.poly.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.AccountDAO;
import com.poly.dao.BillDAO;
import com.poly.dao.BookDAO;
import com.poly.dao.ImageDAO;
import com.poly.entity.Bill;
import com.poly.entity.Book;
import com.poly.entity.Image;
import com.poly.service.ImageService;
import com.poly.service.ProductService;

import java.math.BigDecimal;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/images")
public class ImageRestController {

	@Autowired
	ProductService productService;

	@Autowired
	BookDAO productDao;

	@Autowired
	AccountDAO accountDao;

	@Autowired
	BillDAO billDao;

	@Autowired
	ImageService imgaeservice;
	
	@Autowired
	ImageDAO imgaeDao;

	@GetMapping("{id}")
	public List<Image> getImage(@PathVariable("id") Integer id) {
		Book bok = productService.findById(id);

		return imgaeDao.findByBook(bok);
	}

	@PostMapping()
	public Image create(@RequestBody Image image) {
		return imgaeservice.create(image);
	}

	@PutMapping("{id}")
	public Image update(@PathVariable("id") Integer id, @RequestBody Image image) {
		return imgaeservice.update(image);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		imgaeservice.deleteById(id);
	}
	
	

}

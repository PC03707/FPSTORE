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



import com.poly.dao.BookDAO;

import com.poly.dao.ProofreadDAO;

import com.poly.entity.Book;

import com.poly.entity.Proofread;

import com.poly.service.ProductService;
import com.poly.service.ProofreadsService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/proofread")
public class ProofreadsRestController {

	@Autowired
	ProductService productService;

	@Autowired
	BookDAO productDao;
	
	@Autowired
	ProofreadsService proosirvice;
	
	@Autowired
	ProofreadDAO prooDao;

	@GetMapping("{id}")
	public List<Proofread> getImage(@PathVariable("id") Integer id) {
		Book bok = productService.findById(id);

		return prooDao.findByBook(bok);
	}

	@PostMapping()
	public Proofread create(@RequestBody Proofread proofread) {
		return proosirvice.create(proofread);
	}

	@PutMapping("{id}")
	public Proofread update(@PathVariable("id") Integer id, @RequestBody Proofread proofread) {
		return proosirvice.update(proofread);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		proosirvice.deleteById(id);
	}

}

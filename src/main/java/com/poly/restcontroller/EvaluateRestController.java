package com.poly.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.dao.BookDAO;
import com.poly.dao.EvaluateDAO;
import com.poly.entity.Account;
import com.poly.entity.Bill;
import com.poly.entity.Book;
import com.poly.entity.Evaluate;
import com.poly.service.SessionService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/evaluate")
public class EvaluateRestController {

	@Autowired
	EvaluateDAO edao;
	@Autowired
	SessionService session;
	@Autowired
	BookDAO bdao;
	
	@GetMapping("/{id}")
	public Evaluate getEvaluate(@PathVariable("id") Integer id) {
		Account acc = session.get("user");
		Book book = bdao.findById(id).get();
		
		Evaluate ev = edao.findByAccountAndBook(acc, book);
		return ev;
	}
	
	@GetMapping("/book/{id}")
	public List<Evaluate> getEvaluateBook(@PathVariable("id") Integer id) {
		Book book = bdao.findById(id).get();
		return edao.findByBook(book);
	}
	
	@PostMapping
	public Evaluate postEvaluate(@RequestBody Evaluate evaluate) {
		edao.save(evaluate);
		return evaluate;
	}
}

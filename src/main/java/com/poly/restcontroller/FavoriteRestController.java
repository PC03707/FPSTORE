package com.poly.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.AccountDAO;
import com.poly.dao.BookDAO;
import com.poly.entity.Account;
import com.poly.entity.Book;
import com.poly.entity.Favorites;
import com.poly.service.FavoriteService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/favorites")
public class FavoriteRestController {

	@Autowired
	AccountDAO accountDAO;
	
	@Autowired
	FavoriteService favoriteService;
	
	@Autowired
	BookDAO bookDAO;
	
	@Autowired
	HttpServletRequest request;
	
	@RequestMapping("/favorite/error")
	public String error() {
		return "redirect:/";
	}
	
	@GetMapping("{username}")
	public List<Favorites> findByAccount(@PathVariable("username") String un){
		Account acc = accountDAO.findById(un).get();
		return favoriteService.findByAccount(acc);
	}
	
	@PostMapping()
	public Favorites create (@RequestBody Favorites favorite) {
		return favoriteService.create(favorite);
	}
	
	@DeleteMapping("{bookid}")
	public void deleteByBookId(@PathVariable("bookid") Integer bookid) {
		String un = request.getRemoteUser();
		Account acc = accountDAO.findById(un).get();
		Book book = bookDAO.findById(bookid).get();
		
		Favorites f = favoriteService.findByAccountAndBook(acc, book);
		favoriteService.deleteById(f.getId());
	}
	
	@GetMapping("/check/{bookid}")
	public Favorites check(@PathVariable("bookid") Integer bookid) {
		String un = request.getRemoteUser();
		Account acc = accountDAO.findById(un).get();
		Book book = bookDAO.findById(bookid).get();
		
		return favoriteService.findByAccountAndBook(acc, book);
		
	}
}

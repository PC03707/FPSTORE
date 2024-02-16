package com.poly.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.dao.AccountDAO;
import com.poly.dao.AuthorityDAO;
import com.poly.dao.CartDAO;
import com.poly.entity.Account;
import com.poly.entity.Authority;
import com.poly.entity.Cart;
import com.poly.service.SessionService;
import com.poly.service.UserService;


@Controller
public class SecurityController {

	@Autowired
	SessionService session;
	
	@Autowired
	AccountDAO accountdao;
	
	@Autowired
	AuthorityDAO atdao;
	
	@Autowired
	CartDAO cartdao;
	
	@Autowired
	UserService userservice;
	
	@RequestMapping("/security/login/form")
	public String loginForm(Model model) {			
		model.addAttribute("message", "Vui lòng đăng nhập!");
		return "security/login";
	}
	
	@RequestMapping("/security/login/success")
	public String success(Model model) {
		
	
		String username = session.get("username");
		Account acc = accountdao.findById(username).get();
		Cart checkcart = cartdao.findByAccount(acc);
		List<Authority> au = atdao.findByAccount(acc);
		for (Authority authority : au) {
			if(authority.getRole().getId().equals("ADMIN")) {
				return "redirect:/admin";
			}
		}
		if (checkcart == null) {
			Cart cart = new Cart();
			cart.setAccount(acc);
			cart.setCreateDate(new Date());
			cartdao.save(cart);
			session.set("cart", cart);
		}
		session.set("cart", checkcart);
		model.addAttribute("message", "Đăng nhập thành công!");
		return "redirect:/";
	}
	
	@RequestMapping("/security/login/error")
	public String error(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập!");
		return "security/login";
	}
	
	@RequestMapping("/security/unauthoried")
	public String denied(Model model) {
		model.addAttribute("message", "Bạn không có quyền truy cập!");
		return "security/login";
	}
	
	@RequestMapping("/security/logoff/success")
	public String logoff(Model model) {
		model.addAttribute("message", "Đăng xuất thành công!");
		return "security/login";
	}
	
	@RequestMapping("/oauth2/login/success")
    public String sucess(OAuth2AuthenticationToken oauth2) {
		userservice.loginFromOAuth2(oauth2);
        return "forward:/security/login/success";
    }
}

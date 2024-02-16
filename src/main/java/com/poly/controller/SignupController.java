package com.poly.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.hibernate.boot.model.relational.AbstractAuxiliaryDatabaseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.AccountDAO;
import com.poly.dao.AuthorityDAO;
import com.poly.dao.CartDAO;
import com.poly.dao.RoleDAO;
import com.poly.entity.Account;
import com.poly.entity.Authority;
import com.poly.service.SessionService;
import com.poly.store.service.impl.MailerServiceImpl;

@Controller
public class SignupController {

	@Autowired
	MailerServiceImpl mailer;
	@Autowired
	AccountDAO dao; // làm việc với bảng Account
	@Autowired
	SessionService session;
	@Autowired
	CartDAO cartdao;
	@Autowired
	RoleDAO rdao;
	@Autowired
	AuthorityDAO audao;
	double mxn = Math.round(Math.random() * 999999) + 111122;

	@RequestMapping("signup")
	public String index(Model model) {
		model.addAttribute("account", new Account());
		return "User/signup";
	}

	@RequestMapping("create")
	public String create(Account account, @RequestParam("email") String email, Model model,
			@RequestParam("password") String password, @RequestParam("confirm") String confirm,
			@RequestParam("username") String username) throws IllegalStateException, IOException {

		List<Account> all=dao.findAll();
		for (Account account2 : all) {
			if(account2.getUsername().equals(username)) {
				model.addAttribute("account", account);
				model.addAttribute("message", "Tên đăng nhập đã được sử dụng ");
				return "User/signup";			
			}
			if(account2.getEmail().equals(email)) {
				model.addAttribute("account", account);
				model.addAttribute("message", "Email đã được sử dụng ");
				return "User/signup";		
			}
			
		}
		

		Integer ma = (int) mxn;

		String thongBao = "Thông báo: Mã xác nhận mã \r\n" + "\r\n" + "Kính gửi quý khách hàng,\r\n" + "\r\n <br>	"
				+ "Chúng tôi gửi đến quý khách mã xác nhận mới để đảm bảo tính bảo mật cho tài khoản của quý khách. Mã xác nhận này được sử dụng để xác thực và bảo vệ quyền riêng tư của quý khách trong quá trình sử dụng dịch vụ của chúng tôi.\r\n <br>	"
				+ "\r\n" + "Dưới đây là mã xác nhận code của quý khách:\r\n" + "<br>" + ma + "</br>" + "\r\n"
				+ "\r\n <br>	"
				+ "Vui lòng sử dụng mã xác nhận này để tiếp tục các hoạt động và giao dịch trên tài khoản của quý khách. Chúng tôi khuyến nghị quý khách không tiết lộ mã xác nhận  này cho bất kỳ ai khác và không gửi mã này qua email hay tin nhắn điện thoại.\r\n <br>	"
				+ "\r\n"
				+ "Nếu quý khách không yêu cầu hoặc không nhớ có bất kỳ hoạt động liên quan đến mã xác nhận này, vui lòng liên hệ với bộ phận hỗ trợ khách hàng của chúng tôi ngay để được hỗ trợ và đảm bảo an toàn cho tài khoản của quý khách.\r\n <br>	"
				+ "\r\n" + "Xin chân thành cảm ơn quý khách hàng đã sử dụng dịch vụ của chúng tôi.\r\n" + "\r\n <br>"
				+ "Trân trọng,\r\n" + "FBook";


			if (confirm.equals(password)) {
				mailer.send(email, "YÊU CẦU MÃ XÁC NHẬN TỪ NGƯỜI DÙNG!", thongBao);

				session.set("mxn", ma);
				session.set("account", account);

				return "User/confirm";
			} else {
				model.addAttribute("account", account);
				model.addAttribute("message", "Xác nhận mật khẩu không chính xác");
				return "User/signup";
			}
//		}

	}

	@RequestMapping("confirm")
	public String Confirm(Model model, @RequestParam("confirm") Integer confirm) {
		Integer ma = session.get("mxn");
		if (confirm == null) {
			model.addAttribute("error", "Mã Xác Nhận Không Chính Xác!");
			return "User/confirm";
		} else {
			if (!confirm.equals(ma)) {
				model.addAttribute("error", "Mã Xác Nhận Không Chính Xác!");
				return "User/confirm";
			} else {
				Account item = session.get("account");
				//item.setCreatedate(new Date());
				model.addAttribute("item", item);
				dao.save(item);
				Authority au = new Authority();
				au.setAccount(item);
				au.setRole(rdao.findById("USER").get());
				audao.save(au);
			}
		}
		return "/security/login";
	}

	@RequestMapping("signin")
	public String signin() {
		return "redirect:/security/login/form";
	}
}

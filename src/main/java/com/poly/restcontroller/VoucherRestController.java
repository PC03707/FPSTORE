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

import com.poly.dao.UserVoucherDAO;
import com.poly.entity.Voucher;
import com.poly.service.VoucherService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/vouchers")
public class VoucherRestController {
	@Autowired
	VoucherService voucherService;
	
	
	@GetMapping
	public List<Voucher> getAll() {
		return voucherService.findAll();
	}

	@GetMapping("{voucherid}")
	public Voucher getOne(@PathVariable("voucherid") String voucherid) {
		return voucherService.findById(voucherid);
	}

	@PostMapping
	public Voucher create(@RequestBody Voucher voucher) {
		return voucherService.create(voucher);
	}

	@PutMapping("{voucherid}")
	public Voucher update(@PathVariable("voucherid") String voucherid, @RequestBody Voucher voucher) {
		return voucherService.update(voucher);
	}

	@DeleteMapping("{voucherid}")
	public void delete(@PathVariable("voucherid") String voucherid) {
		voucherService.delete(voucherid);
	}
}

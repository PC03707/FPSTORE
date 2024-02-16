package com.poly.restcontroller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.dao.BillDAO;
import com.poly.entity.Account;
import com.poly.entity.Bill;
import com.poly.entity.BillDetail;
import com.poly.entity.OrderGHN;
import com.poly.service.AccountService;
import com.poly.service.BillService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/order")
public class OrderRestController {
	@Autowired
	BillService billservice;

	@Autowired
	BillDAO billDao;

	@Autowired
	AccountService accountService;

	@GetMapping()
	public List<Bill> getAll() {
		return billDao.getProductsSortedByDate();

	}

	@GetMapping("dhdg")
	public List<Bill> getDHDG() {
		List<Bill> listoder2 = billDao.stautus(2);
		return listoder2;

	}

	@GetMapping("dg")
	public List<Bill> getDG() {
		List<Bill> listoder3 = billDao.stautus(3);
		return listoder3;

	}

	@GetMapping("huy")
	public List<Bill> gethuy() {
		List<Bill> listoder4 = billDao.stautus(4);
		return listoder4;

	}

	@GetMapping("trangthai/{id}")
	public List<Bill> getTrangThaiDH(@PathVariable("id") Integer id) {
		List<Bill> listoder = billDao.stautus(id);
		return listoder;

	}

	@PostMapping()
	public Bill create(@RequestBody JsonNode orderData) {
		return billservice.create(orderData);
	}
	
	@PostMapping("payment")
	public Bill createPayment(@RequestBody JsonNode orderData) {
		return billservice.createPayment(orderData);
	}
	
	@PostMapping("info-order")
	public OrderGHN infoOrder(@RequestBody JsonNode Data) {
		return billservice.infoOrder(Data);
	}

	@GetMapping("/index")
	public List<Long> index(Model model) {
		long choXacNhan = billDao.countOrdersWithStatus(1);
		long dangGiaoHang = billDao.countOrdersWithStatus(2);
		long huyDonHang = billDao.countOrdersWithStatus(4);
		long daNhanHang = billDao.countOrdersWithStatus(3);

		List<Long> list = new ArrayList<>();
		list.add(choXacNhan);
		list.add(dangGiaoHang);
		list.add(daNhanHang);
		list.add(huyDonHang);
		return list;
	}

//	@PutMapping("/{orderId}/status")
//	public ResponseEntity<String> changeOrderStatus(@PathVariable Long orderId, @RequestParam Integer newStatusId) {
//		Bill bill = billservice.getOne(orderId);
//		if (bill == null) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
//		}
//
//		Status newStatus = statusservice.getOne(newStatusId);
//		if (newStatus == null) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid new status");
//		}
//
////		bill.setStatus(newStatus);
//		billservice.save(bill);
//
//		return ResponseEntity.ok("Order status updated successfully");
//	}

	@GetMapping("{id}")
	public List<Bill> getbillid(@PathVariable("id") String id) {
		Account account = accountService.findById(id);
		return billservice.findUsername(id);
	}
	
	@GetMapping("/bill/{id}")
	public List<BillDetail> getBillDetails (@PathVariable("id") Long id) {
		return billservice.findByBill(id);
	}
}

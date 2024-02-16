package com.poly.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.entity.Account;
import com.poly.entity.Bill;
import com.poly.entity.BillDetail;
import com.poly.entity.OrderGHN;

public interface BillService {
	List<Bill> findAll();

	Bill create(JsonNode orderData);

	Bill findById(Long id);

	Bill update(Bill bill);

	Bill getOne(Long orderId);

	void save(Bill bill);

	List<Bill> findByAccount(Account account);

	Bill createPayment(JsonNode orderData);
	
	OrderGHN infoOrder(JsonNode data);

	List<BillDetail> findByBill(Long id);

	List<Bill> findUsername(String id);

}

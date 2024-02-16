package com.poly.service;

import java.util.List;

import com.poly.entity.Voucher;

public interface VoucherService {
	
	List<Voucher> findAll();
	Voucher findById(String voucherid);
	Voucher create(Voucher voucher);
	Voucher update(Voucher voucher);
	void delete(String voucherid);
}

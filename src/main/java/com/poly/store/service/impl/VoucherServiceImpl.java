package com.poly.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.VoucherDAO;
import com.poly.entity.Voucher;
import com.poly.service.VoucherService;

@Service
public class VoucherServiceImpl implements VoucherService {

	@Autowired
	VoucherDAO vdao;

	@Override
	public List<Voucher> findAll() {
		return vdao.findAll();
	}

	@Override
	public Voucher findById(String voucherid) {
		return vdao.findById(voucherid).get();
	}

	@Override
	public Voucher create(Voucher voucher) {
		return vdao.save(voucher);
	}

	@Override
	public Voucher update(Voucher voucher) {
		return vdao.save(voucher);
	}

	@Override
	public void delete(String voucherid) {
		vdao.deleteById(voucherid);
	}

}

package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Voucher;

public interface VoucherDAO extends JpaRepository<Voucher, String>{
	@Query("Select v From Voucher v Where v.startdate <= FORMAT(GETDATE(), 'yyyy-MM-dd') AND v.enddate >= FORMAT(GETDATE(), 'yyyy-MM-dd')")
	List<Voucher> findByDate();
}

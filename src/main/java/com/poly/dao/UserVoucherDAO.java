package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.Account;
import com.poly.entity.UserVoucher;
import com.poly.entity.Voucher;

public interface UserVoucherDAO extends JpaRepository<UserVoucher, Integer>{
	List<UserVoucher> findByAccountAndVoucher(Account account, Voucher voucher);
	List<UserVoucher> findByAccount(Account account);
}

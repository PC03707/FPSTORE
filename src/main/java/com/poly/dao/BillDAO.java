package com.poly.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Account;
import com.poly.entity.Bill;
import com.poly.entity.Report;

import java.math.BigDecimal;

public interface BillDAO extends JpaRepository<Bill, Long> {

	@Query("SELECT o FROM Bill o Where o.account.username = ?1 ORDER BY createdate DESC")
	List<Bill> findUsername (String username);
	
	List<Bill> findByAccount(Account account);

	@Query("SELECT COUNT(o) FROM Bill o")
	Integer countBill();

	@Query("SELECT COUNT(o) FROM Bill o WHERE o.status.id = ?1")
	long countOrdersWithStatus(Integer id);

	@Query("SELECT o FROM Bill o WHERE o.status.id = ?1 OR o.status.id = ?2")
	List<Bill> stautus12(Integer id, Integer id2);

	@Query("SELECT o FROM Bill o WHERE o.status.id = ?1")
	List<Bill> stautus(Integer id);

	@Query("SELECT b FROM Bill b WHERE CAST(b.createDate AS date) = CAST(GETDATE() AS date)")
	List<Bill> findBillsCreatedToday();

	@Query("SELECT SUM(b.billtotal) FROM Bill b WHERE CAST(b.createDate AS date) = CAST(GETDATE() AS date)")
	BigDecimal getTotalAmountOfOrdersPlacedToday();

	@Query("SELECT SUM(o.billtotal) FROM Bill o")
	Double calculateTotalAmountForAllOrders();

	@Query("SELECT c FROM Bill c WHERE c.createDate < GETDATE() ORDER BY c.createDate DESC")
	List<Bill> getProductsSortedByDate();

	@Query("SELECT NEW Report(b.createDate, a.fullname, COUNT(*), SUM(b.billtotal)) " + "FROM Bill b "
			+ "JOIN Account a ON b.account.username = a.username " + "WHERE b.createDate >= ?1 AND b.createDate <= ?2 "
			+ "GROUP BY b.createDate, a.fullname " + "ORDER BY SUM(b.billtotal) DESC")
	List<Report> getReportData(Date startDate, Date endDate);
	
	Bill findByOrdercode(String ordercode);

}

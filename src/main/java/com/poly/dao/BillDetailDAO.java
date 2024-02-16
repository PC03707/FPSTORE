package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.entity.Bill;
import com.poly.entity.BillDetail;
import com.poly.entity.CateStatistic;
import com.poly.entity.ProductStatistics;
import com.poly.entity.ReportCate;


public interface BillDetailDAO extends JpaRepository<BillDetail, Long>{

	@Query("SELECT od FROM BillDetail od JOIN FETCH od.book p WHERE od.bill.id = :orderId")
	List<BillDetail> findByOrderIdWithProducts(Long orderId);
	
	
	List<BillDetail> findByBill (Bill bill);
	
	
	@Query("SELECT new ReportCate(o.book.category.name, COUNT(o), SUM(o.quantity ), SUM(o.price * o.quantity)) FROM BillDetail o GROUP BY o.book.category.name ORDER BY COUNT(o) DESC")
	List<ReportCate> getProductSummary();
	
	@Query("SELECT NEW ProductStatistics(p.name, SUM(od.quantity), SUM(od.price * od.quantity)) " +
			"FROM Book p " +
			"JOIN BillDetail od ON od.book.id = p.id " +
			"GROUP BY p.name " +
			"ORDER BY SUM(od.quantity) DESC")
	List<ProductStatistics> findTopSellingProducts();
	
	
	@Query("SELECT  b.book.category.id AS categoryName, SUM(b.quantity) AS totalSoldQuantity " +
	           "FROM BillDetail b " +
	           "WHERE YEAR(b.billdate) = :year " +
	           "GROUP BY b.book.category.id")
	 List<Object[]> getTotalSoldByCategoryAndMonth(@Param("year") Integer year);

}

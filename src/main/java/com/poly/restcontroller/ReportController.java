package com.poly.restcontroller;



import com.poly.dao.BillDAO;
import com.poly.dao.BillDetailDAO;
import com.poly.entity.CateStatistic;
import com.poly.entity.ProductStatistics;
import com.poly.entity.Report;
import com.poly.entity.ReportCate;
import com.poly.service.ParamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
public class ReportController {
    @Autowired
    ParamService param;
    @Autowired
    BillDAO orderRepository;
    @Autowired
    BillDetailDAO orderDetailsService;
    

    @GetMapping("/reports")
    public List<Report> getReports(
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        System.out.println(startDate);
        System.out.println(endDate);
        return orderRepository.getReportData(startDate, endDate);
    }

    @GetMapping("/reportCate")
    public List<ReportCate> getReportCate() {
        return orderDetailsService.getProductSummary();
    }

    @GetMapping("/reportProduct")
    public List<ProductStatistics> getProductReport() {
        return orderDetailsService.findTopSellingProducts();
    }
    @GetMapping("/sumCategory")
	public List<Object[]> getSumCate(@RequestParam int year) {
		return orderDetailsService.getTotalSoldByCategoryAndMonth(year);
}
}

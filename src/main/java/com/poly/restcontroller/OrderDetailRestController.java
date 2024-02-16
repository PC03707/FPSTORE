package com.poly.restcontroller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.BillDAO;
import com.poly.dao.BillDetailDAO;

import com.poly.entity.BillDetail;




@RestController
@RequestMapping("rest/orderDetails")
public class OrderDetailRestController {
    @Autowired
    BillDetailDAO billdetaildao;
    @Autowired
    BillDAO billdao;
 

    
    

    @GetMapping("/{orderId}")
    public List<BillDetail> getOrderDetailsByOrderId(@PathVariable Long orderId) {           
    	return billdetaildao.findByOrderIdWithProducts(orderId);
    }  

}


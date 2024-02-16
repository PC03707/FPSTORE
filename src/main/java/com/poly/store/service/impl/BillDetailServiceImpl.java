package com.poly.store.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.dao.BillDAO;
import com.poly.dao.BillDetailDAO;
import com.poly.entity.Bill;
import com.poly.entity.BillDetail;
import com.poly.entity.CateStatistic;
import com.poly.service.BillDetailService;
import com.poly.service.BillService;
@Service
public class BillDetailServiceImpl implements BillDetailService  {
	@Autowired 
	BillDetailDAO billdao;

	
	

}

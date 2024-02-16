package com.poly.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.BookDAO;
import com.poly.dao.ImageDAO;
import com.poly.dao.ProofreadDAO;
import com.poly.entity.Book;
import com.poly.entity.Image;
import com.poly.entity.Proofread;
import com.poly.service.ImageService;
import com.poly.service.ProductService;
import com.poly.service.ProofreadsService;

@Service
public class ProofreadServiceImpl implements ProofreadsService {
@Autowired 
ProofreadDAO prooDao;
	@Override
	public Proofread create(Proofread proofread) {
		
		return prooDao.save(proofread);
	}

	@Override
	public Proofread update(Proofread proofread) {
		// TODO Auto-generated method stub
		return prooDao.save(proofread);
	}

	@Override
	public void deleteById(Integer id) {
		prooDao.deleteById(id);
		
	}
	
	
}

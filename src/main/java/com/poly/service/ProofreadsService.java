package com.poly.service;


import com.poly.entity.Proofread;

public interface ProofreadsService {

	Proofread create(Proofread proofread);

	Proofread update(Proofread proofread);

	void deleteById(Integer id);

}

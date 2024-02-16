package com.poly.entity;

import lombok.Data;

@Data
public class OrderGHN {	
	String total;
	String to_phone;
	String to_address;
	String wardcode;
	String note;
	String district;
	Object items;
}

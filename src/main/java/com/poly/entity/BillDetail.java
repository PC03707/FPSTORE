package com.poly.entity;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Billdetails")
public class BillDetail  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Integer quantity;
	Double price;
	@Temporal(TemporalType.DATE)
	@Column(name = "billdate")
	Date billdate = new Date();
	@ManyToOne
	@JoinColumn(name = "Bookid")
	Book book;
	@ManyToOne
	@JoinColumn(name = "Billid")
	Bill bill;
}

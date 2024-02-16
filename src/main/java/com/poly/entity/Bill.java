package com.poly.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Bills")
public class Bill implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Createdate")
	Date createDate = new Date();
	Double ship;
	@JoinColumn(name = "ordercode")
	String ordercode;
	@ManyToOne
	@JoinColumn(name = "Voucherid")
	Voucher voucher;
	@ManyToOne
	@JoinColumn(name = "Userid")
	Account account;
	@JoinColumn(name = "phone")
	String phone;
	@JoinColumn(name = "billtotal")
	Double billtotal;	
	@JoinColumn(name = "status")
	String status;
	@JoinColumn(name = "address")
	String address;
	@JsonIgnore
	@OneToMany(mappedBy = "bill")
	List<BillDetail> billdetails;
}

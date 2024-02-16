package com.poly.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Vouchers")
public class Voucher implements Serializable {

	@Id
	String voucherid;
	String name;
	Double discount;
	String image;
	Date startdate;
	Date enddate;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "voucher")
	List<Bill> bills;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "voucher")
	List<UserVoucher> userVouchers;
}

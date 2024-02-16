package com.poly.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Cartdetails", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"Cartid", "Bookid"})})
public class CartDetail implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@JoinColumn(name = "Price")
	Double price;
	@JoinColumn(name = "Quantity")
	Integer quantity;
	@ManyToOne
	@JoinColumn(name = "Bookid")
	Book book;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "Cartid")
	Cart cart;
}

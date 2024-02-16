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
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Books")
public class Book implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	String name;
	String image;

	Double price;
	String discriptions;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Createdate")
	Date createDate = new Date();

	Boolean status;

	@ManyToOne
	@JoinColumn(name = "Categoryid")
	Category category;
	
	@ManyToOne
	@JoinColumn(name = "Authorsid")
	Author author;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
	@JsonIgnore
	List<Favorites> favorites;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
	@JsonIgnore
	List<BillDetail> billdetails;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
	List<Image> images;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
	List<Proofread> proofread;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
	List<CartDetail> cartdetail;

}

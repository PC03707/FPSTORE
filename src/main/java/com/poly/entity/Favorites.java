package com.poly.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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
@Table(name = "Favorites")
public class Favorites implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Temporal(TemporalType.DATE)
	@Column(name = "Likedate")
	Date likeDate = new Date();
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "Userid")
	Account account;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "Bookid")
	Book book;
}

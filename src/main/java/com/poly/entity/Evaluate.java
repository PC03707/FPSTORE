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
@Table(name = "Evaluates")
public class Evaluate implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Integer star;
	String comment;
	@Temporal(TemporalType.DATE)
	@Column(name = "Commentdate")
	Date commentDate = new Date();
	@ManyToOne
	@JoinColumn(name = "Userid")
	Account account;
	@ManyToOne
	@JoinColumn(name = "Bookid")
	Book book;
}

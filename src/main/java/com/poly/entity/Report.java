package com.poly.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
	@Id
	private Date createDate; // Tên thuộc tính khớp với tên trường trong câu truy vấn
    private String fullName; // Tên thuộc tính khớp với tên trường trong câu truy vấn
    private Long orderCount; // Tên thuộc tính khớp với tên trường trong câu truy vấn
    private Double totalAmount; // Tên thuộc tính khớp với tên trường trong câu truy vấn
}

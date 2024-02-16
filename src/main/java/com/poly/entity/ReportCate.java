package com.poly.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportCate {
    @Id
    private String name;
    private Long numberOfOrders;
    private Long totalQuantity;
    private Double avgPrice;
}

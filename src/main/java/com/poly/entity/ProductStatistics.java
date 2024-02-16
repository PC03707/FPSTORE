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
public class ProductStatistics {
    @Id
    private String name;
    private Long totalQuantitySold;
    private Double totalRevenue;
}

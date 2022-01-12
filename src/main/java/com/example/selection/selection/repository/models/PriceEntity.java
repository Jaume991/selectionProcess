package com.example.selection.selection.repository.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name="prices")
@AllArgsConstructor
@NoArgsConstructor
public class PriceEntity {
    @Id
    int id;
    @Column(name = "brand_id")
    int brandId;
    @Column(name = "start_date")
    LocalDateTime startDate;
    @Column(name = "end_date")
    LocalDateTime endDate;
    @Column(name = "price_list")
    int priceList;
    @Column(name = "product_id")
    int productId;
    int priority;
    BigDecimal price;
    String curr;
}

package com.example.selection.selection.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Price {
    int brandId;
    LocalDateTime startDate;
    LocalDateTime endDate;
    int priceList;
    int productId;
    int priority;
    BigDecimal price;
    String currency;
}

package com.example.selection.selection.models.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PriceResponse {
    int productId;
    int brandId;
    int priceList;
    BigDecimal price;
    LocalDateTime startDate;
    LocalDateTime endDate;
}

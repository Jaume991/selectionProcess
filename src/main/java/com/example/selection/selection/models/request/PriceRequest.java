package com.example.selection.selection.models.request;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PriceRequest {
    LocalDateTime date;
    int productId;
    int brandId;
}

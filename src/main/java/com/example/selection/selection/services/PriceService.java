package com.example.selection.selection.services;

import com.example.selection.selection.exception.ProductNotFoundException;
import com.example.selection.selection.models.request.PriceRequest;
import com.example.selection.selection.models.response.PriceResponse;

public interface PriceService {
    public PriceResponse getPrice(final PriceRequest priceRequest) throws ProductNotFoundException;
}

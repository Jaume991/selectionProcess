package com.example.selection.selection.api;

import com.example.selection.selection.exception.ProductNotFoundException;
import com.example.selection.selection.models.request.PriceRequest;
import com.example.selection.selection.models.response.PriceResponse;
import com.example.selection.selection.services.PriceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceController {
    final PriceService priceService;

    @Autowired
    public PriceController(final PriceService priceService) {
        this.priceService = priceService;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public PriceResponse getPrice(@RequestBody PriceRequest priceRequest) throws ProductNotFoundException {
        return this.priceService.getPrice(priceRequest);
    }
}

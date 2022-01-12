package com.example.selection.selection.services;

import java.util.Comparator;
import java.util.function.Predicate;

import com.example.selection.selection.converters.PriceEntityToPriceConverter;
import com.example.selection.selection.converters.PriceToPriceResponseConverter;
import com.example.selection.selection.exception.ProductNotFoundException;
import com.example.selection.selection.models.Price;
import com.example.selection.selection.models.request.PriceRequest;
import com.example.selection.selection.models.response.PriceResponse;
import com.example.selection.selection.repository.PriceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceServiceImpl implements PriceService {
    final PriceRepository priceRepository;
    final PriceEntityToPriceConverter priceConverter;
    final PriceToPriceResponseConverter priceResponseConverter;

    @Autowired
    public PriceServiceImpl(final PriceRepository priceRepository,
            final PriceEntityToPriceConverter priceConverter,
            final PriceToPriceResponseConverter priceResponseConverter) {
        this.priceRepository = priceRepository;
        this.priceConverter = priceConverter;
        this.priceResponseConverter = priceResponseConverter;
    }

    public PriceResponse getPrice(final PriceRequest priceRequest) throws ProductNotFoundException{
        // it would be better to send date for a query filter
        return this.priceRepository.findByProductIdAndBrandId(priceRequest.getProductId(), priceRequest.getBrandId()).stream()
                .map(this.priceConverter::convert)
                .filter(this.filterTime(priceRequest))
                .sorted(Comparator.comparing(Price::getPrice)
                        .thenComparing(Price::getPriority))
                .findFirst()
                .map(this.priceResponseConverter::convert)
                .orElseThrow(ProductNotFoundException::new);
    }
    
    private Predicate<Price> filterTime(final PriceRequest priceRequest) {
        return price -> price.getStartDate().isBefore(priceRequest.getDate()) 
        && price.getEndDate().isAfter(priceRequest.getDate());
    }
}

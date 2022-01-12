package com.example.selection.selection.converters;

import com.example.selection.selection.models.Price;
import com.example.selection.selection.models.response.PriceResponse;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class PriceToPriceResponseConverter implements Converter<Price, PriceResponse> {

    @Override
    public PriceResponse convert(final Price price) {
        return PriceResponse.builder()
                .brandId(price.getBrandId())
                .priceList(price.getPriceList())
                .productId(price.getProductId())
                .price(price.getPrice())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .build();
    }
    
}
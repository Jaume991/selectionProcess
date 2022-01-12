package com.example.selection.selection.converters;

import com.example.selection.selection.models.Price;
import com.example.selection.selection.repository.models.PriceEntity;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class PriceEntityToPriceConverter implements Converter<PriceEntity, Price> {

    @Override
    public Price convert(PriceEntity price) {
        return Price.builder()
                .brandId(price.getBrandId())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .priceList(price.getPriceList())
                .productId(price.getProductId())
                .priority(price.getPriority())
                .price(price.getPrice())
                .currency(price.getCurr())
                .build();
    }
}

package com.example.selection.selection.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import com.example.selection.selection.converters.PriceEntityToPriceConverter;
import com.example.selection.selection.converters.PriceToPriceResponseConverter;
import com.example.selection.selection.exception.ProductNotFoundException;
import com.example.selection.selection.models.request.PriceRequest;
import com.example.selection.selection.models.response.PriceResponse;
import com.example.selection.selection.repository.PriceRepository;
import com.example.selection.selection.repository.models.PriceEntity;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PriceServiceTest {
    @Autowired
    PriceRepository pricerepository;
    @Autowired
    PriceToPriceResponseConverter priceResponseConverter;
    @Autowired
    PriceEntityToPriceConverter priceConverter;

    @Autowired
    PriceServiceImpl priceService;

    // Checked all test provided
    @Test
    public void shouldReturnMinPriceByPriority() throws ProductNotFoundException {
        this.executeAssert(LocalDateTime.of(2020, 6, 14, 10, 00, 00),
                PriceResponse.builder()
                .brandId(1)
                .priceList(1)
                .productId(35455)
                .price(BigDecimal.valueOf(35.5).setScale(2))
                .startDate(LocalDateTime.of(2020, 6, 14, 00, 00, 00))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .build());
        this.executeAssert(LocalDateTime.of(2020, 6, 14, 16, 00, 00),
                PriceResponse.builder()
                .brandId(1)
                .priceList(2)
                .productId(35455)
                .price(BigDecimal.valueOf(25.45).setScale(2))
                .startDate(LocalDateTime.of(2020, 6, 14, 15, 00, 00))
                .endDate(LocalDateTime.of(2020, 6, 14, 18, 30, 00))
                .build());
        this.executeAssert(LocalDateTime.of(2020, 6, 21, 16, 00, 00),
                PriceResponse.builder()
                .brandId(1)
                .priceList(1)
                .productId(35455)
                .price(BigDecimal.valueOf(35.5).setScale(2))
                .startDate(LocalDateTime.of(2020, 6, 14, 00, 00, 00))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .build());
        this.executeAssert(LocalDateTime.of(2020, 6, 15, 10, 00, 00),
                PriceResponse.builder()
                .brandId(1)
                .priceList(3)
                .productId(35455)
                .price(BigDecimal.valueOf(30.5).setScale(2))
                .startDate(LocalDateTime.of(2020, 6, 15, 00, 00, 00))
                .endDate(LocalDateTime.of(2020, 6, 15, 11, 00, 00))
                .build());
        this.executeAssert(LocalDateTime.of(2020, 6, 16, 21, 00, 00),
                PriceResponse.builder()
                .brandId(1)
                .priceList(1)
                .productId(35455)
                .price(BigDecimal.valueOf(35.5).setScale(2))
                .startDate(LocalDateTime.of(2020, 6, 14, 00, 00, 00))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .build());
    }

    public void executeAssert(final LocalDateTime localDateTime, final PriceResponse priceExpected) throws ProductNotFoundException {
        assertEquals(priceExpected, this.priceService.getPrice(PriceRequest.builder()
                .brandId(1)
                .productId(35455)
                .date(localDateTime)
                .build()));
    }
}

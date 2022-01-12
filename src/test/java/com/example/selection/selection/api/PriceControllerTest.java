package com.example.selection.selection.api;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import com.example.selection.selection.api.handler.PriceAdvice;
import com.example.selection.selection.exception.ProductNotFoundException;
import com.example.selection.selection.models.request.PriceRequest;
import com.example.selection.selection.services.PriceService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {PriceController.class, PriceService.class, PriceAdvice.class})
@WebMvcTest
public class PriceControllerTest {
    @MockBean
    PriceService priceService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldReturnPrice() throws Exception {
        final String priceRequestJson = "{" +
            "\"brandId\": 1," +
            "\"productId\": 35455," +
            "\"date\": \"2020-06-14T16:00:00\"" +
        "}";
        
        final PriceRequest priceRequest = PriceRequest.builder()
                .brandId(1)
                .productId(35455)
                .date(LocalDateTime.of(2020, 06, 14, 16, 0, 0))
                .build();

        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(priceRequestJson))
                .andExpect(status().isOk());

        verify(this.priceService).getPrice(priceRequest);
    }

    @Test
    public void expectNotFound() throws Exception {
        final String priceRequestJson = "{" +
            "\"brandId\": 1," +
            "\"productId\": 35455," +
            "\"date\": \"2020-06-14T16:00:00\"" +
        "}";
        
        final PriceRequest priceRequest = PriceRequest.builder()
                .brandId(1)
                .productId(35455)
                .date(LocalDateTime.of(2020, 06, 14, 16, 0, 0))
                .build();
        
        doThrow(new ProductNotFoundException())
                .when(this.priceService)
                .getPrice(priceRequest);

        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(priceRequestJson))
                .andExpect(status().isNotFound());
    }
}

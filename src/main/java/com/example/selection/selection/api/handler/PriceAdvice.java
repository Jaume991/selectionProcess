package com.example.selection.selection.api.handler;

import com.example.selection.selection.api.PriceController;
import com.example.selection.selection.exception.ProductNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = PriceController.class)
public class PriceAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public void handleProductNotFound() {
        // just return not found status
    }
}

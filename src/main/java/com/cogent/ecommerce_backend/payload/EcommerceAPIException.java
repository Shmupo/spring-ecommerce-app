package com.cogent.ecommerce_backend.payload;

import org.springframework.http.HttpStatus;

public class EcommerceAPIException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public EcommerceAPIException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public EcommerceAPIException(String customMessage, HttpStatus status, String message) {
        super(customMessage);
        this.status = status;
        this.message = message;
    }
}

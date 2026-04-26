package com.microservice.ecom_order_service.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;

import java.util.Objects;

public class CustomDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        if(Objects.equals(methodKey, "404")){
            return new RuntimeException("product ID not found");
        }
        return new RuntimeException("Generic Error "+ response.status());
    }
}

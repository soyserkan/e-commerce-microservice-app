package com.integration.orderservice.exception.handler;

import com.integration.orderservice.dto.InternalApiResponse;
import com.integration.orderservice.dto.Message;
import com.integration.orderservice.exception.enums.MessageCodes;
import com.integration.orderservice.exception.exceptions.ProductNotFoundException;
import com.integration.orderservice.exception.utils.MessageUtils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotFoundException.class)
    public InternalApiResponse<String> handleProductNotCreatedException(ProductNotFoundException exception) {
        return InternalApiResponse.<String>builder()
                .message(Message.builder()
                        .title(MessageUtils.getMessage(exception.getLanguage(), MessageCodes.ERROR))
                        .description(MessageUtils.getMessage(exception.getLanguage(), exception.getMessageCode()))
                        .build())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .hasError(true)
                .errorMessages(Collections.singletonList(exception.getMessage()))
                .build();
    }
}

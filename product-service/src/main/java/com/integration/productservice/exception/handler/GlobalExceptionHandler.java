package com.integration.productservice.exception.handler;

import com.integration.productservice.exception.enums.MessageCodes;
import com.integration.productservice.exception.exceptions.ProductNotCreatedException;
import com.integration.productservice.exception.utils.MessageUtils;
import com.integration.productservice.dto.InternalApiResponse;
import com.integration.productservice.dto.Message;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotCreatedException.class)
    public InternalApiResponse<String> handleProductNotCreatedException(ProductNotCreatedException exception) {
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

package com.microservice.priceandinventoryservice.exception.handler;

import com.microservice.priceandinventoryservice.dto.InternalApiResponse;
import com.microservice.priceandinventoryservice.dto.Message;
import com.microservice.priceandinventoryservice.exception.enums.MessageCodes;
import com.microservice.priceandinventoryservice.exception.exceptions.ProductNotFoundException;
import com.microservice.priceandinventoryservice.exception.utils.MessageUtils;
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

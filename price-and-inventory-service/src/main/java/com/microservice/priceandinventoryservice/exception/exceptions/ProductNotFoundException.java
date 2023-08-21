package com.microservice.priceandinventoryservice.exception.exceptions;

import com.microservice.priceandinventoryservice.enums.Language;
import com.microservice.priceandinventoryservice.exception.enums.IMessageCode;
import com.microservice.priceandinventoryservice.exception.utils.MessageUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ProductNotFoundException extends RuntimeException {
    private final Language language;
    private final IMessageCode messageCode;

    public ProductNotFoundException(Language language, IMessageCode messageCode, String message) {
        super(MessageUtils.getMessage(language, messageCode));
        this.language = language;
        this.messageCode = messageCode;
        log.error(
                "ProductNotFoundException -> {} developer message -> {}",
                MessageUtils.getMessage(language, messageCode),
                message
        );
    }
}

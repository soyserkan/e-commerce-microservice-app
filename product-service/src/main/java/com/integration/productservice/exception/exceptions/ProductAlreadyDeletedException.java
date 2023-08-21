package com.integration.productservice.exception.exceptions;

import com.integration.productservice.enums.Language;
import com.integration.productservice.exception.enums.IMessageCode;
import com.integration.productservice.exception.enums.MessageCodes;
import com.integration.productservice.exception.utils.MessageUtils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ProductAlreadyDeletedException extends RuntimeException {
    private final Language language;
    private final IMessageCode messageCode;

    public ProductAlreadyDeletedException(Language language, IMessageCode messageCode, String message) {
        super(MessageUtils.getMessage(language, messageCode));
        this.language = language;
        this.messageCode = messageCode;
        log.error(
                "ProductAlreadyDeletedException -> {} developer message -> {}",
                MessageUtils.getMessage(language, messageCode),
                message
        );
    }
}

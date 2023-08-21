package com.integration.productservice.exception.exceptions;

import com.integration.productservice.enums.Language;
import com.integration.productservice.exception.enums.IMessageCode;
import com.integration.productservice.exception.utils.MessageUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Getter
public class ProductNotCreatedException extends RuntimeException {
    private final IMessageCode messageCode;
    private final Language language;
    private final String message;

    public ProductNotCreatedException(Language language, IMessageCode messageCode, String message) {
        super(MessageUtils.getMessage(language, messageCode));
        this.messageCode = messageCode;
        this.language = language;
        this.message = message;
        log.error(
                "[ProductNotCreatedException] -> error: {} description: {}",
                MessageUtils.getMessage(language, messageCode),
                message
        );
    }
}

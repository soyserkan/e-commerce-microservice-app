package com.integration.orderservice.exception.enums;

public enum MessageCodes implements IMessageCode {

    OK(1000),
    ERROR(1001),
    SUCCESS(1002),
    PRODUCT_NOT_FOUND_EXCEPTION(1502),
    ;

    private final int value;

    MessageCodes(int value) {
        this.value = value;
    }

    @Override
    public int getMessageCode() {
        return value;
    }
}

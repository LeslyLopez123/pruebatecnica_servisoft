package com.servisoft.product.exceptions;

import org.apache.coyote.BadRequestException;

public class SimpleException extends BadRequestException {
    public SimpleException(final String message) {
        super(message);
    }
}

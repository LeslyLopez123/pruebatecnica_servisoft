package com.servisoft.product.utils;

import com.servisoft.product.exceptions.SimpleException;

public class ValidateValuesUtils {

    private ValidateValuesUtils() {
    }

    public static void validateEmptyString(final String value, final String message) throws SimpleException {
        if(value.trim().isEmpty()) {
            throw new SimpleException("Error: " + message + " is empty.");
        }
    }

    public static void validateEmptyDouble(final double value, final String message) throws SimpleException {
        if(value <= 0) {
            throw new SimpleException("Error: " + message + " cannot be less than 0.");
        }
    }

    public static void validateEmptyInteger(final int value, final String message) throws SimpleException {
        if(value <= 0) {
            throw new SimpleException("Error: " + message + " cannot be less than 0.");
        }
    }

    public static void validateEmptyLong(final long value, final String message) throws SimpleException {
        if(value <= 0) {
            throw new SimpleException("Error: " + message + " cannot be less than 0.");
        }
    }
}

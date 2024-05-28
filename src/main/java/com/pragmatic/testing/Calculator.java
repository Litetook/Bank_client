package com.pragmatic.testing;

public class Calculator {
    private Logger logger;
    private String additionalData;

    public Calculator(Logger logger) {
        this.logger = logger;
        this.additionalData = logger.getAdditionalData();
    }

    public int add(int a, int b) {
        int result = a + b;
        logger.log("Adding " + a + " and " + b + ": " + result);
        return result;
    }

    public int subtract(int a, int b) {
        int result = a - b;
        logger.log("Subtracting " + a + " from " + b + ": " + result);
        return result;
    }

    public int multiply(int a, int b) {
        int result = a * b;
        logger.log("Multiplying " + a + " and " + b + ": " + result);
        return result;
    }

    public int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        int result = a / b;
        logger.log("Dividing " + a + " by " + b + ": " + result);
        return result;
    }

    public String getAdditionalData() {
        return additionalData;
    }
}
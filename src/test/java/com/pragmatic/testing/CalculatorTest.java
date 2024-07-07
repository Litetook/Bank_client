package com.pragmatic.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CalculatorTest {

    @Mock
    private Database mockDatabase;

    @Mock
    private Logger mockLogger;

    @InjectMocks
    private Calculator calculator;

    @BeforeEach
    public void setup() {
        Mockito.when(mockLogger.getAdditionalData()).thenReturn("Mocked additional data");
        Mockito.when(mockDatabase.returnTest()).thenReturn("mockTest");
        calculator = new Calculator(mockLogger); // Створюємо Calculator після налаштування mockLogger
    }

    @Test
    public void testSubtract() {
        System.out.println(mockDatabase.returnTest());
        // Налаштовуємо mockLogger для повернення додаткових даних

        // Викликаємо метод subtract
        int result = calculator.subtract(5, 3);

        // Перевіряємо результат
        assertEquals(2, result);
}
}
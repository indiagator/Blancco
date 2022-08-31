package com.testblancco.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class CalculatorServiceTest {


    @Test
    public void addMethodIsInfactAdding()
    {

        CalculatorService calculatorService = new CalculatorService();
        assertEquals(67,calculatorService.add(60,7));

    }

    @Test
    public void subtractIsInfactSubtracting()
    {

        CalculatorService calculatorService = new CalculatorService();
        assertEquals(10, calculatorService.subtract(45,35));
    }

    @Test
    public void multiplyIsInfactMultiplying()
    {
        CalculatorService calculatorService = new CalculatorService();
        assertTrue( calculatorService.multiply(4,5) == 20);
    }

    //---------

    @Test
    public void addShouldInfactAdd()
    {


        CalculatorService calculatorService = new CalculatorService();

        ServletRequest request = Mockito.mock(ServletRequest.class);

        when(request.exists()).thenReturn(true);
        when(request.getId()).thenReturn(7);

        assertEquals(10, calculatorService.add(4,6));

        assertTrue(calculatorService.add(5,5) == 10);

        assertTrue( request instanceof ServletRequest);



    }
}

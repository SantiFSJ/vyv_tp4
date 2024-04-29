package com.unrn.vv.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.unrn.vv.exeptions.DivideByCeroExeption;


public class CalcTest {

    Calc calc;

    @BeforeEach
    public void setUp() {
        this.calc = new Calc();
    }

    @AfterEach
    public void tearDown() {
        this.calc = null;
    }


    @Test
    public void testSumPositive() {
        int a = 5;
        int b = 7;
        
        int result = calc.sum(a,b);
        
        assertEquals(12, result);

    }

    @Test
    public void testSumNegative() {
        int a = -5;
        int b = -7;
        
        int result = calc.sum(a,b);
        
        assertEquals(-12, result);

    }

    @Test
    public void testSub02() {
        int a = 5;
        int b = 7;
        
        int result = calc.sub(a,b);
        
        assertEquals(-2, result);

    }

    @Test
    public void testSub03() {
        int a = 7;
        int b = 5;
        
        int result = calc.sub(a,b);
        
        assertEquals(2, result);

    }

    @Test
    public void testDivide04() {
        int a = 7;
        int b = 0;
                
        assertThrows(DivideByCeroExeption.class, () -> calc.divide(a,b));

    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE})
    public void testIsOdd(Integer a) {
        
        boolean result = calc.isOdd(a);
    
        assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource({"0.1,0.2,0.3", "-1,1,0", "1,-1,0","-1,-1,-2"})
    public void testSumPositive(int a, int b, int res) {
        int result = calc.sum(a,b);
        assertEquals(res, result);
    }



}

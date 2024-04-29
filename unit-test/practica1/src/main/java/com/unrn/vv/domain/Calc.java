package com.unrn.vv.domain;

import com.unrn.vv.exeptions.DivideByCeroExeption;

public class Calc {

    public int sum(int a, int b) {
       return a + b;
    }

    public int sub(int a, int b) {
        return a - b;
    }

    public int divide(int a, int b) throws Exception {
        
        if (b == 0) throw new DivideByCeroExeption();
        return a / b;    
      
    }

    public boolean isOdd(int a) {
        return a % 2 != 0;
    }

}

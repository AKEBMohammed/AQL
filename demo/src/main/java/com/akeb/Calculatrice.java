package com.akeb;

public class Calculatrice {
    public int additionner(int a, int b) {
        result = a + b;
        return result;
    }
    
    private int result;
    
    // Adding a getter for result to verify state
    public int getResult() {
        return result;
    }
}

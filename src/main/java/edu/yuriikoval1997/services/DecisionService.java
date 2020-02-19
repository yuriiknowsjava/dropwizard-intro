package edu.yuriikoval1997.services;

public class DecisionService {

    public String whatIsNumber(int n) {
        if (n % 2 == 0) {
            return odd();
        }
        return even();
    }

    private String odd() {
        return "The number is odd.";
    }

    private String even() {
        return "The number is even.";
    }
}

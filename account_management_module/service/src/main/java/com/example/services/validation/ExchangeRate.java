package com.example.services.validation;

public class ExchangeRate {

        private final double USD;
        private final double RUR;
        private final double AMD;
        private final double EUR;


        public ExchangeRate() {
            this.AMD = 1;
            this.USD = 385.94;
            this.RUR = 4.83;
            this.EUR = 415.16;

        }

    public double getUSD() {
        return USD;
    }

    public double getRUR() {
        return RUR;
    }

    public double getAMD() {
        return AMD;
    }

    public double getEUR() {
        return EUR;
    }
}



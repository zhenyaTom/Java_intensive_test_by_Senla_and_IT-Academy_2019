package com.senla.atm.entity;

public class CashMachine {
    private Double availableCash;
    private final Double TOP_UP_LIMIT = 1000000.00;

    public Double getAvailableCash() {
        return availableCash;
    }

    public void setAvailableCash(Double availableCash) {
        this.availableCash = availableCash;
    }

    public Double getTopUpLimit() {
        return TOP_UP_LIMIT;
    }

    @Override
    public String toString (){
        return String.valueOf(availableCash);
    }
}
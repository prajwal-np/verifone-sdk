package com.kiosk.kioskVerifone.model;

import com.verifone.payment_sdk.Decimal;

public class PaymentModel {
    public long amount;
    public long tax;
    public PaymentModel(long amount, long tax){
        this.amount = amount;
        this.tax = tax;
    }
    long getAmount(){
      return amount;
    }
    long getTax(){
        return tax;
    }
}

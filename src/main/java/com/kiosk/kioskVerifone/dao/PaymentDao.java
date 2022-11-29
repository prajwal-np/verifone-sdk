package com.kiosk.kioskVerifone.dao;

import com.kiosk.kioskVerifone.model.PaymentModel;
import com.verifone.payment_sdk.Decimal;
import org.springframework.stereotype.Controller;

@Controller
public class PaymentDao {

    private Decimal amount;
    private Decimal tax;

    public void VerifonePayment(PaymentModel payment){
        this.amount = new Decimal(2,payment.amount);
        this.tax = new Decimal(2,payment.tax);
    }

    public Decimal  getAmount(){
        return this.amount;
    }
    public Decimal  getTax(){
        return this.tax;
    }
}

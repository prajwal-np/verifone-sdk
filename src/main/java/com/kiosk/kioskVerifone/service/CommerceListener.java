package com.kiosk.kioskVerifone.service;

import com.verifone.payment_sdk.CommerceListenerAdapter;
import com.verifone.payment_sdk.PaymentCompletedEvent;
import com.verifone.payment_sdk.Status;
import com.verifone.payment_sdk.TransactionEvent;

class CommerceListener {
    private final CommerceListenerAdapter mCommerceListener = new CommerceListenerAdapter() {
        @Override
        public void handleStatus(Status status){
            System.out.println(status);
        }

        @Override
        public  void handleTransactionEvent(TransactionEvent event){
            System.out.println(event);
        }

        @Override
        public void handlePaymentCompletedEvent(PaymentCompletedEvent paymentCompletedEvent) {
            String s = "handlePaymentCompletedEvent: " + paymentCompletedEvent.getType() + ":" + paymentCompletedEvent.getMessage();
        }
    };

}
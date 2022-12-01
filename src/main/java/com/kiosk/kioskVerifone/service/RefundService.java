package com.kiosk.kioskVerifone.service;

import com.kiosk.kioskVerifone.dao.PaymentDao;
import com.verifone.payment_sdk.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;


public class RefundService {

    private static final String TAG = "PaymentSdkRefundHandler";
    private PaymentSdk paymentSdk;
    private Payment payment;

    private final static Logger LOGGER = Logger.getLogger(PaymentSdk.class.getName());
    @Autowired
    public RefundService() {
//        paymentSdk = PaymentSdk.create();
//        payment = Payment.create();
//        PaymentSdk.configureLogFile("logfile.txt", 1024 * 5);
    }

    public void refundPayment(String amount) {
        LOGGER.info(TAG + "::" + "Start transaction");
        AmountTotals amountTotals = AmountTotals.create(true);
        double f = Double.parseDouble(amount);
        amountTotals.setTotal(new Decimal(f));
        payment.setRequestedAmounts(amountTotals);
        Status result = paymentSdk.getTransactionManager().processRefund(payment);
        LOGGER.info(TAG + "::" + "startPayment returned " + result.getStatus() + " : " + result.getMessage());
        if (result.getStatus() != StatusCode.SUCCESS) {
            LOGGER.info(TAG + "::" + "Start transaction failed");
        } else {
            LOGGER.info(TAG + "::" + "Start transaction success");
        }
    }
}

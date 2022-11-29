package com.kiosk.kioskVerifone.service;

import com.verifone.payment_sdk.Payment;
import com.verifone.payment_sdk.PaymentSdk;
import com.verifone.payment_sdk.TransactionManager;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ReversalService {
    private static final String TAG = "PaymentSdkReversalHandler";
    private PaymentSdk paymentSdk;
    private Payment payment;

    private TransactionManager transactionManager;

    private final static Logger LOGGER = Logger.getLogger(PaymentSdk.class.getName());

    public ReversalService() {
//        paymentSdk = PaymentSdk.create();
//        payment = Payment.create();
//        transactionManager = paymentSdk.getTransactionManager();
//        PaymentSdk.configureLogFile("logfile.txt", 1024 * 5);
    }

    public void initReversal(String appSpecificData){
        payment.setAppSpecificData(appSpecificData);
        transactionManager.processVoid(payment);
    }
}

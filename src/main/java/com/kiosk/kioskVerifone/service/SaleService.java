package com.kiosk.kioskVerifone.service;

import com.kiosk.kioskVerifone.dao.PaymentDao;
import com.kiosk.kioskVerifone.model.PaymentModel;
import com.verifone.payment_sdk.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.verifone.payment_sdk.AmountTotals;
import com.verifone.payment_sdk.Payment;
import com.verifone.payment_sdk.TransactionManager;

import java.util.logging.Logger;



@Service
public class SaleService extends CommerceListener{

    private static final String TAG = "PaymentSdkSaleHandler";
    final PaymentDao paymentDao;
    private PaymentSdk paymentSdk;
    private Payment payment;

    private final static Logger LOGGER = Logger.getLogger(PaymentSdk.class.getName());

    @Autowired
    public SaleService(PaymentDao paymentDao){
        this.paymentDao = paymentDao;
    }
    public String initiatePayment(PaymentModel paymentList){
       try{
           this.paymentDao.VerifonePayment(paymentList);
           paymentSdk = PaymentSdk.create();
           payment = Payment.create();

           // set payment amount start
           AmountTotals requestedAmounts = payment.getRequestedAmounts();
           System.out.println("Tax"+this.paymentDao.getTax());
           requestedAmounts.setTax(this.paymentDao.getTax());
           requestedAmounts.setTotal(this.paymentDao.getAmount());
           System.out.println(this.paymentDao.getTax());
           // end

           TransactionManager transactionManager = paymentSdk.getTransactionManager();
           Status status = transactionManager.startPayment(payment);
           LOGGER.info(status.getMessage());

           return "Payment Initiated";
       } catch(Error e){
           LOGGER.info(e.getLocalizedMessage());
           return "Payment failed";
       }
    };

    public void startOtherPayment(String amount) {
        LOGGER.info(TAG + "::" + "Start transaction");
        AmountTotals amountTotals = AmountTotals.create(true);
        double f = Double.parseDouble(amount);
        amountTotals.setTotal(new Decimal(f));
        payment = Payment.create();
        payment.setRequestedAmounts(amountTotals);
        Status result = paymentSdk.getTransactionManager().startPayment(payment);
        LOGGER.info(TAG + "::" + "startPayment returned " + result.getStatus() + " : " + result.getMessage());
        if (result.getStatus() != StatusCode.SUCCESS) {
            LOGGER.info(TAG + "::" + "Start transaction failed");
        }
    }
}
package com.kiosk.kioskVerifone.service;

import com.kiosk.kioskVerifone.dao.PaymentDao;
import com.kiosk.kioskVerifone.model.PaymentModel;
import com.verifone.payment_sdk.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.verifone.payment_sdk.AmountTotals;
import com.verifone.payment_sdk.Payment;
import com.verifone.payment_sdk.TransactionManager;

import java.util.HashMap;
import java.util.logging.Logger;



@Service
public class SaleService extends CommerceListener{

    private static final String TAG = "SalesSrvice";
    final PaymentDao paymentDao;
    private PaymentSdk paymentSdk;
    private Payment payment;

    private String connectionType= "tcpip";
    private String terminalAddress="127.0.0.1";

    private final static Logger LOGGER = Logger.getLogger(PaymentSdk.class.getName());

    @Autowired
    public SaleService(PaymentDao paymentDao){
        this.paymentDao = paymentDao;
        paymentSdk = PaymentSdk.create();
        initSdk();
        startSession();
        PaymentSdk.configureLogFile("logfile.text",1024*5);
    }

    public void initSdk(){
        HashMap<String,String> paramMap = new HashMap<>();
        paramMap.put(PsdkDeviceInformation.DEVICE_CONNECTION_TYPE_KEY, connectionType);
        paramMap.put(PsdkDeviceInformation.DEVICE_SERIAL_NUMBER_KEY, PsdkDeviceInformation.ACCEPT_ANY_DEVICE_VALUE);
        paramMap.put(PsdkDeviceInformation.DEVICE_ADDRESS_KEY, terminalAddress);
        paymentSdk.initialize(mCommerceListener);
    }

    public void startSession(){
        Transaction transaction = Transaction.create();
        transaction.setCurrency("EUR");
        boolean result = paymentSdk.getTransactionManager().startSession2(transaction);
        LOGGER.info(TAG+"::"+result);
    }

    public void logoutSession(){
        boolean result = paymentSdk.getTransactionManager().endSession();
        String s = "startSession returned" + result;
        LOGGER.info(TAG+"::"+s);
    }
    public String initiatePayment(PaymentModel paymentList){
       try{
           this.paymentDao.VerifonePayment(paymentList);
           payment = Payment.create();

           // set payment amount start
           AmountTotals requestedAmounts = payment.getRequestedAmounts();
//           requestedAmounts.setTax(this.paymentDao.getTax());
           requestedAmounts.setTotal(new Decimal(10));
           payment.setRequestedAmounts(requestedAmounts);
           // end

           TransactionManager transactionManager = paymentSdk.getTransactionManager();
           Status status = transactionManager.startPayment(payment);
           LOGGER.info("payment"+"::" + status.getMessage());
           return   String.valueOf(status.getStatus());
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
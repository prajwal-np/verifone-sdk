package com.kiosk.kioskVerifone.service;

import com.verifone.payment_sdk.*;

import java.util.logging.Logger;

class CommerceListener {
    private static final String TAG = "CommerceListener";
    private final static Logger LOGGER = Logger.getLogger(PaymentSdk.class.getName());
    public  CommerceListener2 mCommerceListener = new CommerceListener2() {
        @Override
        public void handleStatus(Status status){
            String s = status.getMessage() +":"+status.getStatus();
            LOGGER.info(TAG+"::"+s);
        }

        @Override
        public void handleDeviceVitalsInformationEvent(DeviceVitalsInformationEvent deviceVitalsInformationEvent) {

        }

        @Override
        public void handlePinEvent(PinEvent pinEvent) {

        }

        @Override
        public void handleCommerceEvent(CommerceEvent commerceEvent) {

        }

        @Override
        public  void handleTransactionEvent(TransactionEvent event){
        }

        @Override
        public void handleAmountAdjustedEvent(AmountAdjustedEvent amountAdjustedEvent) {

        }

        @Override
        public void handleBasketAdjustedEvent(BasketAdjustedEvent basketAdjustedEvent) {

        }

        @Override
        public void handleBasketEvent(BasketEvent basketEvent) {

        }

        @Override
        public void handleCardInformationReceivedEvent(CardInformationReceivedEvent cardInformationReceivedEvent) {

        }

        @Override
        public void handleDeviceManagementEvent(DeviceManagementEvent deviceManagementEvent) {

        }

        @Override
        public void handleLoyaltyReceivedEvent(LoyaltyReceivedEvent loyaltyReceivedEvent) {

        }

        @Override
        public void handlePaymentCompletedEvent(PaymentCompletedEvent paymentCompletedEvent) {
            String s = "handlePaymentCompletedEvent: " + paymentCompletedEvent.getType() + ":" + paymentCompletedEvent.getMessage();
            LOGGER.info(TAG+"::"+s);
        }

        @Override
        public void handleReceiptDeliveryMethodEvent(ReceiptDeliveryMethodEvent receiptDeliveryMethodEvent) {

        }

        @Override
        public void handleStoredValueCardEvent(StoredValueCardEvent storedValueCardEvent) {

        }

        @Override
        public void handleUserInputEvent(UserInputEvent userInputEvent) {

        }

        @Override
        public void handleReconciliationEvent(ReconciliationEvent reconciliationEvent) {

        }

        @Override
        public void handleReconciliationsListEvent(ReconciliationsListEvent reconciliationsListEvent) {

        }

        @Override
        public void handleTransactionQueryEvent(TransactionQueryEvent transactionQueryEvent) {

        }

        @Override
        public void handleHostAuthorizationEvent(HostAuthorizationEvent hostAuthorizationEvent) {

        }

        @Override
        public void handleHostFinalizeTransactionEvent(HostFinalizeTransactionEvent hostFinalizeTransactionEvent) {

        }

        @Override
        public void handleNotificationEvent(NotificationEvent notificationEvent) {

        }

        @Override
        public void handlePrintEvent(PrintEvent printEvent) {

        }

    };


}
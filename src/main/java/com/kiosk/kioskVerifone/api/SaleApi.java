package com.kiosk.kioskVerifone.api;

import com.kiosk.kioskVerifone.model.PaymentModel;
import com.kiosk.kioskVerifone.service.SaleService;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/test")
@RestController
public class SaleApi {

    private final SaleService saleService;

    @Autowired
    public SaleApi(SaleService saleService){
        this.saleService = saleService;
    }

    @PostMapping()
    public String initiatePayment(@RequestBody() PaymentModel paymentBody){
        return saleService.initiatePayment(paymentBody);
    }
}

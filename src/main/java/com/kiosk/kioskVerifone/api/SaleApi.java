package com.kiosk.kioskVerifone.api;

import com.kiosk.kioskVerifone.model.PaymentModel;
import com.kiosk.kioskVerifone.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/test")
@RestController
public class SaleApi {


   private SaleApi(){
    }
    @GetMapping(value="init/{ip}")
    public String initiatePayment(@PathVariable String ip){
        SaleService saleService = new SaleService(ip);
        return saleService.initiatePayment();
    }
    @GetMapping(value="hello")
    public String test(){
        return "TESTING";
    }
}

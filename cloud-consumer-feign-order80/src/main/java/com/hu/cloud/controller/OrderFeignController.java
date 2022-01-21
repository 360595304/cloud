package com.hu.cloud.controller;

import com.hu.cloud.entities.Payment;
import com.hu.cloud.entities.Result;
import com.hu.cloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suhu
 * @createDate 2022/1/7
 */
@RestController
@Slf4j
public class OrderFeignController {
    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public Result<Payment> getPaymentById(@PathVariable("id") Long id) {
        return  paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/timeout")
    public String timeout() {
        return  paymentFeignService.timeout();
    }
}

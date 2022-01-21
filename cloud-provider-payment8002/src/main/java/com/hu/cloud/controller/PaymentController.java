package com.hu.cloud.controller;

import com.hu.cloud.entities.Payment;
import com.hu.cloud.entities.Result;
import com.hu.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author suhu
 * @createDate 2022/1/1
 */
@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public Result create(@RequestBody Payment payment) {
        boolean result = paymentService.save(payment);
        log.info("*****插入结果：" + result);

        if (result) {
            return new Result(200, "插入数据库成功,serverPort: " + serverPort, true);
        } else {
            return new Result(444, "插入数据库失败", null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public Result getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getById(id);
        log.info("*****插入结果：" + payment);

        if (payment != null) {
            return new Result(200, "查询成功,serverPort: " + serverPort, payment);
        } else {
            return new Result(444, "没有对应记录，查询id=" + id, null);
        }
    }

    @GetMapping("/payment/lb")
    public String loadBalance() {
        return serverPort;
    }
}

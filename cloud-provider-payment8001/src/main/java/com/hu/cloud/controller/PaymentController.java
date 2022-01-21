package com.hu.cloud.controller;

import com.hu.cloud.entities.Payment;
import com.hu.cloud.entities.Result;
import com.hu.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private DiscoveryClient discoveryClient;

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

    @GetMapping("payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("*******service: " + service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return discoveryClient;
    }

    @GetMapping("/payment/timeout")
    public String timeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping("/payment/lb")
    public String loadBalance() {
        return serverPort;
    }
}

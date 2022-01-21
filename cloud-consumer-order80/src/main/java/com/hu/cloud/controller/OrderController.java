package com.hu.cloud.controller;

import com.hu.cloud.entities.Payment;
import com.hu.cloud.entities.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * @author suhu
 * @createDate 2022/1/2
 */
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

//    public static final String PRIMARY_URL = "http://localhost:8001";
    public static final String PRIMARY_URL = "http://CLOUD-PAYMENT-SERVICE";

    @GetMapping("/consumer/payment/create")
    public Result<Payment> create(Payment payment) {
        return restTemplate.postForObject(PRIMARY_URL + "/payment/create", payment, Result.class);
    }



    @GetMapping("/consumer/payment/get/{id}")
    public Result<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PRIMARY_URL + "/payment/get/" + id, Result.class);
    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public Result<Payment> getPayment2(@PathVariable("id") Long id) {
        ResponseEntity<Result> entity = restTemplate.getForEntity(PRIMARY_URL + "/payment/get/" + id, Result.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new Result<>(444, "操作失败");
        }
    }
}

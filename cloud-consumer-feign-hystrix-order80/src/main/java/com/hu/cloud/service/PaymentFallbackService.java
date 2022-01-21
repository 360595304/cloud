package com.hu.cloud.service;

import org.springframework.stereotype.Component;

/**
 * @author suhu
 * @createDate 2022/1/8
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "-----PaymentFallbackService Fallback-paymentInfo_OK, o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "-----PaymentFallbackService Fallback-paymentInfo_Timeout, o(╥﹏╥)o";
    }
}

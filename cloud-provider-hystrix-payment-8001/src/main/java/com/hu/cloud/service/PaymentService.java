package com.hu.cloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author suhu
 * @createDate 2022/1/7
 */
@Service
public class PaymentService {
    /**
     * 正常访问
     */
    public String paymentInfo_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_OK, id：" + id + "\tSUCCESS";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_Timeout(Integer id) {
        int timeNumber = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        int age = 10 / 0;
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_OK, id：" + id;
    }

    public String paymentInfo_TimeoutHandler(Integer id) {
        return "8001业务繁忙或运行出错，请稍后再试o(╥﹏╥)o " + Thread.currentThread().getName() + " id：" + id;
    }

    //=========服务熔断
    // 在 "一个时间周期类" 的 "N次调用" 中若 "失败率" 到达一定的值则会进行断路
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),  // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间周期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), // 错误率
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0)
            throw new RuntimeException("******id 不能负数");

        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\t" + "调用成功，流水号: " + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " + id;
    }
}

package com.hu.cloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.cloud.dao.PaymentDao;
import com.hu.cloud.entities.Payment;
import com.hu.cloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author suhu
 * @createDate 2022/1/1
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentDao, Payment> implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

}

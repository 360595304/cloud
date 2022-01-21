package com.hu.cloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hu.cloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author suhu
 * @createDate 2022/1/1
 */
@Mapper
public interface PaymentDao extends BaseMapper<Payment> {

}

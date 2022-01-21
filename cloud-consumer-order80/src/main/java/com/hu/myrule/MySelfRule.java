package com.hu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author suhu
 * @createDate 2022/1/6
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule() {
        return new RoundRobinRule();
    }
}

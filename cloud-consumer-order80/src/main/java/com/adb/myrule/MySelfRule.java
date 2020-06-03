package com.adb.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 该类不要放在和主启动类的包平级或者主启动类的包下面
 * 防止被Ribbon所公用
 */
@Configuration
public class MySelfRule {


    /**
     * 使用自己选择的随机调度方法（默认是轮训）
     * @return
     */
    @Bean
    public IRule myrule(){
        return new RandomRule();
    }
}

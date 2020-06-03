package com.adb.springcloud.service.impl;

import com.adb.springcloud.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;
//这不是传统的service,这是和rabbitmq打交道的，不需要加注解@Service
//这里不调用dao，调用消息中间件的service
//信道channel和exchange绑定在一起
@EnableBinding(Source.class)
public class IMessageServiceImpl implements IMessageService {
    //消息管道
    /*@Autowired
    @Qualifier("output")*/    //@Autowired注解默认按类型来匹配，配合@Qualifier就可以按名称来屁匹配；@Resource默认按名称来匹配
    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String uuid = UUID.randomUUID().toString();
        boolean send = output.send(MessageBuilder.withPayload(uuid).build());
        return uuid;
    }
}

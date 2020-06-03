package com.adb.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

@EnableBinding(Sink.class)  //表示是消息的接收者
public class ConsumerMessageListenerController {

    @Value("${server.port}")
    private String port;

    @StreamListener(Sink.INPUT)  //监听消息对列，实时获取消息
    public void receiveMessage(Message<String> message){
        System.out.println("消费者1号，------->接收到的消息： "+message.getPayload()+"\t port: "+port);
    }

}

package com.everyday.notification.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OTPListener {
    @KafkaListener(
            topics = "",
            groupId = ""
    )
    void listener(String data){

    }
}

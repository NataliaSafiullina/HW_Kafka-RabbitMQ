package ru.safiullina.HWCreditResponse.listener;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.safiullina.CreditDecisionEvent;
import ru.safiullina.HWCreditResponse.service.CreditDecisionService;

@Component
@AllArgsConstructor
@Slf4j
public class KafkaMyListener {

    private final CreditDecisionService service;

    @KafkaListener(topics = "credit-request", groupId = "CreditResponse")
    public void handle(@Payload CreditDecisionEvent event) {
        System.out.println(event.toString());
        service.makeDecision(event);
    }

}

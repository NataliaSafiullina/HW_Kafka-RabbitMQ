package ru.safiullina.HWCreditRequest.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.safiullina.CreditDecisionEvent;
import ru.safiullina.HWCreditRequest.service.CreditService;

@Component
@RequiredArgsConstructor
public class RabbitMessageListener {

    private final CreditService service;

    @RabbitListener(queues = "credit_app")
    public void listenCreditDecision(CreditDecisionEvent creditDecisionEvent) {
        System.out.println("Consume message: " + creditDecisionEvent);
        service.saveCreditDecision(creditDecisionEvent);
    }
}

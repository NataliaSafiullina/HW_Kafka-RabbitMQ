package ru.safiullina.HWCreditResponse.service;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.safiullina.CreditDecisionEvent;

@Service
public class CreditDecisionService {
    @Autowired
    private final RabbitTemplate rabbitTemplate;

    public CreditDecisionService(RabbitTemplate rabbitTemplate, KafkaTemplate<String, CreditDecisionEvent> kafkaTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void makeDecision(CreditDecisionEvent event){
        if ((event.getAmount()/event.getTerm()) > event.getIncome()/2) {
            event.setStatus("refused");
        }
        else {
            event.setStatus("approved");
        }
        System.out.println("Send decision. " + event);

        try {
            rabbitTemplate.convertAndSend("creditapp", event);
            System.out.println("Сообщение успешно отправлено в очередь.");
        } catch (AmqpException e) {
            System.out.println("Ошибка отправки сообщения: " + e);
        }

    }
}

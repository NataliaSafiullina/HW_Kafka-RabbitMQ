package ru.safiullina.HWCreditRequest.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.safiullina.CreditDecisionEvent;
import ru.safiullina.HWCreditRequest.dto.CreditRequestDto;
import ru.safiullina.HWCreditRequest.entity.CreditRequestEntity;
import ru.safiullina.HWCreditRequest.repository.CreditRepository;

@Service
public class CreditService {

    private final CreditRepository creditRepository;
    private final KafkaTemplate<String, CreditDecisionEvent> kafkaTemplate;

    public CreditService(CreditRepository creditRepository, KafkaTemplate<String, CreditDecisionEvent> kafkaTemplate) {
        this.creditRepository = creditRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Integer createCreditRequest(CreditRequestDto creditRequestDto) {
        CreditRequestEntity creditRequestEntity = new CreditRequestMapperImpl().mapDtoToEntity(creditRequestDto);
        System.out.println("Create credit request: " + creditRequestEntity);

        CreditRequestEntity savedEntity = creditRepository.save(creditRequestEntity);

        CreditDecisionEvent event = new CreditDecisionEvent(
                savedEntity.getId(),
                savedEntity.getStatus(),
                savedEntity.getAmount(),
                savedEntity.getTerm(),
                savedEntity.getIncome()
        );
        kafkaTemplate.send("credit-request", event);
        return savedEntity.getId();
    }

    public String getCreditStatus(Integer id) {
        System.out.println("Find by ID = " + id);
        return creditRepository.findById(id).isPresent() ?
                creditRepository.findById(id).get().getStatus() :
                null;
    }

    public void saveCreditDecision(CreditDecisionEvent creditDecisionEvent) {
        System.out.println("Save event-decision: " + creditDecisionEvent);
        creditRepository.setStatusById(creditDecisionEvent.getStatus(), creditDecisionEvent.getId());
    }
}

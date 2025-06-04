package ru.safiullina.HWCreditRequest.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.safiullina.CreditDecisionEvent;
import ru.safiullina.HWCreditRequest.dto.CreditRequestDto;
import ru.safiullina.HWCreditRequest.entity.CreditRequestEntity;
import ru.safiullina.HWCreditRequest.repository.CreditRepository;

import java.util.Optional;

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

    @Transactional
    public void saveCreditDecision(CreditDecisionEvent creditDecisionEvent) {

        try {
            System.out.println("Save event-decision: " + creditDecisionEvent);
            Optional<CreditRequestEntity> entity = creditRepository.findById(creditDecisionEvent.getId());
            if (entity.isPresent()){
                System.out.println("Нашли запись по ID. " + entity.toString());
                System.out.println(creditDecisionEvent.getStatus() + " " + creditDecisionEvent.getId());
                creditRepository.updateStatusById(creditDecisionEvent.getStatus(), creditDecisionEvent.getId());
            }


        } catch (Exception ex) {
            System.out.println("Ошибка при обработке сообщения: " + ex);
        }

    }
}

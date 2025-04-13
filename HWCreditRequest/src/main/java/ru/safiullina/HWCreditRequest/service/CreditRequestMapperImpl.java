package ru.safiullina.HWCreditRequest.service;

import org.springframework.stereotype.Component;
import ru.safiullina.HWCreditRequest.dto.CreditRequestDto;
import ru.safiullina.HWCreditRequest.entity.CreditRequestEntity;

import javax.annotation.processing.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2025-04-12T15:53:13+0700",
        comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 23 (Oracle Corporation)"
)
@Component
public class CreditRequestMapperImpl implements CreditRequestMapper {

    @Override
    public CreditRequestEntity mapDtoToEntity(CreditRequestDto creditRequestDto) {
        if (creditRequestDto == null) {
            return null;
        }

        CreditRequestEntity.CreditRequestEntityBuilder creditRequestEntity = CreditRequestEntity.builder();

        creditRequestEntity.amount(creditRequestDto.getAmount());
        creditRequestEntity.term(creditRequestDto.getTerm());
        creditRequestEntity.income(creditRequestDto.getIncome());
        creditRequestEntity.credit_load(creditRequestDto.getCredit_load());
        creditRequestEntity.rating(creditRequestDto.getRating());
        creditRequestEntity.status("in processing");

        return creditRequestEntity.build();
    }

    @Override
    public CreditRequestDto mapEntityToDto(CreditRequestEntity creditRequestEntity) {
        if (creditRequestEntity == null) {
            return null;
        }

        CreditRequestDto creditRequestDto = new CreditRequestDto();

        creditRequestDto.setAmount(creditRequestEntity.getAmount());
        creditRequestDto.setTerm(creditRequestEntity.getTerm());
        creditRequestDto.setIncome(creditRequestEntity.getIncome());
        creditRequestDto.setCredit_load(creditRequestEntity.getCredit_load());
        creditRequestDto.setRating(creditRequestEntity.getRating());

        return creditRequestDto;
    }
}

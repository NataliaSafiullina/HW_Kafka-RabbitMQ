package ru.safiullina.HWCreditRequest.service;

import org.mapstruct.Mapper;
import ru.safiullina.HWCreditRequest.dto.CreditRequestDto;
import ru.safiullina.HWCreditRequest.entity.CreditRequestEntity;

@Mapper(componentModel = "spring")
public interface CreditRequestMapper {

    CreditRequestEntity mapDtoToEntity(CreditRequestDto creditRequestDto);

    CreditRequestDto mapEntityToDto(CreditRequestEntity creditRequestEntity);
}

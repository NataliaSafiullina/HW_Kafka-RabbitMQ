package ru.safiullina.HWCreditRequest.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreditRequestDto {
    private float amount;
    private int term;
    private float income;
    private float credit_load;
    private int rating;
}

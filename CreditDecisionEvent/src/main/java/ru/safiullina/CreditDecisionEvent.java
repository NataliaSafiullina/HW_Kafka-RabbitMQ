package ru.safiullina;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class CreditDecisionEvent {

    private Integer id;
    private String status;
    private float amount;
    private int term;
    private float income;
}

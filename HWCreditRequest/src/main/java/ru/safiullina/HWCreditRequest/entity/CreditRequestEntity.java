package ru.safiullina.HWCreditRequest.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * +--------+--------------+------+-----+---------------+----------------+
 * | Field  | Type         | Null | Key | Default       | Extra          |
 * +--------+--------------+------+-----+---------------+----------------+
 * | id     | int unsigned | NO   | PRI | NULL          | auto_increment |
 * | amount | decimal(9,2) | NO   |     | NULL          |                |
 * | term   | int          | NO   |     | NULL          |                |
 * | income | decimal(9,2) | YES  |     | NULL          |                |
 * | load   | decimal(9,2) | YES  |     | NULL          |                |
 * | rating | int          | YES  |     | NULL          |                |
 * | status | varchar(20)  | YES  |     | in processing |                |
 * +--------+--------------+------+-----+---------------+----------------+
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Entity
@Table(name = "credit_requests")
public class CreditRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "amount", nullable = false)
    private float amount;
    @Column(name = "term", nullable = false)
    private int term;
    @Column(name = "income")
    private float income;
    @Column(name = "credit_load")
    private float credit_load;
    @Column(name = "rating")
    private int rating;

    @Column(name = "status")
    private String status = "in processing";

    public CreditRequestEntity(float amount, int term, float income, float credit_load, int rating) {
        this.amount = amount;
        this.term = term;
        this.income = income;
        this.credit_load = credit_load;
        this.rating = rating;
        this.status = "in processing";
    }
}

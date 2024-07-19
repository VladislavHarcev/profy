package com.profy.profuru.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.UUID;



@Getter
@Setter
@Entity
@Table(name = "bank_balance_history")
public class BalanceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Уникальный идентификатор")
    private UUID id;

    @Column(name="rev")
    private Integer revision;

    @Column(name="revtype")
    private Integer revisionType;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "executor_id", referencedColumnName = "id")
    @Schema(description = "Связанный исполнитель")
    private Executor executor;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @Schema(description = "Связанный заказчик")
    private Customer customer;

    @Column(name="balance")
    @Schema(description = "Баланс заказчика или исполнителя")
    private BigDecimal balance;

}

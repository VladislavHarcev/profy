package com.profy.profuru.models;


import com.profy.profuru.annotationEntity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "bank_balance")
@Setter
@Getter
@Schema(description = "Класс, представляющий клиента в системе" )
public class Balance extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "executorId", referencedColumnName = "id")
    @Schema(description = "Связанный исполнитель")
    private Executor executor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    @Schema(description = "Id заказчика")
    private Customer customer;

    @Column(name="balance")
    @Schema(description = "Баланс заказчика или исполнителя")
    private BigDecimal balance;

}

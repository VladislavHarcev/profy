package com.profy.profuru.models;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import java.math.BigDecimal;
import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;


@Entity
@Table(name = "bank_balance")
@Setter
@Getter
@Schema(description = "Класс, представляющий клиента в системе" )
@Audited
@AuditTable(value = "bank_balance_history")
public class Balance extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "executorId", referencedColumnName = "id")
    @Schema(description = "Связанный исполнитель")
    @Audited(targetAuditMode = NOT_AUDITED)
    private Executor executor;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    @Schema(description = "Связанный заказчик")
    @Audited(targetAuditMode = NOT_AUDITED)
    private Customer customer;

    @Column(name="balance")
    @Schema(description = "Баланс заказчика или исполнителя")
    private BigDecimal balance;

}
